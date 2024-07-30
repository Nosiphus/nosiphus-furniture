package com.nosiphus.furniture.item.crafting;

import com.nosiphus.furniture.core.ModBlocks;
import com.nosiphus.furniture.core.ModRecipeSerializers;
import com.nosiphus.furniture.core.ModRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class ToastingRecipe extends AbstractCookingRecipe {

    public ToastingRecipe(ResourceLocation id, String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float exp, int cookTime) {
        super(ModRecipeTypes.TOASTING.get(), id, group, category, ingredient, result, exp, cookTime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.TOASTER_LIGHT.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipeSerializers.TOASTING.get();
    }

    @Override
    public boolean isSpecial()
    {
        return true;
    }

}
