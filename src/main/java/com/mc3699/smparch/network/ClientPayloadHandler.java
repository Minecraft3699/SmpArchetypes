package com.mc3699.smparch.network;

import com.mc3699.smparch.registry.SMPAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;

public class ClientPayloadHandler {

    public static void handlePlayAnimation(PlayAnimationPacket packet) {
        if (Minecraft.getInstance().player instanceof AbstractClientPlayer player) {
            SMPAnimations.playAnimation(player, packet.animationId());
        }
    }
}