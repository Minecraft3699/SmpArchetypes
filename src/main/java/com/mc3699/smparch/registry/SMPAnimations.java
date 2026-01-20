package com.mc3699.smparch.registry;

import com.mc3699.smparch.SMPArch;
import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranim.api.PlayerAnimationAccess;
import com.zigythebird.playeranim.api.PlayerAnimationFactory;
import com.zigythebird.playeranimcore.enums.PlayState;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class SMPAnimations {
    public static final ResourceLocation ANIMATION_LAYER = ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "animation_layer");

    //Start Of Resourcelocations
    public static final ResourceLocation OPEN_TERMINAL =
            ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "open_terminal");

    public static final ResourceLocation PRESS_TERMINAL =
            ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "press_terminal");

    public static final ResourceLocation CLOSE_TERMINAL =
            ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "close_terminal");

    public static final ResourceLocation QUICK_CAST =
            ResourceLocation.fromNamespaceAndPath(SMPArch.MODID, "quick_cast");

    // Follow this format with your ANIM names
    //See FireballAbility For an example on how to play an animation

    //End of Resourcelocations
    public static void registerAnimationLayer() {
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                ANIMATION_LAYER,
                1500,
                player -> new PlayerAnimationController(player,
                        (controller, state, animSetter) -> PlayState.STOP
                )
        );
    }

    public static void playAnimation(AbstractClientPlayer player, ResourceLocation animationId) {
        PlayerAnimationController controller = (PlayerAnimationController)
                PlayerAnimationAccess.getPlayerAnimationLayer(player, ANIMATION_LAYER);

        if (controller != null) {
            controller.triggerAnimation(animationId);
        }
    }
}