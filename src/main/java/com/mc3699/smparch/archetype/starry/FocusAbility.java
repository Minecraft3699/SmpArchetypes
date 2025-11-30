package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;


public class FocusAbility extends ToggleAbility {

    // Refills your action points a bit faster while you stand. Side effects may include paralysis and 4 minute weakness.

    @Override
    public float getUseCost() {
        return 0f;
    }

    @Override
    public Component getName() {
        return Component.literal("Focus").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.BOLD);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240*20, 100));
        ProvenanceDataHandler.changeAP(player, 0.02f);
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/mob_effect/absorption.png");
    }
}
