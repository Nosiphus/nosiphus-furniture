package com.nosiphus.furniture.core;

import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.item.crafting.ChoppingRecipe;
import com.nosiphus.furniture.item.crafting.CookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializer {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ChoppingRecipe>> CHOPPING = RECIPE_SERIALIZER.register("chopping",
            () -> ChoppingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CookingRecipe>> COOKING = RECIPE_SERIALIZER.register("cooking",
            () -> CookingRecipe.Serializer.INSTANCE);

}
