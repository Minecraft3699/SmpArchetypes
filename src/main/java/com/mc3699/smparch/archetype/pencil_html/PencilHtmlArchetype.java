package com.mc3699.smparch.archetype.pencil_html;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PencilHtmlArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Pencil_Html");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilities = new LinkedHashMap<>();
        abilities.put(1, SMPAbilities.END_STEP.get());
        return abilities;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
