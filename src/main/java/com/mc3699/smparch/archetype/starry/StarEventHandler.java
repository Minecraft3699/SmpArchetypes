package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.DamageCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = SMPArch.MODID)
public class StarEventHandler
{
    @SubscribeEvent
    public static void onIncomeAttack(LivingIncomingDamageEvent event) {

        if(!(event.getEntity() instanceof Player player)) return;

        // I feel like there might be a less verbose way for the next two lines
        if(event.getSource().is(DamageTypes.IN_FIRE) ||
                event.getSource().is(DamageTypes.ON_FIRE) ||
                event.getSource().is(DamageTypes.FREEZE)) return;

        CessationAbility ability = ProvenanceDataHandler.getAbilities(player).stream()
                .filter(CessationAbility.class::isInstance)
                .map(CessationAbility.class::cast)
                .findFirst()
                .orElse(null);

        if (ability != null && ability.ignoreHit) {

            ServerLevel level = player.getServer().getLevel(player.level().dimension());
            boolean isHighDamage = event.getAmount() >= 20;

            CessationAbility.spawnParticles(level, player.blockPosition().getCenter());
            level.playSound(null, player.getX(), player.getY(), player.getZ(), Holder.direct(isHighDamage ? SoundEvents.DRAGON_FIREBALL_EXPLODE : SoundEvents.WARDEN_ATTACK_IMPACT), SoundSource.PLAYERS, 1f, 1f);

            player.displayClientMessage(Component.literal("The realm obeys. ").withStyle(ChatFormatting.DARK_PURPLE), true);

            ability.ignoreHit = false;
            if(isHighDamage) {
                // Kinda magic numbers but it looks cooler this way
                player.knockback(event.getAmount() / 60, 0, 0);
                event.setAmount(10f);
                return;
            }

            event.setCanceled(true);
        }

    }
}
