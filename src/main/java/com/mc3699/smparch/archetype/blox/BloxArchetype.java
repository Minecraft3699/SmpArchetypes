package com.mc3699.smparch.archetype.blox;

import com.mc3699.smparch.registry.SMPAbilities;
import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BloxArchetype extends BaseArchetype {
    @Override
    public Component getName() {
        return Component.literal("Blox");
    }

    @Override
    public List<Component> getDescription() {
        return List.of();
    }

    @Override
    public HashMap<Integer, BaseAbility> getPlayerAbilities() {
        HashMap<Integer, BaseAbility> abilities = new LinkedHashMap<>();
        abilities.put(1, SMPAbilities.BLOX_DASH.get());
        abilities.put(2, SMPAbilities.BLOX_SPEED.get());
        abilities.put(3, SMPAbilities.BLOX_SHIELD.get());
        return abilities;
    }

    @Override
    public List<AmbientAbility> getAmbientAbilities() {
        return List.of();
    }

    public static boolean checkArmor(ServerPlayer serverPlayer)
    {
        return !serverPlayer.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                !serverPlayer.getItemBySlot(EquipmentSlot.CHEST).isEmpty() &&
                !serverPlayer.getItemBySlot(EquipmentSlot.LEGS).isEmpty() &&
                !serverPlayer.getItemBySlot(EquipmentSlot.FEET).isEmpty();
    }
}
