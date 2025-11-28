package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfusionAbility extends BaseAbility {
    // Description: Does random things to entities within 15 blocks or so
    // Possible events: Nothing; Lightning bolt; Random vanilla effect; Teleport 100 blocks up; Ignite for 10 seconds
    // The user himself is not safe from the consequences, so I think it's pretty fair.

    @Override
    public float getUseCost() {
        return 7f;
    }

    @Override
    public Component getName() {
        return Component.literal("Confusion");
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "textures/ability_icon/confusion.png");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);

        Vec3 pPos = new Vec3(player.getX(), player.getY(), player.getZ());

        float radius = 15f; // Not actually radius, I'd call it "distance between box corners divided by two"

        Level level = player.serverLevel();


        // Horrors beyond recognition incoming
        AABB box = new AABB(
                pPos.x - radius, pPos.y - radius, pPos.z - radius,
                pPos.x + radius, pPos.y + radius, pPos.z + radius
        );

        List<LivingEntity> colEntities = level.getEntitiesOfClass(LivingEntity.class, box); // Entities that collide with the box

        level.playSound(null, new BlockPos((int)pPos.x, (int)pPos.y, (int)pPos.z), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS);

        ProvScheduler.schedule(40, () -> fuckShitUp(colEntities.toArray(new LivingEntity[0]), player));


    }

    private static void fuckShitUp(LivingEntity[] entities, ServerPlayer player) {

        List<EntityType<?>> whitelist = BuiltInRegistries.ENTITY_TYPE.stream()
                .filter(type -> type != EntityType.WOLF && type != EntityType.HORSE && type != EntityType.CAT) // So that no one gets hurt.
                .toList();

        Level level = player.serverLevel();

        for(LivingEntity entity : entities) {
            if (!whitelist.contains(entity.getType())) return;

            int choice = level.random.nextInt(5);


            // couldn't bother making a weighted choice system
            switch (choice) {
                case 0:
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0)); // 1 second so that you don't burn

                    LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    bolt.setPos(entity.getX(),entity.getY(),entity.getZ());
                    level.addFreshEntity(bolt);
                    break;
                case 1:
                    addRandomEffect(entity);
                    entity.jumpFromGround();
                    level.playSound(null, new BlockPos(entity.getBlockX(),entity.getBlockY(), entity.getBlockZ()), SoundEvents.ALLAY_HURT, SoundSource.PLAYERS);
                    break;
                case 2:
                    entity.teleportTo(entity.getX(),entity.getY() + 80, entity.getZ());
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,5*20,2));
                    break;
                case 3:
                    entity.igniteForSeconds(10);
                    entity.jumpFromGround();

                    level.playSound(null, new BlockPos(entity.getBlockX(),entity.getBlockY(), entity.getBlockZ()), SoundEvents.ANVIL_HIT, SoundSource.PLAYERS);
                    break;
            }
        }

        level.playSound(null, player.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }

    // This is single-handedly the most confusing piece of code I've ever written in my entire life
    private static void addRandomEffect(LivingEntity entity) {
        RandomSource random = entity.getRandom();


        List<ResourceLocation> keys = BuiltInRegistries.MOB_EFFECT.keySet().stream()
                .toList();

        ResourceLocation randomKey = keys.get(random.nextInt(keys.size()));

        Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getHolder(randomKey).orElse(null);

        if(effect == null) return;

        int duration = 200 + random.nextInt(200);  // 10–20 seconds ig
        int amplifier = random.nextInt(10);         // Levels ig

        entity.addEffect(new MobEffectInstance(effect, duration, amplifier));
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public int getCooldown() {
        return 30 * 20;
    }
}
