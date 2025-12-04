package com.mc3699.smparch.archetype.miku;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = SMPArch.MODID)
public class BlockEventHandler {
    @SubscribeEvent
    public static void onBlockPlace(UseItemOnBlockEvent event) {
        ItemStack item = new ItemStack(Items.SCULK_SHRIEKER);
    }

    private static boolean hasActiveAidFromBelow(Player player) {
        return ProvenanceDataHandler.getAbility(player, 8) instanceof AidFromBelowAbility;
    }
    //MAKE WARDEN-SUMMONING SKULK SHRIEKERS PLACEABLE BY PEOPLE WITH THE ARCHETYPE I BEG YOU
}
