package com.mc3699.smparch.archetype.john_ultrakill;

import com.mc3699.smparch.registry.SMPSounds;
import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge.EXPLOSION_DAMAGE_CALCULATOR;

public class UltrakillSlamAbility extends ToggleAbility {

    private int fallTime = 0;

    @Override
    public float getUseCost() {
        return 0;
    }

    @Override
    public Component getName() {
        return Component.literal("Potential Energy Storage System");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return !serverPlayer.getBlockStateOn().is(Blocks.AIR);
    }

    @Override
    public void tick(ServerPlayer serverPlayer) {
        ServerLevel serverLevel = serverPlayer.serverLevel();

        if(!serverPlayer.onGround())
        {

            fallTime++;
            serverPlayer.setDeltaMovement(0,-3f,0);
            serverPlayer.hurtMarked = true;

            if(fallTime == 2)
            {
                serverLevel.playSound(null, serverPlayer.getBlockPosBelowThatAffectsMyMovement().above(1), SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, 1, 1);
            }


            if(fallTime % 20 == 0)
            {
                serverLevel.playSound(null, serverPlayer.getBlockPosBelowThatAffectsMyMovement().above(1), SMPSounds.SLAM_FALL.value(), SoundSource.PLAYERS, 1, 0.8f + (serverLevel.random.nextFloat() * 0.5f));
            }
        } else {
            fallTime = 0;
            serverPlayer.fallDistance = 0;
            serverLevel.explode(serverPlayer, null, EXPLOSION_DAMAGE_CALCULATOR, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 12.0F, false, Level.ExplosionInteraction.TRIGGER, ParticleTypes.GUST_EMITTER_LARGE, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.WIND_CHARGE_BURST);
            this.setEnabled(false);
        }




    }
}
