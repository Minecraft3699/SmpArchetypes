package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.archetype.nightheart.RandomTeleportAbility;
import net.mc3699.provenance.ProvenanceRegistries;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.core.Holder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMPAbilities {

    public static final DeferredRegister<BaseAbility> ABILITIES =
            DeferredRegister.create(ProvenanceRegistries.ABILITY_REGISTRY, SMPArch.MODID);


    public static final Supplier<RandomTeleportAbility> RANDOM_TELEPORT =
            ABILITIES.register("random_teleport", RandomTeleportAbility::new);


    public static void register(IEventBus eventBus)
    {
        ABILITIES.register(eventBus);
    }

}
