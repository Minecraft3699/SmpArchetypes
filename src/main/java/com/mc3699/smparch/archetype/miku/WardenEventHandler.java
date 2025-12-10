package com.mc3699.smparch.archetype.miku;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = SMPArch.MODID)
public class WardenEventHandler {

    @SubscribeEvent
    public static void onWardenTargeting(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Warden warden)) return;

        boolean hasNearbyWandHolder = warden.level().getEntitiesOfClass(Player.class, warden.getBoundingBox().inflate(16.0))
                .stream()
                .anyMatch(WardenEventHandler::hasActiveAidFromBelow);

        if (hasNearbyWandHolder) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onWardenTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Warden warden)) return;

        if (warden.getPersistentData().getBoolean("IsFriendly")) return;

        if (warden.tickCount % 20 == 0) {
            Player holder = warden.level()
                    .getEntitiesOfClass(Player.class, warden.getBoundingBox().inflate(16.0))
                    .stream()
                    .filter(WardenEventHandler::hasActiveAidFromBelow)
                    .findFirst()
                    .orElse(null);

            if (holder != null) {
                if (warden.getTarget() == holder) {
                    warden.setTarget(null);
                    warden.getNavigation().stop();
                }
                try {
                    warden.clearAnger(holder);
                } catch (Exception ignored) {}
                warden.getPersistentData().putLong("PeacefulUntil", warden.level().getGameTime() + 100);
            }
        }

        long peacefulUntil = warden.getPersistentData().getLong("PeacefulUntil");
        if (peacefulUntil > 0 && warden.level().getGameTime() > peacefulUntil) {
            warden.getPersistentData().remove("PeacefulUntil");
        }
    }

    private static boolean hasActiveAidFromBelow(Player player) {
        return ProvenanceDataHandler.getAbilities(player).stream().anyMatch(ability -> ability instanceof AidFromBelowAbility);
    }
    @SubscribeEvent
    public static void onWardenDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Warden warden && warden.getPersistentData().getBoolean("NoDrops")) {
            event.getDrops().clear();
        }
    }

    @SubscribeEvent
    public static void onWardenXP(LivingExperienceDropEvent event) {
        if (event.getEntity() instanceof Warden warden && warden.getPersistentData().getBoolean("NoDrops")) {
            event.setCanceled(true);
        }
    }
}
