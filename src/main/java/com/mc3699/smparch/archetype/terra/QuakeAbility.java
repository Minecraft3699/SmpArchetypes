package com.mc3699.smparch.archetype.terra;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.mc3699.provenance.util.ProvScheduler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ShriekParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge.EXPLOSION_DAMAGE_CALCULATOR;

public class QuakeAbility extends BaseAbility {


    private static final int waitTime = 3 * 20;
    private static final float range = 6;

    float oldSpeed = 0;

    @Override
    public float getUseCost() {
        return 4f;
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Level level = player.serverLevel();



        Vec3 pos = player.position();
        ((ServerLevel) level).sendParticles(
                new ShriekParticleOption(waitTime-10),
                pos.x, pos.y-1, pos.z,
                30,  // count
                range, 0, range,  // offsets
                2f  // speed
        );

        oldSpeed = player.getAbilities().getWalkingSpeed();
        player.getAbilities().setWalkingSpeed(0f);
        player.onUpdateAbilities();

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.PLAYERS);

        ProvScheduler.schedule(waitTime, () -> quake(player));

    }

    void quake(ServerPlayer plr) {
        AABB box = plr.getBoundingBox().inflate(range, range/2, range);
        Level level = plr.serverLevel();

        var pos = plr.position();
        var entities = level.getEntitiesOfClass(LivingEntity.class, box);
        entities.remove(plr);

        if(entities.isEmpty()) {

            ((ServerLevel) level).sendParticles(
                    ParticleTypes.FLASH,
                    pos.x, pos.y-1, pos.z,
                    10,  // count
                    1, 0, 1,  // offsets
                    0f  // speed
            );

        }

        level.playSound(null, pos.x,pos.y,pos.z, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS);

        for(LivingEntity e : entities) {

                e.hurt(e.damageSources().explosion(level.explode(plr,
                        null,
                        EXPLOSION_DAMAGE_CALCULATOR,
                        e.getX(), e.getY(), e.getZ(),
                        2.0F,
                        false,
                        Level.ExplosionInteraction.TRIGGER,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        SoundEvents.WIND_CHARGE_BURST)), 3f);
                e.addDeltaMovement(new Vec3(0, 1, 0));


        }
        plr.getAbilities().setWalkingSpeed(oldSpeed);
        plr.onUpdateAbilities();
    }


    @Override
    public Component getName() {
        return Component.literal("Quake").withStyle(ChatFormatting.GOLD);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public int getCooldown() {
        return  120 * 20;
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "textures/ability_icon/quake.png");
    }
}
