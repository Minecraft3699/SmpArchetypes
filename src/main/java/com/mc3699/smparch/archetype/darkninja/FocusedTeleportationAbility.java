package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;

public class FocusedTeleportationAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 2;
    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public Component getName() {
        return Component.literal("Focused Teleportation");
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/item/ender_eye.png");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 target = eyePos.add(look.scale(22));
        ServerLevel level = player.serverLevel();

        BlockHitResult hitResult = player.serverLevel().clip(new ClipContext(
            eyePos, target,
            ClipContext.Block.COLLIDER,
            ClipContext.Fluid.NONE,
            player
        ));

        if (hitResult.getType() == BlockHitResult.Type.MISS) {
            ProvenanceDataHandler.changeAP(player, 2);
        } else {
            Vec3 endPos = hitResult.getLocation().subtract(look.scale(0.5));
            EntityTeleportEvent.EnderEntity event = EventHooks.onEnderTeleport(player,endPos.x,endPos.y,endPos.z);
            level.gameEvent(GameEvent.TELEPORT, event.getTarget(), GameEvent.Context.of(player));
            player.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
            player.hurt(level.damageSources().fall(),5);
            level.playSound(null, event.getTargetX(), event.getTargetY(), event.getTargetZ(), SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
            player.resetFallDistance();
        }
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}