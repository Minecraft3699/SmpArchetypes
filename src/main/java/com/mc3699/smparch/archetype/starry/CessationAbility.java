package com.mc3699.smparch.archetype.starry;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;


public class CessationAbility extends BaseAbility {

    // onToggle and setEnabled methods don't seem to work at all?
    // I had to make the class a BaseAbility instead and do a ton of workarounds

    float time = 0f;
    final float maxTime = 1200f; // in ticks
    boolean enabled = false;

    @Override
    public float getUseCost() {
        return 2f;
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
    public void execute(ServerPlayer player) {
        super.execute(player);
        time = maxTime;
        enabled = !ProvenanceDataHandler.isAbilityEnabled(player, SMPArch.path("cessation"));
        toggle(player, enabled);
    }


    private void toggle(ServerPlayer player, boolean enabled) {
        player.setGameMode(enabled ? GameType.SPECTATOR : GameType.DEFAULT_MODE);
        player.getAbilities().setFlyingSpeed(enabled ? 0.0045f : 0.05f);
        player.onUpdateAbilities();

        player.displayClientMessage(Component.literal(enabled ? "You have entered the realm of no realm." : "You have returned.").withStyle(ChatFormatting.DARK_PURPLE), true);

        spawnParticles(player.serverLevel(),player.position().add(0,1,0));

        ProvenanceDataHandler.setAbilityEnabled(player, SMPArch.path("cessation"), enabled);
    }

    @Override
    public void backgroundTick(ServerPlayer player) {
        super.backgroundTick(player);
        if(time>0 && enabled) time--;
        else if(enabled) {
            time = maxTime;
            enabled = false;
            ProvenanceDataHandler.setAbilityEnabled(player, SMPArch.path("cessation"), false);
        }
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
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "textures/ability_icon/cessation.png");
    }

    @Override
    public int getCooldown() { return 5 * 20; }

}
