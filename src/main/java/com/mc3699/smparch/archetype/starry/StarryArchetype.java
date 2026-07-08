package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


import java.util.List;
import java.util.Set;

public class StarryArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Star");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public Set<ResourceLocation> getGrantedAbilities() {
        return Set.of(
                SMPArch.path("cessation"),
                SMPArch.path("air_jump")
        );
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }
}
