package com.nosiphus.furniture.common;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class ModDamageSources {

    private final Registry<DamageType> damageTypes;

    private final DamageSource electricFence;

    public ModDamageSources(RegistryAccess registryAccess) {
        this.damageTypes = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        this.electricFence = this.source(ModDamageTypes.ELECTRIC_FENCE);
    }

    private DamageSource source(ResourceKey<DamageType> damageTypeKey) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(damageTypeKey));
    }

    private DamageSource source(ResourceKey<DamageType> damageTypeKey, @Nullable Entity entity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(damageTypeKey), entity);
    }

    private DamageSource source(ResourceKey<DamageType> damageTypeKey, @Nullable Entity causingEntity, @Nullable Entity directEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(damageTypeKey), causingEntity, directEntity);
    }

    public DamageSource electricFence() {
        return this.electricFence;
    }

}
