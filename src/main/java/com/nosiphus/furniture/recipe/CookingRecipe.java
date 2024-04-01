package com.nosiphus.furniture.recipe;

import com.google.gson.JsonObject;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.core.ModBlocks;
import com.nosiphus.furniture.core.ModRecipeSerializer;
import com.nosiphus.furniture.core.ModRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class CookingRecipe extends AbstractCookingRecipe {

    public CookingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookingtime) {
        super(ModRecipeTypes.COOKING.get(), id, group, ingredient, result, experience, cookingtime);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.MICROWAVE_LIGHT.get());
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.COOKING.get();
    }

    public static class Serializer implements RecipeSerializer<CookingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "cooking");

        public CookingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            String group = GsonHelper.getAsString(jsonObject, "group", "");
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "item"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            float experience = GsonHelper.getAsFloat(jsonObject, "experience", 0.0F);
            int cookingtime = GsonHelper.getAsInt(jsonObject, "cookingtime", 0);
            return new CookingRecipe(resourceLocation, group, ingredient, result, experience, cookingtime);
        }

        public CookingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            String group = friendlyByteBuf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack result = friendlyByteBuf.readItem();
            float experience = friendlyByteBuf.readFloat();
            int cookingtime = friendlyByteBuf.readVarInt();
            return new CookingRecipe(resourceLocation, group, ingredient, result, experience, cookingtime);
        }

        public void toNetwork(FriendlyByteBuf friendlyByteBuf, CookingRecipe cookingRecipe) {
            friendlyByteBuf.writeUtf(cookingRecipe.group);
            cookingRecipe.ingredient.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(cookingRecipe.result);
            friendlyByteBuf.writeFloat(cookingRecipe.experience);
            friendlyByteBuf.writeVarInt(cookingRecipe.cookingTime);
        }
    }

}
