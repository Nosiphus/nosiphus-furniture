package com.nosiphus.furniture.item.crafting;

import com.google.gson.JsonObject;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.core.ModBlocks;
import com.nosiphus.furniture.core.ModRecipeSerializer;
import com.nosiphus.furniture.core.ModRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class ToastingRecipe implements Recipe<Container> {

    public final Ingredient base;
    public final ItemStack result;
    private final ResourceLocation id;

    public ToastingRecipe(ResourceLocation id, Ingredient base, ItemStack result) {
        this.id = id;
        this.base = base;
        this.result = result;
    }

    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
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

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.TOASTER_LIGHT.get());
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.TOASTING.get();
    }

    public RecipeType<?> getType() {
        return ModRecipeTypes.TOASTING.get();
    }

    public boolean isIncomplete() {
        return Stream.of(this.base).anyMatch((ingredient) -> {
            return net.minecraftforge.common.ForgeHooks.hasNoElements(ingredient);
        });
    }

    public static class Serializer implements RecipeSerializer<ToastingRecipe> {

        public static final ToastingRecipe.Serializer INSTANCE = new ToastingRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "toasting");

        public ToastingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return new ToastingRecipe(resourceLocation, ingredient, itemStack);
        }

        public ToastingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient ingredient = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack itemStack = friendlyByteBuf.readItem();
            return new ToastingRecipe(resourceLocation, ingredient, itemStack);
        }

        public void toNetwork(FriendlyByteBuf friendlyByteBuf, ToastingRecipe toastingRecipe) {
            toastingRecipe.base.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(toastingRecipe.result);
        }
    }

}
