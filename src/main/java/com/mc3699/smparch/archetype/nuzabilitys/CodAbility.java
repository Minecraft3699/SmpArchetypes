package com.mc3699.smparch.archetype.nuzabilitys;

import net.mc3699.provenance.ability.foundation.AmbientAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cod;

public class CodAbility extends AmbientAbility {
    private static final int SPAWN_RADIUS = 10;

    @Override
    public void tick(ServerPlayer serverPlayer) {
        ServerLevel level = serverPlayer.serverLevel();
        RandomSource random = level.getRandom();

        for (int i = 0; i < 12; i++) {
            int dx = random.nextInt(SPAWN_RADIUS * 2 + 1) - SPAWN_RADIUS;
            int dz = random.nextInt(SPAWN_RADIUS * 2 + 1) - SPAWN_RADIUS;
            int dy = random.nextInt(3) - 1;

            BlockPos spawnPos = serverPlayer.blockPosition()
                    .offset(dx, dy, dz);

            Cod cod = new Cod(EntityType.COD, level);
            cod.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 20, 0.0F, 0.0F);

            level.addFreshEntity(cod);
        }
    }

    @Override
    public Component getName() {
        return Component.literal("COD RAIN!!!");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return serverPlayer.level().isRaining();
    }
}
