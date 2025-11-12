package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class MoonsPunishment extends AmbientAbility {

    private int tickCount = 0;
    @Override
    public void tick(ServerPlayer player) {
        tickCount++;
        if(tickCount > 20)
        {
            ServerLevel level = player.serverLevel();
            int phase = level.getMoonPhase();
            if (phase == 1 || phase == 2 || phase == 6 || phase == 7) {
                // meant to give you weakness 1 on Third Quarter, Waning Crescent, Waxing Crescent, and First Quarter moon phases
                MobEffectInstance weakness1 = new MobEffectInstance(MobEffects.WEAKNESS, 50, 0,true,false,true);
                player.addEffect(weakness1);
            } else if (phase == 0) {
                // meant to give you weakness 2 and slowness 1 on a full moon
                MobEffectInstance weakness2 = new MobEffectInstance(MobEffects.WEAKNESS, 50, 1, true, false, true);
                MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 0, true, false, true);
                player.addEffect(weakness2);
                player.addEffect(slowness);
            }
            tickCount = 0;
        }
    }

    @Override
    public Component getName() {
        return Component.literal("The Moon itself disapproves of your power");
    }

    @Override
    public boolean canExecute(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        BlockPos pos = BlockPos.containing(player.getEyePosition());
        return level.canSeeSky(pos);
    }
}
