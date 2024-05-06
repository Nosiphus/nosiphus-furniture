package com.nosiphus.furniture.recipe;

import com.nosiphus.furniture.core.ModBlocks;
import com.nosiphus.furniture.core.ModRecipeSerializer;
import com.nosiphus.furniture.core.ModRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ChoppingRecipe extends AbstractCookingRecipe {

    public ChoppingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookTime) {
        super(ModRecipeTypes.CHOPPING.get(), id, group, ingredient, result, experience, cookTime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CHOPPING_BOARD_OAK.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CHOPPING.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}