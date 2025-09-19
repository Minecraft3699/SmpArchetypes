package com.mc3699.smparch.archetype.nuzabilitys;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.Provenance;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.mc3699.provenance.registry.ProvAbilities;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.List;

public class NuzArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Nuz");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilityMap = new HashMap<>();
        abilityMap.put(1, SMPAbilities.BRACE.get());
        abilityMap.put(2, ProvAbilities.RAGE.get());
        return abilityMap;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of(
                ProvAbilities.NIGHT_VISION_AMBIENT.get()
        );
    }
}
