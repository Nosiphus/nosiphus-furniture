package com.nosiphus.furniture.common;

import com.nosiphus.furniture.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface ModDamageTypes {

    ResourceKey<DamageType> ELECTRIC_FENCE = ResourceKey.create(Registries.DAMAGE_TYPE,
            new ResourceLocation(Reference.MOD_ID, "electric_fence"));

}
