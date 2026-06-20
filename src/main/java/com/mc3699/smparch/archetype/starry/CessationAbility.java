package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;


public class CessationAbility extends BaseAbility {

    boolean ignoreHit = false;

    @Override
    public float getUseCost() {
        return 4f;
    }

    @Override
    public Component getName() {
        return Component.literal("Cessation").withStyle(ChatFormatting.DARK_PURPLE);
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    public void execute(ServerPlayer player) {
        super.execute(player);

        ignoreHit = true;
    }


    public static void spawnParticles(ServerLevel level, Vec3 pos) {
        level.sendParticles(
                ParticleTypes.EXPLOSION_EMITTER,
                pos.x, pos.y, pos.z,
                1,  // count
                0, 0, 0,  // offsets
                1f  // speed
        );
    }


    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "textures/ability_icon/cessation.png");
    }

    @Override
    public int getCooldown() { return 20 * 40; }

}
