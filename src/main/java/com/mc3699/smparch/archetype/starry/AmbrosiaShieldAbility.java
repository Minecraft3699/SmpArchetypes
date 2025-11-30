package com.mc3699.smparch.archetype.starry;

import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;


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

    private static void spawnParticles(ServerLevel level, Vec3 pos) {


        level.sendParticles(
                new DustParticleOptions(new Vector3f(1f, 0.8f, 0.4f), 1.5f),
                pos.x, pos.y, pos.z,
                2,  // count
                0.5, 0.5, 0.5,  // offsets
                0.5f  // speed
        );
    }

    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);

        spawnParticles(player.serverLevel(),player.position().add(0,1,0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 100));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/mob_effect/absorption.png");
    }

    @Override
    public int getCooldown() { return 10 * 20; }

}
