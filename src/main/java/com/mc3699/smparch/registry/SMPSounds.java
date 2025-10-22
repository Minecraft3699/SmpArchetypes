package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SMPSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SMPArch.MODID);

    public static final Holder<SoundEvent> HEARTBEAT = SOUNDS.register("heartbeat", SoundEvent::createVariableRangeEvent);

    public static final Holder<SoundEvent> SLAM_FALL = SOUNDS.register("slam_fall", SoundEvent::createVariableRangeEvent);

    public static void register(IEventBus eventBus)
    {
        SOUNDS.register(eventBus);
    }

}
