package com.mc3699.smparch.generic_abilities;

import net.mc3699.provenance.ability.foundation.ToggleAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class WallClimbAbility extends ToggleAbility {
    @Override
    public float getUseCost() {
        return 0.05f;
    }

    @Override
    public Component getName() {
        return Component.literal("Wall Climbing");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return isTouchingWall(serverPlayer);
    }

    @Override
    public ResourceLocation getIcon() {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/mob_effect/jump_boost.png");
    }

    @Override
    public void tick(ServerPlayer player) {
        super.tick(player);
        double climbSpeed = 0.2D;
        if (isTouchingWall(player)) {
            if (player.getDeltaMovement().y < climbSpeed) {
                player.setDeltaMovement(player.getDeltaMovement().x, climbSpeed, player.getDeltaMovement().z);
            }
            player.fallDistance = 0;
            player.hurtMarked = true;
        }
    }
    private boolean isTouchingWall(ServerPlayer player) {
        var level = player.level();
        var pos = player.blockPosition();
        var eyeY = (int) (player.getY() + player.getEyeHeight());

        for (var dir : net.minecraft.core.Direction.Plane.HORIZONTAL) {
            var checkPos = pos.relative(dir);
            if (!level.getBlockState(checkPos).isAir() &&
                    player.getBoundingBox().inflate(0.01).intersects(
                            new net.minecraft.world.phys.AABB(checkPos)
                    )) {
                return true;
            }
        }
        return false;
    }

}
