package com.mc3699.smparch.archetype.starry;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

public class AirJumpAbility extends BaseAbility {
    private static final double VELOCITY_MULT = 1.5d;

    @Override
    public float getUseCost() {
        return 3.5f;
    }

    @Override
    public Component getName() {
        return Component.literal("Air Jump");
    }

    @Override
    public boolean canExecute(ServerPlayer player) {
        return true;
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 lookDir = player.getLookAngle();

        Vec3 dashMotion = lookDir.multiply(VELOCITY_MULT, VELOCITY_MULT*4, VELOCITY_MULT);
        player.setDeltaMovement(dashMotion);
        player.hurtMarked = true;
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20*4, 1));
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/mob_effect/speed.png");
    }
}