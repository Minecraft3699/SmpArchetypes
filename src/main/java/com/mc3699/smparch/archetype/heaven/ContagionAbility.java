package com.mc3699.smparch.archetype.heaven;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ContagionAbility extends BaseAbility {
    @Override
    public float getUseCost() {
        return 0;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 target = eyePos.add(look.scale(32));


    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }
}
