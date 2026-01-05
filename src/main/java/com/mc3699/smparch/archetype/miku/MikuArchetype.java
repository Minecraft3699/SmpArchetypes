package com.mc3699.smparch.archetype.miku;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MikuArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Miku");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilities = new LinkedHashMap<>();
        abilities.put(1, SMPAbilities.SKULK_BLAST.get());
        abilities.put(2, SMPAbilities.WARDEN_SKIN.get());
        abilities.put(3, SMPAbilities.WARDEN_STRENGTH.get());
        abilities.put(4, SMPAbilities.SONIC_BOOM.get());
        abilities.put(5, SMPAbilities.STRONG_LEGS_HEAVY_ARMS.get());
        abilities.put(6, SMPAbilities.SKULK_SHIELD.get());
        abilities.put(7, SMPAbilities.DEEP_DARKNESS.get());
        abilities.put(8, SMPAbilities.AID_FROM_BELOW.get());
        return abilities;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of(
            SMPAbilities.ONE_WITH_THE_DARK.get()
        );
    }
}