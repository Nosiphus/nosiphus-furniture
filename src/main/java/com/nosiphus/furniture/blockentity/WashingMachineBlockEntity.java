package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModFluids;
import com.nosiphus.furniture.inventory.container.WashingMachineMenu;
import com.nosiphus.furniture.network.PacketHandler;
import com.nosiphus.furniture.network.message.S2CMessageWashingMachineSync;
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
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class WashingMachineBlockEntity extends BlockEntity implements MenuProvider {

    private boolean washing = false;

    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch(slot) {
                case 0 -> stack.getItem() instanceof ArmorItem;
                case 1 -> stack.getItem() instanceof ArmorItem;
                case 2 -> stack.getItem() instanceof ArmorItem;
                case 3 -> stack.getItem() instanceof ArmorItem;
                case 4 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                default -> super.isItemValid(slot, stack);
            };
        }

    };

    private final FluidTank FLUID_TANK = new FluidTank(64000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) {
                PacketHandler.getPlayChannel().send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new S2CMessageWashingMachineSync(this.fluid, worldPosition, washing));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            if (FLUID_TANK.isEmpty()) {
                return stack.getFluid() == ModFluids.SOAPY_WATER.get() || stack.getFluid() == ModFluids.SUPER_SOAPY_WATER.get();
            } else if (FLUID_TANK.getFluid().getFluid() == ModFluids.SOAPY_WATER.get()) {
                return stack.getFluid() == ModFluids.SOAPY_WATER.get();
            } else if (FLUID_TANK.getFluid().getFluid() == ModFluids.SUPER_SOAPY_WATER.get()) {
                return stack.getFluid() == ModFluids.SUPER_SOAPY_WATER.get();
            }
            return false;
        }
    };

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }

    public void setWashing(boolean washing) {
        this.washing = washing;
    }

    public boolean getWashing() {
        return this.washing;
    }

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public WashingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WASHING_MACHINE.get(), pos, state);
    }

    public int getContainerSize() {
        return 6;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nfm.washing_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        PacketHandler.getPlayChannel().send(PacketDistributor.TRACKING_CHUNK.with(() -> this.level.getChunkAt(this.worldPosition)), new S2CMessageWashingMachineSync(this.FLUID_TANK.getFluid(), this.worldPosition, this.washing));
        return new WashingMachineMenu(id, inventory, this);
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
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        FLUID_TANK.readFromNBT(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WashingMachineBlockEntity blockEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasFluidItemInSourceSlot(blockEntity)) {
            transferItemFluidToFluidTank(blockEntity);
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

    }

    private static void repairItem(WashingMachineBlockEntity blockEntity, int slot) {
        ItemStack itemInSlot = blockEntity.itemHandler.getStackInSlot(slot);
        itemInSlot.setDamageValue(itemInSlot.getDamageValue() - 1);
        if(itemInSlot.getDamageValue() == 0) {
            blockEntity.washing = false;
        }
        if(blockEntity.getFluidStack().getFluid() == ModFluids.SOAPY_WATER.get()) {
            blockEntity.FLUID_TANK.drain(20, IFluidHandler.FluidAction.EXECUTE);
            if(blockEntity.getFluidStack().getAmount() < 20) {
                blockEntity.washing = false;
            }
        }
        if(blockEntity.getFluidStack().getFluid() == ModFluids.SUPER_SOAPY_WATER.get()) {
            blockEntity.FLUID_TANK.drain(10, IFluidHandler.FluidAction.EXECUTE);
            if(blockEntity.getFluidStack().getAmount() < 10) {
                blockEntity.washing = false;
            }
        }
        if(blockEntity.getFluidStack().isEmpty()) {
            blockEntity.washing = false;
        }
    }

    private static boolean hasRepairableItem(WashingMachineBlockEntity blockEntity, int slot) {
        boolean isDamaged;
        if (blockEntity.itemHandler.getStackInSlot(slot).getDamageValue() > 0) {
            isDamaged = true;
            blockEntity.washing = true;
        } else {
            isDamaged = false;
            blockEntity.washing = false;
        }
        return isDamaged && hasCorrectFluidAmountInTank(blockEntity);
    }

    private static boolean hasCorrectFluidAmountInTank(WashingMachineBlockEntity blockEntity) {
        return blockEntity.FLUID_TANK.getFluidAmount() > 0;
    }

    private static void transferItemFluidToFluidTank(WashingMachineBlockEntity blockEntity) {
        blockEntity.itemHandler.getStackInSlot(4).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(blockEntity.FLUID_TANK.getSpace(), 1000);
            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(blockEntity.FLUID_TANK.isFluidValid(stack)) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithFluid(blockEntity, stack, handler.getContainer());
            }
        });
    }

    private static void fillTankWithFluid(WashingMachineBlockEntity blockEntity, FluidStack stack, ItemStack container) {
        blockEntity.FLUID_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        blockEntity.itemHandler.extractItem(4, 1, false);
        blockEntity.itemHandler.insertItem(4, container, false);
    }

    private static boolean hasFluidItemInSourceSlot(WashingMachineBlockEntity blockEntity) {
        return blockEntity.itemHandler.getStackInSlot(4).getCount() > 0;
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

}