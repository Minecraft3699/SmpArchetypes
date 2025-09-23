package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.archetype.firelight.DragonLeapAbility;
import com.mc3699.smparch.archetype.nightheart.RandomTeleportAbility;
import com.mc3699.smparch.archetype.nuzabilitys.CodAbility;
import com.mc3699.smparch.archetype.nuzabilitys.Brace;
import com.mc3699.smparch.generic_abilities.DashAbility;
import net.mc3699.provenance.ProvenanceRegistries;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMPAbilities {

    public static final DeferredRegister<BaseAbility> ABILITIES =
            DeferredRegister.create(ProvenanceRegistries.ABILITY_REGISTRY, SMPArch.MODID);


    public static final Supplier<RandomTeleportAbility> RANDOM_TELEPORT =
            ABILITIES.register("random_teleport", RandomTeleportAbility::new);

    public static final Supplier<Brace> BRACE =
            ABILITIES.register("brace", Brace::new);

    public static final Supplier<CodAbility> COD_ABILITY =
            ABILITIES.register("cod", CodAbility::new);

    public static final Supplier<DashAbility> DASH =
            ABILITIES.register("dash", DashAbility::new);

    public static final Supplier<DragonLeapAbility> DRAGON_LEAP =
            ABILITIES.register("dragon_leap", DragonLeapAbility::new);


    public static void register(IEventBus eventBus)
    {
        ABILITIES.register(eventBus);
    }

}
