package com.mc3699.smparch.generic_abilities;

import net.mc3699.provenance.ProvenanceDataHandler;
import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class InvisTestAbility extends ToggleAbility {

    @Override
    public float getUseCost() {
        return 0;
    }

    @Override
    public Component getName() {
        return Component.literal("Vanish");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void setEnabled(ServerPlayer player, int slot, boolean enabled) {
        boolean wasEnabled = ProvenanceDataHandler.isAbilityEnabled(player, slot);
        if (enabled == wasEnabled) return;

        super.setEnabled(player, slot, enabled);

        player.setInvisible(enabled);
        player.setSilent(enabled);
        player.displayClientMessage(Component.literal(enabled ? "§7You have vanished." : "§aYou are visible again."), true);

    }
}
