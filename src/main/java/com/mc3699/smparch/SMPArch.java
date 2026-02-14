package com.mc3699.smparch;

import com.mc3699.smparch.network.PlayAnimationPacket;
import com.mc3699.smparch.registry.SMPAbilities;
import com.mc3699.smparch.registry.SMPArchetypes;
import com.mc3699.smparch.registry.SMPEntities;
import com.mc3699.smparch.registry.SMPSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

@Mod(SMPArch.MODID)
public class SMPArch {
    public static final String MODID = "smparch";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SMPArch(IEventBus modEventBus, ModContainer modContainer) {
        SMPAbilities.register(modEventBus);
        SMPArchetypes.register(modEventBus);
        SMPEntities.register(modEventBus);
        SMPSounds.register(modEventBus);

        modEventBus.addListener(this::registerPackets);
    }

    public static ResourceLocation path(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }

    private void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                PlayAnimationPacket.TYPE,
                PlayAnimationPacket.STREAM_CODEC,
                PlayAnimationPacket::handle
        );
    }
}
