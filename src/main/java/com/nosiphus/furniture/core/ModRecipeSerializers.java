package com.nosiphus.furniture.core;

import com.mrcrayfish.furniture.item.crafting.SimpleCookingSerializer;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.item.crafting.ChoppingRecipe;
import com.nosiphus.furniture.item.crafting.CookingRecipe;
import com.nosiphus.furniture.item.crafting.ToastingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ChoppingRecipe>> CHOPPING = RECIPE_SERIALIZER.register("chopping",
            () -> ChoppingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CookingRecipe>> COOKING = RECIPE_SERIALIZER.register("cooking",
            () -> CookingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<SimpleCookingSerializer<ToastingRecipe>> TOASTING = register("toasting", () -> new SimpleCookingSerializer<>(ToastingRecipe::new, 100));

    private static <T extends RecipeSerializer<? extends Recipe<?>>> RegistryObject<T> register(String name, Supplier<T> serializer) {
        return RECIPE_SERIALIZER.register(name, serializer);
    }

}
