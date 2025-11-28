package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.registry.SMPSounds;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import org.apache.logging.log4j.core.jmx.Server;
import org.joml.Quaterniondc;

import java.util.Optional;
import java.util.function.Function;


public class AmbrosiaShieldAbility extends ToggleAbility {

    // Ambrosia is the food/drink of the Greek gods that gave them immortality.

    // Basically gives you insane damage resistance at the expense of speed and damage.

    @Override
    public float getUseCost() {
        return 0.04f;
    }

    @Override
    public Component getName() {
        return Component.literal("Ultimate Defense").withStyle(ChatFormatting.GOLD);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);

        player.serverLevel().playSound(player, player.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.APPLY_EFFECT_BAD_OMEN, SoundSource.PLAYERS, 8, 1);


    }

    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 100));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 1));
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/mob_effect/absorption.png");
    }
}
