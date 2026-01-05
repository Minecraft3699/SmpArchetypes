package com.mc3699.smparch.archetype.tekkitdooood;

import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.List;

public class TekkitdoooodArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("El doooodit");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        return null;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
