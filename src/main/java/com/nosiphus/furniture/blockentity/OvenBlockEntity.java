package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.inventory.container.OvenMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.item.crafting.CookingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class OvenBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress1 = 0;
    private int progress2 = 0;
    private int progress3 = 0;
    private int progress4 = 0;
    private int maxProgress = 100;

    public OvenBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OVEN.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> OvenBlockEntity.this.progress1;
                    case 1 -> OvenBlockEntity.this.progress2;
                    case 2 -> OvenBlockEntity.this.progress3;
                    case 3 -> OvenBlockEntity.this.progress4;
                    case 4 -> OvenBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> OvenBlockEntity.this.progress1 = value;
                    case 1 -> OvenBlockEntity.this.progress2 = value;
                    case 2 -> OvenBlockEntity.this.progress3 = value;
                    case 3 -> OvenBlockEntity.this.progress4 = value;
                    case 4 -> OvenBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    public int getContainerSize() {
        return 10;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nfm.oven");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new OvenMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
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

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("oven.progress1", this.progress1);
        tag.putInt("oven.progress2", this.progress2);
        tag.putInt("oven.progress3", this.progress3);
        tag.putInt("oven.progress4", this.progress4);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        progress1 = tag.getInt("oven.progress1");
        progress2 = tag.getInt("oven.progress2");
        progress3 = tag.getInt("oven.progress3");
        progress4 = tag.getInt("oven.progress4");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, OvenBlockEntity blockEntity) {
        if(level != null) {
            if(level.isClientSide()) {
                return;
            }

            if(hasRecipe(blockEntity, 1, 2)) {
                blockEntity.progress1++;
                setChanged(level, pos, state);
                if(blockEntity.progress1 >= blockEntity.maxProgress) {
                    craftItem(blockEntity, 1, 2);
                }
            } else {
                blockEntity.resetProgress(1);
                setChanged(level, pos, state);
            }

            if(hasRecipe(blockEntity, 3, 4)) {
                blockEntity.progress2++;
                setChanged(level, pos, state);
                if(blockEntity.progress2 >= blockEntity.maxProgress) {
                    craftItem(blockEntity, 3, 4);
                }
            } else {
                blockEntity.resetProgress(3);
                setChanged(level, pos, state);
            }

            if(hasRecipe(blockEntity, 5, 6)) {
                blockEntity.progress3++;
                setChanged(level, pos, state);
                if(blockEntity.progress3 >= blockEntity.maxProgress) {
                    craftItem(blockEntity, 5, 6);
                }
            } else {
                blockEntity.resetProgress(5);
                setChanged(level, pos, state);
            }

            if(hasRecipe(blockEntity, 7, 8)) {
                blockEntity.progress4++;
                setChanged(level, pos, state);
                if(blockEntity.progress4 >= blockEntity.maxProgress) {
                    craftItem(blockEntity, 7, 8);
                }
            } else {
                blockEntity.resetProgress(7);
                setChanged(level, pos, state);
            }
        }
    }

    private void resetProgress(int inputSlot) {
        if (inputSlot == 1) {
            this.progress1 = 0;
        } else if (inputSlot == 3) {
            this.progress2 = 0;
        } else if (inputSlot == 5) {
            this.progress3 = 0;
        } else if (inputSlot == 7) {
            this.progress4 = 0;
        }
    }

    private static void craftItem(OvenBlockEntity blockEntity, int inputSlot, int outputSlot) {
        Level level = blockEntity.level;
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
        inventory.setItem(1, blockEntity.itemHandler.getStackInSlot(inputSlot));
        inventory.setItem(2, blockEntity.itemHandler.getStackInSlot(outputSlot));
        Optional<CookingRecipe> recipe = level.getRecipeManager().getRecipeFor(CookingRecipe.Type.INSTANCE, inventory, level);
        if (hasRecipe(blockEntity, inputSlot, outputSlot)) {
            blockEntity.itemHandler.extractItem(inputSlot, 1, false);
            blockEntity.itemHandler.setStackInSlot(outputSlot, new ItemStack(recipe.get().getResultItem(null).getItem(),
                    blockEntity.itemHandler.getStackInSlot(outputSlot).getCount() + 1));
            blockEntity.resetProgress(inputSlot);
        }
    }

    private static boolean hasRecipe(OvenBlockEntity blockEntity, int inputSlot, int outputSlot) {
        Level level = blockEntity.level;
        SimpleContainer inventory = new SimpleContainer(blockEntity.itemHandler.getSlots());
        inventory.setItem(1, blockEntity.itemHandler.getStackInSlot(inputSlot));
        inventory.setItem(2, blockEntity.itemHandler.getStackInSlot(outputSlot));
        Optional<CookingRecipe> recipe = level.getRecipeManager().getRecipeFor(CookingRecipe.Type.INSTANCE, inventory, level);
        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory, outputSlot) &&
                canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem(null));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, int outputSlot) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

}