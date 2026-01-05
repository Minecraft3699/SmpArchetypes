package com.mc3699.smparch.archetype.miku;

import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.*;


public class WardenStrengthAbility extends BaseAbility {
    public static final Map<Player, Float> NEXT_ATTACK_BONUS = new WeakHashMap<>();
    @Override
    public float getUseCost() {
        return 1.5f;
    }

    @Override
    public int getCooldown() { return 10*40; }

    @Override
    public Component getName() {
        return Component.literal("Warden Strength");
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);

        NEXT_ATTACK_BONUS.put(player, 10f);
    }
    
    @Override
    public void backgroundTick(ServerPlayer serverPlayer) {
        super.backgroundTick(serverPlayer);
        int cooldown = ProvenanceDataHandler.getCooldown(serverPlayer,getSlots(serverPlayer,new WardenStrengthAbility())[0]);
        
        if (!(ProvenanceDataHandler.isOnCooldown(serverPlayer,getSlots(serverPlayer,new WardenStrengthAbility())[0]))) return;
        if (NEXT_ATTACK_BONUS.get(serverPlayer) == null) return;
        if (cooldown > 1) {
            serverPlayer.displayClientMessage(Component.literal("Warden Strength is active! " + cooldown).withStyle(ChatFormatting.ITALIC, ChatFormatting.AQUA), true);
        } else if (cooldown == 1) {
            NEXT_ATTACK_BONUS.remove(serverPlayer);
            serverPlayer.displayClientMessage(Component.literal("Warden Strength has worn off. (Cooldown over)").withStyle(ChatFormatting.ITALIC, ChatFormatting.AQUA), true);
        }
    }
    
    public static int[] getSlots(ServerPlayer player, BaseAbility ability) {
        int[] slots = new int[8];
        for (int sl = 1; sl < 8; sl++) {
            if (ProvenanceDataHandler.getAbility(player,sl) != null)
                if (ProvenanceDataHandler.getAbility(player,sl).getClass().equals(ability.getClass())) slots[sl] = sl;
        }
        return Arrays.stream(slots).filter(slot -> slot > 0).toArray();
    }
    
    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/block/sculk_catalyst_top.png");
    }
}