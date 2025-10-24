package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;

public class HighJumpAbility extends ToggleAbility {
    // no clue how the costs work or what is a good cost for such a thing
    @Override
    public float getUseCost() {
        return 0.02f;
    }

    @Override
    public Component getName() {
        return Component.literal("High Jump");
    }

    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/item/feather.png");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) { return true; }
    // This function is probably one of the most lazy ways to do this, maybe there's a better way to get what I want
    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 3));
    }
}
