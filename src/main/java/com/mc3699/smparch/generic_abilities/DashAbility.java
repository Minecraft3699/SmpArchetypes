package com.mc3699.smparch.generic_abilities;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class DashAbility extends BaseAbility {
    private static final double DASH_VELOCITY = 1.5d;

    @Override
    public float getUseCost() {
        return 2f;
    }

    @Override
    public Component getName() {
        return Component.literal("Dash");
    }

    @Override
    public boolean canExecute(ServerPlayer player) {
        return player.onGround();
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 lookDir = player.getLookAngle();

        Vec3 dashMotion = lookDir.scale(DASH_VELOCITY);
        player.setDeltaMovement(dashMotion);
        player.hurtMarked = true;
        player.fallDistance = 0;
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/mob_effect/speed.png");
    }
}