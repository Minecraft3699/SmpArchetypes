package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.entity.AbilityProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SMPEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, SMPArch.MODID);

    public static final Supplier<EntityType<AbilityProjectile>> ABILITY_PROJECTILE =
            ENTITIES.register("ability_projectile",
                    () -> EntityType.Builder.of(AbilityProjectile::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("ability_projectile")
            );

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }

}
