package com.mc3699.smparch.archetype.nightheart;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.mc3699.provenance.registry.ProvAbilities;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.List;

public class NightheartArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Nightheart");
    }

    @Override
    public List<Component> getDescription() {
        return List.of(
                Component.literal("Made for DrNightheart")
        );
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilityMap = new HashMap<>();
        abilityMap.put(1, SMPAbilities.RANDOM_TELEPORT.get());

        return abilityMap;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
