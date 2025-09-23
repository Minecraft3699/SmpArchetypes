package com.mc3699.smparch.archetype.nuzabilitys;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Brace extends BaseAbility {
    @Override
    public float getUseCost() {
        return 1;
    }

    @Override
    public Component getName() {
        return Component.literal("Brace");
    }
    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/item/iron_ingot.png");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void execute(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,20,10));
        super.execute(player);

    }
}
