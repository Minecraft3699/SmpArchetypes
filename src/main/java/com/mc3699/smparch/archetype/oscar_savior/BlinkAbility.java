package com.mc3699.smparch.archetype.oscar_savior;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BlinkAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 2.5f;
    }

    @Override
    public Component getName() {
        return Component.literal("Blink");
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/item/ender_pearl.png");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 target = eyePos.add(look.scale(8));

        BlockHitResult hitResult = player.serverLevel().clip(new ClipContext(
                eyePos, target,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        Vec3 endPos = hitResult.getType() == BlockHitResult.Type.MISS
                ? target
                : hitResult.getLocation().subtract(look.scale(0.5));

        player.teleportTo(endPos.x, endPos.y, endPos.z);
        player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}
