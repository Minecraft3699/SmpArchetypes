package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ConfusionAbility extends BaseAbility {
    // Description: Does random things to entities within 15 blocks or so
    // Possible events: Lightning bolt; Random vanilla effect; Teleport 80 blocks up; Summon 2 silverfish; Launch firework; Hurt by 5.
    // The user himself is not safe from the consequences, so I think it's pretty fair.

    private ServerPlayer player;
    private Level level;

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
        //super.execute(player);
        this.player = player;
        this.level = player.serverLevel();

        float radius = 15f;



        AABB box = player.getBoundingBox().inflate(radius);

        List<LivingEntity> colEntities = level.getEntitiesOfClass(LivingEntity.class, box); // Entities that collide with the box

        spawnParticles(ParticleTypes.REVERSE_PORTAL, player.position());

        fuckShitUp(colEntities.toArray(new LivingEntity[0]));


    }

    private void fuckShitUp(LivingEntity[] entities) {

        List<EntityType<?>> whitelist = BuiltInRegistries.ENTITY_TYPE.stream()
                .filter(type -> type != EntityType.WOLF && type != EntityType.HORSE && type != EntityType.CAT) // So that no one gets hurt.
                .toList();


        int choice = level.random.nextInt(6);


        for (LivingEntity entity : entities) {
            if (!whitelist.contains(entity.getType())) return;

            boolean isPlayer = entity instanceof ServerPlayer;

            if (isPlayer)
                ((ServerPlayer) entity).displayClientMessage(Component.literal("You sense the gale around you ceasing...").withStyle(ChatFormatting.BOLD, ChatFormatting.LIGHT_PURPLE), true);

            ProvScheduler.schedule(5 * 20, () -> choice(entity, choice, isPlayer, level));

        }

        level.playSound(null, player.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }

    private void choice(LivingEntity entity, int choice, boolean isPlayer, Level level) {

        switch (choice) {
            case 5:
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                //bolt.setVisualOnly(true);
                bolt.setPos(entity.position());
                level.addFreshEntity(bolt);

                spawnParticles(ParticleTypes.CLOUD, entity.position());

                break;

            case 4:
                entity.hurt(entity.damageSources().indirectMagic(player, null),5f);

                spawnParticles(ParticleTypes.CRIT, entity.position());

                level.playSound(null, entity.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.ANVIL_HIT, SoundSource.PLAYERS);
                break;

            case 3:
                spawnParticles(ParticleTypes.ENCHANT, entity.position());

                entity.teleportTo(entity.getX(),entity.getY() + 80, entity.getZ());
                entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,5*20,2));
                break;

            case 2:

                for(int i = 0; i < 2; i++) {
                    Silverfish s = new Silverfish(EntityType.SILVERFISH, level);
                    s.setPos(entity.position());
                    s.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
                            s,
                            LivingEntity.class,
                            false,
                            target -> target == entity
                    ));
                    s.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 4, false, false));
                    s.addEffect(new MobEffectInstance(MobEffects.WITHER, Integer.MAX_VALUE, 0, false, false));

                    level.addFreshEntity(s);
                }
                spawnParticles(ParticleTypes.INFESTED, entity.position());
                break;

            case 1:
                addRandomEffect(entity);

                spawnParticles(ParticleTypes.GLOW, entity.position());
                level.playSound(null, entity.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.ALLAY_HURT, SoundSource.PLAYERS);
                break;
            case 0:
                launchFirework(entity.position().add(0,1,0));
                break;
        }
        entity.jumpFromGround();
        if(isPlayer) ((ServerPlayer) entity).displayClientMessage(Component.literal("It's a " + (choice + 1) + "!").withStyle(ChatFormatting.BOLD, ChatFormatting.LIGHT_PURPLE), true);
    }


    // this is insane bs
    FireworkRocketEntity launchFirework(Vec3 pos) {

        ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET);

        FireworkExplosion explosion = new FireworkExplosion(
                FireworkExplosion.Shape.SMALL_BALL,
                new IntArrayList(new int[] {0x8000E8}),
                new IntArrayList(new int[] {0xDEB3FF}),
                true,
                true
        );

        Fireworks fireworks = new Fireworks(
                2,
                List.of(explosion)
        );

        stack.set(DataComponents.FIREWORKS, fireworks);

        FireworkRocketEntity entity = new FireworkRocketEntity(level, pos.x, pos.y, pos.z, stack);
        level.addFreshEntity(entity);

        return entity;
    }



    private void spawnParticles(ParticleOptions type, Vec3 pos) {
        ((ServerLevel) level).sendParticles(
                type,
                pos.x, pos.y, pos.z,
                60,  // count
                1, 1, 1,  // offsets
                0.05  // speed
        );
    }

    private static void addRandomEffect(LivingEntity entity) {
        RandomSource random = entity.getRandom();

        List<ResourceLocation> keys = BuiltInRegistries.MOB_EFFECT.keySet().stream()
                .toList();

        ResourceLocation randomKey = keys.get(random.nextInt(keys.size()));

        Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getHolder(randomKey).orElse(null);

        if(effect == null) return;
        int amplifier = random.nextInt(10);         // Levels ig

        entity.addEffect(new MobEffectInstance(effect, 15*20, amplifier));
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public int getCooldown() {
        return 120 * 20;
    }
}
