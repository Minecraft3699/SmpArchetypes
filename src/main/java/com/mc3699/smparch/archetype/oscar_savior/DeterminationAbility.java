package com.mc3699.smparch.archetype.oscar_savior;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class DeterminationAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 2;
    }

    @Override
    public Component getName() {
        return Component.literal("Determination");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2));
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/mob_effect/regeneration.png");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}
