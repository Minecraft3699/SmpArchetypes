package com.mc3699.smparch;

import com.mc3699.smparch.registry.SMPAbilities;
import com.mc3699.smparch.registry.SMPArchetypes;
import com.mc3699.smparch.registry.SMPEntities;
import com.mc3699.smparch.registry.SMPSounds;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(SMPArch.MODID)
public class SMPArch {
    public static final String MODID = "smparch";

    public SMPArch(IEventBus modEventBus, ModContainer modContainer) {
        SMPAbilities.register(modEventBus);
        SMPArchetypes.register(modEventBus);
        SMPEntities.register(modEventBus);
        SMPSounds.register(modEventBus);
    }

    public static ResourceLocation path(String id) {
        return ResourceLocation.fromNamespaceAndPath(MODID, id);
    }

}
