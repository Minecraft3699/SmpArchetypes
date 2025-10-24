package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;

public class MoonsPunishment extends AmbientAbility {
    // TODO: only run the tick code every 2 seconds or something to reduce stress, probably using modulo operator
    @Override
    public void tick(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        int phase = level.getMoonPhase();
        if (phase == 2 || phase == 3 || phase == 5 || phase == 6) {
            // meant to give you weakness 1 on Third Quarter, Waning Crescent, Waxing Crescent, and First Quarter moon phases
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
        } else if (phase == 4) {
            // meant to give you weakness 2 and slowness 1 on full moon
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0));
        }
        // TODO: give weakness 3, withering 1, and nausea 1 when being chased by TBE
        /*player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 2));
        player.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 0));
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20, 0));*/
    }

    @Override
    public Component getName() {
        return Component.literal("The Moon itself disapproves of your power");
    }

    @Override
    public boolean canExecute(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        BlockPos pos = player.blockPosition();
        return level.canSeeSky(pos);
    }
}
