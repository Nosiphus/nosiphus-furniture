package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.tileentity.BasicLootBlockEntity;
import com.nosiphus.furniture.block.MicrowaveBlock;
import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModRecipeTypes;
import com.nosiphus.furniture.recipe.CookingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MicrowaveBlockEntity extends BasicLootBlockEntity implements MenuProvider {

    private RecipeType<? extends CookingRecipe> recipeType;
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private final RecipeManager.CachedCheck<Container, ? extends CookingRecipe> quickCheck;

    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    public int cookingProgress;
    public int cookingTotalTime;

    public MicrowaveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MICROWAVE.get(), pos, state);
        this.recipeType = recipeType;
        this.quickCheck = RecipeManager.createCheck((RecipeType) recipeType);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MicrowaveBlockEntity.this.cookingProgress;
                    case 1 -> MicrowaveBlockEntity.this.cookingTotalTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MicrowaveBlockEntity.this.cookingProgress = value;
                    case 1 -> MicrowaveBlockEntity.this.cookingTotalTime = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDefaultName() {
        return Component.translatable("container.nfm.microwave");
    }

    public int getContainerSize() { return 2; }

    @Override
    protected AbstractContainerMenu createMenu(int ID, Inventory inventory) {return new MicrowaveMenu(ID, inventory, this);}

    @Override
    public boolean isMatchingContainerMenu(AbstractContainerMenu menu) {
        return menu instanceof MicrowaveMenu microwaveMenu && microwaveMenu.getBlockEntity() == this;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MicrowaveBlockEntity blockEntity) {
        if (blockEntity.itemHandler.getStackInSlot(1).getItem() == Items.REDSTONE_BLOCK) {
            ItemStack itemStack = blockEntity.items.get(0);
            boolean flag = !blockEntity.items.get(0).isEmpty();
            Recipe<?> recipe;
            if (flag) {
                recipe = blockEntity.quickCheck.getRecipeFor(blockEntity, level).orElse(null);
                if (blockEntity.canBurn(recipe, blockEntity.items, 1) == true) {
                    ++blockEntity.cookingProgress;
                    if (blockEntity.cookingProgress == blockEntity.cookingTotalTime) {
                        blockEntity.cookingProgress = 0;
                        blockEntity.cookingTotalTime = getTotalCookTime(level, blockEntity);
                        ItemStack resultItem = recipe.getResultItem();
                        blockEntity.itemHandler.setStackInSlot(0, resultItem);
                    }
                }
            } else {
                blockEntity.cookingProgress = 0;
            }
        } else {
            blockEntity.cookingProgress = 0;
        }
    }

    private boolean canBurn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> itemStacks, int index) {
        if (recipe != null) {
            return true;
        } else {
            return false;
        }
    }

    private static int getTotalCookTime(Level level, MicrowaveBlockEntity blockEntity) {
        return blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(CookingRecipe::getCookingTime).orElse(200);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.items);
        itemHandler.deserializeNBT(compound.getCompound("inventory"));
        cookingProgress = compound.getInt("CookTime");
        cookingTotalTime = compound.getInt("CookTimeTotal");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(tag, this.items);
        super.saveAdditional(tag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

}
