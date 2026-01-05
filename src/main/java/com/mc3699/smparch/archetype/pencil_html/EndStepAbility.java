package com.mc3699.smparch.archetype.pencil_html;

import com.mc3699.smparch.registry.SMPAbilities;
import com.mc3699.smparch.registry.SMPSounds;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class EndStepAbility extends ToggleAbility {
    private Vec3 teleport_position;


    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("smparch", "textures/ability_icon/end_step.png");
    }

    @Override
    public float getUseCost() {
        return 0.07f;
    }

    @Override
    public Component getName() {
        return Component.literal("End Step");
    }

    @Override
    public void setEnabled(ServerPlayer player, int slot, boolean enabled) {
        if (!enabled) {
            AttributeInstance moveSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
            ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath("smparch", "end_step_slow");
            moveSpeed.removeModifier(modifierId);
        }
        super.setEnabled(player, slot, enabled);
    }

    private void spawnTeleportParticles(ServerPlayer player, Level level) {
        float radius = 0.75f;
        int particleCount = 28;
        float maxRange = 16f;

        Vec3 eyePosition = player.getEyePosition();
        Vec3 lookAngle = player.getLookAngle();
        Vec3 endPosition = eyePosition.add(lookAngle.scale(16));
        Vec3 targetPosition = null;

        BlockHitResult result = player.level().clip(new ClipContext(
                eyePosition,
                endPosition,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        if (result.getType() != HitResult.Type.MISS) {
            Vec3 resultPosition = result.getLocation();

            if (result.getDirection() == Direction.UP) {
                targetPosition = resultPosition;

            } else {
                BlockPos hitBlock = result.getBlockPos();
                BlockPos aboveHitBlock = (hitBlock.above());
                BlockState state = level.getBlockState(aboveHitBlock);
                Block block = state.getBlock();

                Vec3 topOfBlock = new Vec3(
                        hitBlock.getX() + 0.5,
                        hitBlock.getY() + 1.0,
                        hitBlock.getZ() + 0.5
                );

                if (eyePosition.distanceTo(topOfBlock) <= maxRange && block == Blocks.AIR) {
                    targetPosition = topOfBlock;
                }
            }
        } else {
            Vec3 searchStart = endPosition;
            Vec3 searchEnd = endPosition.add(0, -256, 0);

            BlockHitResult downResult = player.level().clip(new ClipContext(
                    searchStart,
                    searchEnd,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player
            ));

            if (downResult.getType() != HitResult.Type.MISS &&
                    downResult.getDirection() == Direction.UP) {
                targetPosition = downResult.getLocation();
            }
        }

        if (targetPosition != null) {
            this.teleport_position = targetPosition;
            for (int i = 0; i < particleCount; i++) {

                double angle = (i / (double)particleCount) * Math.PI * 2;
                double xOffset = radius * Math.cos(angle);
                double zOffset = radius * Math.sin(angle);

                player.serverLevel().sendParticles(
                        new DustParticleOptions(new Vector3f(0.7f, 0.3f, 0.9f), 0.7f),
                        targetPosition.x + xOffset,
                        targetPosition.y + 0.1,
                        targetPosition.z + zOffset,
                        1,
                        0, 0, 0,
                        0
                );
            }
        }
    }

    private void teleportPlayer(ServerPlayer player, Vec3 position) {
            double playerX = position.x;
            double playerY = position.y;
            double playerZ = position.z;



            ServerLevel level = player.serverLevel();

            player.teleportTo(playerX, playerY, playerZ);
            for (int slot = 0; slot < 9; slot++) {
                setEnabled(player, slot, false);
            }
            level.playSound(null, playerX, playerY, playerZ, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 0.7f);

    }

    @Override
    public void tick(ServerPlayer serverPlayer) {
        AttributeInstance moveSpeed = serverPlayer.getAttribute(Attributes.MOVEMENT_SPEED);
        ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath("smparch", "end_step_slow");

        if (moveSpeed.getModifier(modifierId) == null) {
            moveSpeed.addTransientModifier(new AttributeModifier(
                    modifierId,
                    -0.035,
                    AttributeModifier.Operation.ADD_VALUE
            ));
        }
        spawnTeleportParticles(serverPlayer, serverPlayer.serverLevel());
        if (serverPlayer.isShiftKeyDown() && this.teleport_position != null) {
            teleportPlayer(serverPlayer, this.teleport_position);
        }

        super.tick(serverPlayer);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void backgroundTick(ServerPlayer serverPlayer) {
        if (serverPlayer.isInWaterOrRain()) {
            serverPlayer.hurt(serverPlayer.damageSources().drown(), 2.0f);
        }
    }
}
