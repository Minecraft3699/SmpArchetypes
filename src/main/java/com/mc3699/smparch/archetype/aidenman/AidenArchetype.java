package com.mc3699.smparch.archetype.aidenman;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AidenArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Aidenman1123");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        return new HashMap<>();
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of(
                SMPAbilities.PHOTOSYNTHESIS.get()
        );
    }
}
