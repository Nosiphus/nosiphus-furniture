package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModFluids;
import com.nosiphus.furniture.inventory.container.DishwasherMenu;
import com.nosiphus.furniture.network.PacketHandler;
import com.nosiphus.furniture.network.message.S2CMessageFluidSync;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DishwasherBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch(slot) {
                case 0 -> stack.getItem() instanceof PickaxeItem;
                case 1 -> stack.getItem() instanceof ShovelItem;
                case 2 -> stack.getItem() instanceof SwordItem;
                case 3 -> stack.getItem() instanceof AxeItem;
                case 4 -> stack.getItem() instanceof HoeItem;
                case 5 -> stack.getItem() instanceof ShieldItem;
                case 6 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                default -> super.isItemValid(slot, stack);
            };
        }

    };

    private final FluidTank FLUID_TANK = new FluidTank(64000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide()) {
                PacketHandler.sendToClients(new S2CMessageFluidSync(this.fluid, worldPosition));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == ModFluids.SOAPY_WATER.get() || stack.getFluid() == ModFluids.SUPER_SOAPY_WATER.get();
        }
    };

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
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
        return 8;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nfm.dishwasher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        PacketHandler.sendToClients(new S2CMessageFluidSync(this.getFluidStack(), worldPosition));
        return new DishwasherMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return lazyFluidHandler.cast();
        }
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidHandler.invalidate();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag = FLUID_TANK.writeToNBT(tag);
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
        FLUID_TANK.readFromNBT(tag);
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
        if(level.isClientSide()) {
            return;
        }

        if(hasRepairableItem(blockEntity, 0)) {

            repairItem(blockEntity, 0);
            setChanged(level, pos, state);
        }

        if(hasRepairableItem(blockEntity, 1)) {
            repairItem(blockEntity, 1);
            setChanged(level, pos, state);
        }

        if(hasRepairableItem(blockEntity, 2)) {
            repairItem(blockEntity, 2);
            setChanged(level, pos, state);
        }

        if(hasRepairableItem(blockEntity, 3)) {
            repairItem(blockEntity, 3);
            setChanged(level, pos, state);
        }

        if(hasRepairableItem(blockEntity, 4)) {
            repairItem(blockEntity, 4);
            setChanged(level, pos, state);
        }

        if(hasRepairableItem(blockEntity, 5)) {
            repairItem(blockEntity, 5);
            setChanged(level, pos, state);
        }

        if(hasFluidItemInSourceSlot(blockEntity)) {
            transferItemFluidToFluidTank(blockEntity);
        }

    }

    private static void repairItem(DishwasherBlockEntity blockEntity, int slot) {
        if(hasRepairableItem(blockEntity, slot)) {
            ItemStack itemInSlot = blockEntity.itemHandler.getStackInSlot(slot);
            itemInSlot.setDamageValue(itemInSlot.getDamageValue() - 1);
        }
    }

    private static boolean hasRepairableItem(DishwasherBlockEntity blockEntity, int slot) {
        boolean isDamaged;
        if (blockEntity.itemHandler.getStackInSlot(slot).getDamageValue() > 0) {
            isDamaged = true;
        } else {
            isDamaged = false;
        }
        return isDamaged && hasCorrectFluidAmountInTank(blockEntity);
    }

    private static boolean hasCorrectFluidAmountInTank(DishwasherBlockEntity blockEntity) {
        return blockEntity.FLUID_TANK.getFluidAmount() > 0;
    }

    private static void transferItemFluidToFluidTank(DishwasherBlockEntity blockEntity) {
        blockEntity.itemHandler.getStackInSlot(6).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(blockEntity.FLUID_TANK.getSpace(), 1000);
            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(blockEntity.FLUID_TANK.isFluidValid(stack)) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithFluid(blockEntity, stack, handler.getContainer());
            }
        });
    }

    private static void fillTankWithFluid(DishwasherBlockEntity blockEntity, FluidStack stack, ItemStack container) {
        blockEntity.FLUID_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        blockEntity.itemHandler.extractItem(6, 1, false);
        blockEntity.itemHandler.insertItem(6, container, false);
    }

    private static boolean hasFluidItemInSourceSlot(DishwasherBlockEntity blockEntity) {
        return blockEntity.itemHandler.getStackInSlot(6).getCount() > 0;
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

}