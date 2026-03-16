package com.mc3699.smparch.network;

import com.mc3699.smparch.SMPArch;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PlayAnimationPacket(ResourceLocation animationId) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PlayAnimationPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "play_animation"));

    public static final StreamCodec<ByteBuf, PlayAnimationPacket> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            PlayAnimationPacket::animationId,
            PlayAnimationPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PlayAnimationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ClientPayloadHandler.handlePlayAnimation(packet));
    }
}