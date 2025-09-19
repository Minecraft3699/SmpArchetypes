package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.archetype.nightheart.NightheartArchetype;
import com.mc3699.smparch.archetype.nuzabilitys.NuzArchetype;
import net.mc3699.provenance.ProvenanceRegistries;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.archetype.foundation.BaseArchetype;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMPArchetypes {

    public static final DeferredRegister<BaseArchetype> ARCHETYPES =
            DeferredRegister.create(ProvenanceRegistries.ARCHETYPE_REGISTRY, SMPArch.MODID);


    public static final Supplier<NightheartArchetype> NIGHTHEART =
            ARCHETYPES.register("nightheart", NightheartArchetype::new);

    public static final Supplier<NuzArchetype> NUZABILITYS =
            ARCHETYPES.register("nuz", NuzArchetype::new);


    public static void register(IEventBus eventBus)
    {
        ARCHETYPES.register(eventBus);
    }
}
