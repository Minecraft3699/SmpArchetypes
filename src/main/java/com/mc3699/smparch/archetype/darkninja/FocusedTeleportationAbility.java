package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FocusedTeleportationAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 2;
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public Component getName() {
        return Component.literal("Focused Teleportation");
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/item/ender_eye.png");
    }

    // apparently Oscar_Savior already basically had this ability ready to go. That makes things SO much easier, unless I am clueless and this actually DOESN'T do what I want it to do.
    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 target = eyePos.add(look.scale(20));
        ServerLevel level = player.serverLevel();

        BlockHitResult hitResult = player.serverLevel().clip(new ClipContext(
                eyePos, target,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        if (hitResult.getType() == BlockHitResult.Type.MISS) {
            ProvenanceDataHandler.changeAP(player, 2);
        } else {
            Vec3 endPos = hitResult.getType() == BlockHitResult.Type.MISS
                    ? target
                    : hitResult.getLocation().subtract(look.scale(0.5));

            player.teleportTo(endPos.x, endPos.y, endPos.z);
            player.hurt(level.damageSources().fall(),5);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
        }
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}