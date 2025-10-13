package com.mc3699.smparch.entity;

import com.mc3699.smparch.registry.SMPEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AbilityProjectile extends AbstractHurtingProjectile {

    public AbilityProjectile(EntityType<? extends AbilityProjectile> type, Level level) {
        super(type, level);
    }


}
