package com.mc3699.smparch.archetype.darkninja;

import net.mc3699.provenance.ability.foundation.BaseAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ExperienceCrystallizationAbility extends BaseAbility {
    // no clue how the costs work or what is a good cost for such a thing
    @Override
    public float getUseCost() {
        return 4;
    }

    @Override
    public Component getName() {
        return Component.literal("Experience Crystallization");
    }

    @Override
    public boolean canExecute(ServerPlayer serverPlayer) {
        return true;
    }

    @Override
    public void execute(ServerPlayer player) {
        super.execute(player);

        ServerLevel serverLevel = player.serverLevel();

        AABB searchArea = new AABB(player.getBlockPosBelowThatAffectsMyMovement()).inflate(16);
        List<ExperienceOrb> orbs = serverLevel.getEntitiesOfClass(ExperienceOrb.class, searchArea);
        orbs.forEach(o -> {
            // TODO: Figure out how to spawn Experience Nuggets before destroying the xp orb
            //o.spawnAtLocation(new ItemStack(Items.EXPERIENCE_NUGGET));
            o.remove(Entity.RemovalReason.DISCARDED);
        });
    }
}

