package com.mc3699.smparch.archetype.starry;

import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;


public class CessationAbility extends ToggleAbility {


    @Override
    public float getUseCost() {
        return 0.0125f;
    }

    @Override
    public Component getName() {
        return Component.literal("Cessation").withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void setEnabled(ServerPlayer player, int slot, boolean enabled) {
        super.setEnabled(player, slot, enabled);


        player.setGameMode(enabled ? GameType.SPECTATOR : GameType.DEFAULT_MODE);
        player.getAbilities().setFlyingSpeed(enabled ? 0f : 0.05f);
        player.onUpdateAbilities();



        player.displayClientMessage(Component.literal(enabled ? "You entered the realm of no realm." : "You have returned.").withStyle(ChatFormatting.DARK_PURPLE), true);

        spawnParticles(player.serverLevel(),player.position().add(0,1,0));
    }



    private static void spawnParticles(ServerLevel level, Vec3 pos) {


        level.sendParticles(
                new DustParticleOptions(new Vector3f(0.3f, 0.2f, 0.4f), 4f),
                pos.x, pos.y, pos.z,
                10,  // count
                0.25, 0.5, 0.25,  // offsets
                2f  // speed
        );
    }

    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);


    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft", "textures/mob_effect/absorption.png");
    }

    @Override
    public int getCooldown() { return 10 * 20; }

}
