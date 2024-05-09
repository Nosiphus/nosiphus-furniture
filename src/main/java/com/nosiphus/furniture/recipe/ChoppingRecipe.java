package com.nosiphus.furniture.recipe;

import com.google.gson.JsonObject;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.core.ModBlocks;
import com.nosiphus.furniture.core.ModRecipeSerializer;
import com.nosiphus.furniture.core.ModRecipeTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class ChoppingRecipe implements Recipe<Container> {

    public final Ingredient base;
    public final ItemStack result;
    private final ResourceLocation id;

    public ChoppingRecipe(ResourceLocation id, Ingredient base, ItemStack result) {
        this.id = id;
        this.base = base;
        this.result = result;
    }

    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0));
    }

    public ItemStack assemble(Container container) {
        ItemStack itemStack = this.result.copy();
        CompoundTag compoundTag = container.getItem(0).getTag();
        if(compoundTag != null) {
            itemStack.setTag(compoundTag.copy());
        }
        return itemStack;
    }

    public boolean canCraftInDimensions(int int1, int int2) {
        return int1 * int2 >= 2;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CHOPPING_BOARD_OAK.get());
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.CHOPPING.get();
    }

    public RecipeType<?> getType() {
        return ModRecipeTypes.CHOPPING.get();
    }

    public boolean isIncomplete() {
        return Stream.of(this.base).anyMatch((ingredient) -> {
            return net.minecraftforge.common.ForgeHooks.hasNoElements(ingredient);
        });
    }

    public static class Serializer implements RecipeSerializer<ChoppingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "chopping");

        public ChoppingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return new ChoppingRecipe(resourceLocation, ingredient, itemStack);
        }

        public ChoppingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack itemStack = friendlyByteBuf.readItem();
            return new ChoppingRecipe(resourceLocation, ingredient, itemStack);
        }

        public void toNetwork(FriendlyByteBuf friendlyByteBuf, ChoppingRecipe choppingRecipe) {
            choppingRecipe.base.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(choppingRecipe.result);
        }
    }

}