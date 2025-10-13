package com.mc3699.smparch.archetype.heaven;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class DisruptionAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 8;
    }

    @Override
    public Component getName() {
        return Component.literal("Disruption").withStyle(ChatFormatting.RED);
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        AABB range = player.getBoundingBox().inflate(6);
        ServerLevel serverLevel = player.serverLevel();

        List<LivingEntity> effectEntities = serverLevel.getEntitiesOfClass(LivingEntity.class, range);

        effectEntities.forEach(entity -> {
            if(!entity.is(player))
            {
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6*20, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 6*20, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 6*20, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 6*20, 2));
            }
        });
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}
