package com.mc3699.smparch.archetype.eyae;

import com.mc3699.smparch.SMPArch;
import com.mc3699.smparch.registry.SMPAnimations;
import net.mc3699.provenance.ability.utils.ClientAbilityInfo;
import net.mc3699.provenance.util.ProvKeymappings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

//Shit Solution but from what i can tell prov just applies the abilitys from the arch and doesnt actually store which arch the player has
//This handles the open and close prov menu anim
@EventBusSubscriber(modid = SMPArch.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class EyaeAnimationHandler {

    private static boolean prevBarKey = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        boolean barKeyDown = ProvKeymappings.ABILITY_BAR_KEY.isDown();

        if (barKeyDown && !prevBarKey) {
            if (Minecraft.getInstance().player instanceof AbstractClientPlayer player) {
                CompoundTag data = ClientAbilityInfo.clientData;
                String abilityId = data.getString("slot_1");
                //SMPArch.LOGGER.debug(abilityId);
                if (abilityId.equals("smparch:summon_big_hands")) { //The thing which actually checks which ability is in slot one
                    SMPAnimations.playAnimation(player, SMPAnimations.OPEN_TERMINAL);
                }
            }
        } else if (!barKeyDown && prevBarKey) {
            if (Minecraft.getInstance().player instanceof AbstractClientPlayer player) {
                CompoundTag data = ClientAbilityInfo.clientData;
                String abilityId = data.getString("slot_1");
                if (abilityId.equals("smparch:summon_big_hands")) { //The thing which actually checks which ability is in slot one 2 electric boogalo
                    SMPAnimations.playAnimation(player, SMPAnimations.CLOSE_TERMINAL);
                }
            }
        }

        prevBarKey = barKeyDown;
    }
}