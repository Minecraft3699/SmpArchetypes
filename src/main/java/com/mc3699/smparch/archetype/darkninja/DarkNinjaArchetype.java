package com.mc3699.smparch.archetype.darkninja;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Set;

public class DarkNinjaArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("DarkNinja");
    }
    
    @Override
    public List<Component> getDescription() {
        return List.of();
    }
    
    @Override
    public Set<ResourceLocation> getGrantedAbilities() {
        return Set.of(
                SMPArch.path("focused_teleportation"),
                SMPArch.path("random_teleport"),
                SMPArch.path("experience_crystallization"),
                SMPArch.path("high_jump")
        );
    }
    
    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of(
                SMPAbilities.MOONS_PUNISHMENT.get()
        );
    }
}