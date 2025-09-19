package com.mc3699.smparch.archetype.nuzabilitys;

import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cod;

public class NonAmbientAbility extends AmbientAbility {
    @Override
    public void tick(ServerPlayer serverPlayer) {

    }

    @Override
    public Component getName() {
        return Component.literal("AmbientTestt");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return serverPlayer.level().isRaining();
    }
}
