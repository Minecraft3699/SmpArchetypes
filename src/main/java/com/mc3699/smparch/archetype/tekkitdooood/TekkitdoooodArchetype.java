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

    //Cannot be too careful with null (also this is the way the human does it so uh yeah)
    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        return HashMap.newHashMap(0);
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
