package com.mc3699.smparch.archetype.john_generic;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//A Goober for quick testing of the generic stuff
public class JohnArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("John Generic");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilities = new LinkedHashMap<>();
        abilities.put(1, SMPAbilities.ARROW.get());
        abilities.put(2, SMPAbilities.DASH.get());
        abilities.put(3, SMPAbilities.FIREBALL.get());
        abilities.put(4, SMPAbilities.INVIS_TEST.get());
        abilities.put(5, SMPAbilities.WALL_CLIMB.get());
        return abilities;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
