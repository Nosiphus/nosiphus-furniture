package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.client.menu.DishwasherMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
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

public class DishwasherBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(7) {
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
    private int progress5 = 0;
    private int progress6 = 0;
    private int maxProgress = 100;

    public DishwasherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISHWASHER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DishwasherBlockEntity.this.progress1;
                    case 1 -> DishwasherBlockEntity.this.progress2;
                    case 2 -> DishwasherBlockEntity.this.progress3;
                    case 3 -> DishwasherBlockEntity.this.progress4;
                    case 4 -> DishwasherBlockEntity.this.progress5;
                    case 5 -> DishwasherBlockEntity.this.progress6;
                    case 6 -> DishwasherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DishwasherBlockEntity.this.progress1 = value;
                    case 1 -> DishwasherBlockEntity.this.progress2 = value;
                    case 2 -> DishwasherBlockEntity.this.progress3 = value;
                    case 3 -> DishwasherBlockEntity.this.progress4 = value;
                    case 4 -> DishwasherBlockEntity.this.progress5 = value;
                    case 5 -> DishwasherBlockEntity.this.progress6 = value;
                    case 6 -> DishwasherBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 7;
            }
        };
    }

    public int getContainerSize() {
        return 7;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nfm.dishwasher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new DishwasherMenu(id, inventory, this, this.data);
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
        tag.putInt("dishwasher.progress1", this.progress1);
        tag.putInt("dishwasher.progress2", this.progress2);
        tag.putInt("dishwasher.progress3", this.progress3);
        tag.putInt("dishwasher.progress4", this.progress4);
        tag.putInt("dishwasher.progress5", this.progress5);
        tag.putInt("dishwasher.progress6", this.progress6);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        progress1 = tag.getInt("dishwasher.progress1");
        progress2 = tag.getInt("dishwasher.progress2");
        progress3 = tag.getInt("dishwasher.progress3");
        progress4 = tag.getInt("dishwasher.progress4");
        progress5 = tag.getInt("dishwasher.progress5");
        progress6 = tag.getInt("dishwasher.progress6");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DishwasherBlockEntity blockEntity) {
        if(level != null) {
            if(level.isClientSide()) {
                return;
            }



        }
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

}
