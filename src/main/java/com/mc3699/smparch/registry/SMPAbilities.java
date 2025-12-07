package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.archetype.aidenman.PhotosynthesisAbility;
import com.mc3699.smparch.archetype.arveral.TendrilsAbility;
import com.mc3699.smparch.archetype.blox.BloxDashAbility;
import com.mc3699.smparch.archetype.blox.BloxShieldAbility;
import com.mc3699.smparch.archetype.blox.BloxSpeedAbility;
import com.mc3699.smparch.archetype.darkninja.ExperienceCrystallizationAbility;
import com.mc3699.smparch.archetype.darkninja.FocusedTeleportationAbility;
import com.mc3699.smparch.archetype.darkninja.HighJumpAbility;
import com.mc3699.smparch.archetype.darkninja.MoonsPunishment;
import com.mc3699.smparch.archetype.firelight.DragonLeapAbility;
import com.mc3699.smparch.archetype.growth.GrowthDebuff;
import com.mc3699.smparch.archetype.growth.NatureGiftAbility;
import com.mc3699.smparch.archetype.growth.NatureSightAbility;
import com.mc3699.smparch.archetype.john_ultrakill.UltrakillDashAbility;
import com.mc3699.smparch.archetype.john_ultrakill.UltrakillFeedbackerAbility;
import com.mc3699.smparch.archetype.john_ultrakill.UltrakillSlamAbility;
import com.mc3699.smparch.archetype.nightheart.RandomTeleportAbility;
import com.mc3699.smparch.archetype.oscar_savior.BlinkAbility;
import com.mc3699.smparch.archetype.oscar_savior.DeterminationAbility;
import com.mc3699.smparch.archetype.starry.AirJumpAbility;
import com.mc3699.smparch.archetype.starry.CessationAbility;
import com.mc3699.smparch.archetype.starry.ConfusionAbility;
import com.mc3699.smparch.archetype.terra.HeartbeatAbility;
import com.mc3699.smparch.archetype.terra.SolarSurgeAbility;
import com.mc3699.smparch.generic_abilities.DashAbility;
import com.mc3699.smparch.generic_abilities.InvisTestAbility;
import com.mc3699.smparch.generic_abilities.WallClimbAbility;
import net.mc3699.provenance.ProvenanceRegistries;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMPAbilities {

    public static final DeferredRegister<BaseAbility> ABILITIES =
            DeferredRegister.create(ProvenanceRegistries.ABILITY_REGISTRY, SMPArch.MODID);

    public static final Supplier<InvisTestAbility> INVIS_TEST =
            ABILITIES.register("vanish", InvisTestAbility::new);

    public static final Supplier<RandomTeleportAbility> RANDOM_TELEPORT =
            ABILITIES.register("random_teleport", RandomTeleportAbility::new);

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

    public static final Supplier<NatureGiftAbility> NATURES_GIFT =
            ABILITIES.register("nature_gift", NatureGiftAbility::new);

    public static final Supplier<NatureSightAbility> NATURES_SIGHT =
            ABILITIES.register("nature_sight", NatureSightAbility::new);

    public static final Supplier<GrowthDebuff> GROWTH_DEBUFF =
            ABILITIES.register("growth_debuff", GrowthDebuff::new);

    public static final Supplier<HeartbeatAbility> HEARTBEAT =
            ABILITIES.register("heartbeat", HeartbeatAbility::new);

    public static final Supplier<SolarSurgeAbility> SOLAR_SURGE =
            ABILITIES.register("solar_surge", SolarSurgeAbility::new);

    public static final Supplier<UltrakillDashAbility> ULTRAKILL_DASH =
            ABILITIES.register("ultrakill_dash", UltrakillDashAbility::new);

    public static final Supplier<UltrakillSlamAbility> ULTRAKILL_SLAM =
            ABILITIES.register("ultrakill_slam", UltrakillSlamAbility::new);

    public static final Supplier<UltrakillFeedbackerAbility> ULTRAKILL_FEEDBACKER =
            ABILITIES.register("ultrakill_feedbacker", UltrakillFeedbackerAbility::new);

    public static final Supplier<FocusedTeleportationAbility> FOCUSED_TELEPORTATION =
            ABILITIES.register("focused_teleportation", FocusedTeleportationAbility::new);

    public static final Supplier<ExperienceCrystallizationAbility> EXPERIENCE_CRYSTALLIZATION =
            ABILITIES.register("experience_crystallization", ExperienceCrystallizationAbility::new);

    public static final Supplier<HighJumpAbility> HIGH_JUMP =
            ABILITIES.register("high_jump", HighJumpAbility::new);

    public static final Supplier<MoonsPunishment> MOONS_PUNISHMENT =
            ABILITIES.register("moons_punishment", MoonsPunishment::new);

    public static final Supplier<ConfusionAbility> CONFUSION =
            ABILITIES.register("confusion", ConfusionAbility::new);

    public static final Supplier<CessationAbility> CESSATION =
            ABILITIES.register("cessation", CessationAbility::new);

    public static final Supplier<AirJumpAbility> AIR_JUMP =
            ABILITIES.register("air_jump", AirJumpAbility::new);


    public static void register(IEventBus eventBus) { ABILITIES.register(eventBus); }

}
