package com.mc3699.smparch.archetype.nuzabilitys;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class TestAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 2;
    }

    @Override
    public Component getName() {
        return Component.literal("Test");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void execute(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,1400,4));
        super.execute(player);
    }
}
