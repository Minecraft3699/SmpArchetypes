package com.mc3699.smparch.archetype.miku;

import com.mc3699.smparch.SMPArch;
import net.mc3699.provenance.ProvenanceDataHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = SMPArch.MODID)
public class BlockEventHandler {
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getPlacedBlock().getBlock().equals(Blocks.SCULK_SHRIEKER))) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!hasActiveAidFromBelow(player)) return;
        event.getLevel().setBlock(event.getPos(),event.getPlacedBlock().setValue(BlockStateProperties.CAN_SUMMON,true),0);
    }

    private static boolean hasActiveAidFromBelow(Player player) {
        return ProvenanceDataHandler.getAbilities(player).stream().anyMatch(ability -> ability instanceof AidFromBelowAbility);
    }
}
