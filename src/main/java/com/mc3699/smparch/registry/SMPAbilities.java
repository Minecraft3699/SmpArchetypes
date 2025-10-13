package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.archetype.aidenman.PhotosynthesisAbility;
import com.mc3699.smparch.archetype.arveral.TendrilsAbility;
import com.mc3699.smparch.archetype.blox.BloxDashAbility;
import com.mc3699.smparch.archetype.blox.BloxShieldAbility;
import com.mc3699.smparch.archetype.blox.BloxSpeedAbility;
import com.mc3699.smparch.archetype.firelight.DragonLeapAbility;
import com.mc3699.smparch.archetype.nightheart.RandomTeleportAbility;
import com.mc3699.smparch.archetype.nuzabilitys.CodAbility;
import com.mc3699.smparch.archetype.nuzabilitys.Brace;
import com.mc3699.smparch.archetype.oscar_savior.BlinkAbility;
import com.mc3699.smparch.archetype.oscar_savior.DeterminationAbility;
import com.mc3699.smparch.generic_abilities.DashAbility;
import com.mc3699.smparch.generic_abilities.WallClimbAbility;
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

    public static final Supplier<WallClimbAbility> WALL_CLIMB =
            ABILITIES.register("wall_climb", WallClimbAbility::new);

    public static final Supplier<TendrilsAbility> TENDRILS =
            ABILITIES.register("tendrils", TendrilsAbility::new);

    public static final Supplier<BlinkAbility> BLINK =
            ABILITIES.register("blink", BlinkAbility::new);

    public static final Supplier<DeterminationAbility> DETERMINATION =
            ABILITIES.register("determination", DeterminationAbility::new);

    public static final Supplier<BloxShieldAbility> BLOX_SHIELD =
            ABILITIES.register("blox_shield", BloxShieldAbility::new);

    public static final Supplier<BloxDashAbility> BLOX_DASH =
            ABILITIES.register("blox_dash", BloxDashAbility::new);

    public static final Supplier<BloxSpeedAbility> BLOX_SPEED =
            ABILITIES.register("blox_speed", BloxSpeedAbility::new);

    public static final Supplier<PhotosynthesisAbility> PHOTOSYNTHESIS =
            ABILITIES.register("photosynthesis", PhotosynthesisAbility::new);


    public static void register(IEventBus eventBus)
    {
        ABILITIES.register(eventBus);
    }

}
