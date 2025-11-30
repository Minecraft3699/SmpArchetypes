package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ConfusionAbility extends BaseAbility {
    // Description: Does random things to entities within 15 blocks or so
    // Possible events: Nothing; Lightning bolt; Random vanilla effect; Teleport 80 blocks up; Ignite for 10 seconds
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

        float radius = 15f;

        Level level = player.serverLevel();


        AABB box = player.getBoundingBox().inflate(radius);

        List<LivingEntity> colEntities = level.getEntitiesOfClass(LivingEntity.class, box); // Entities that collide with the box

        level.playSound(null, player.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.5f, 1);
        spawnParticles((ServerLevel) level, ParticleTypes.REVERSE_PORTAL, player.position());

        ProvScheduler.schedule(5*20, () -> fuckShitUp(colEntities.toArray(new LivingEntity[0]), player));


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
                    LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    bolt.setVisualOnly(true);
                    bolt.setPos(entity.position());
                    level.addFreshEntity(bolt);

                    spawnParticles(player.serverLevel(), ParticleTypes.CLOUD, entity.position());

                    break;
                case 1:
                    addRandomEffect(entity);

                    spawnParticles(player.serverLevel(), ParticleTypes.GLOW, entity.position());

                    entity.jumpFromGround();
                    level.playSound(null, entity.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.ALLAY_HURT, SoundSource.PLAYERS);
                    break;
                case 2:
                    entity.teleportTo(entity.getX(),entity.getY() + 80, entity.getZ());
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,5*20,2));

                    spawnParticles(player.serverLevel(), ParticleTypes.ENCHANT, entity.position());

                    break;
                case 3:
                    entity.igniteForSeconds(10);
                    entity.jumpFromGround();

                    spawnParticles(player.serverLevel(), ParticleTypes.FLAME, entity.position());

                    level.playSound(null, entity.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.ANVIL_HIT, SoundSource.PLAYERS);
                    break;
            }
        }

        level.playSound(null, player.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }

    private static void spawnParticles(ServerLevel level, ParticleOptions type, Vec3 pos) {
        level.sendParticles(
                type,
                pos.x, pos.y, pos.z,
                60,  // count
                2, 2, 2,  // offsets
                0.05  // speed
        );
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
        return 240 * 20;
    }
}
