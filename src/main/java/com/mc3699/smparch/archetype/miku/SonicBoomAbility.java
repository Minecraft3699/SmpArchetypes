package com.mc3699.smparch.archetype.miku;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SonicBoomAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 1.5f;
    }

    @Override
    public int getCooldown() { return 10*20; }

    @Override
    public Component getName() {
        return Component.literal("Sonic Boom");
    }

    // "Create a Warden's Sonic Boom (With 8 Damage instead of 16 like base) at a target"

    @Override
    public boolean canExecute(ServerPlayer player) {
        return true;
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/sculk_catalyst_top_bloom.png");
    }
}
