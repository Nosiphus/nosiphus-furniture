package com.nosiphus.furniture.inventory.container;

import com.mojang.datafixers.util.Pair;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.blockentity.WashingMachineBlockEntity;
import com.nosiphus.furniture.core.ModFluids;
import com.nosiphus.furniture.core.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.SlotItemHandler;

public class WashingMachineMenu extends AbstractContainerMenu {

    public static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("textures/atlas/blocks.png");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_TOOL_SLOT_BUCKET = new ResourceLocation(Reference.MOD_ID, "item/empty_tool_slot_bucket");

    protected final WashingMachineBlockEntity blockEntity;
    private final Level level;
    private FluidStack fluidStack;
    private boolean washing;

    public WashingMachineMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public WashingMachineMenu(int id, Inventory inventory, BlockEntity entity) {
        super(ModMenuTypes.WASHING_MACHINE.get(), id);
        checkContainerSize(inventory, 6);
        blockEntity = (WashingMachineBlockEntity) entity;
        this.level = inventory.player.level();
        this.fluidStack = blockEntity.getFluidStack();
        this.washing = blockEntity.getWashing();

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 80, 44) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(WashingMachineMenu.BLOCK_ATLAS, WashingMachineMenu.EMPTY_ARMOR_SLOT_HELMET);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 64, 60) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(WashingMachineMenu.BLOCK_ATLAS, WashingMachineMenu.EMPTY_ARMOR_SLOT_CHESTPLATE);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 96, 60) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(WashingMachineMenu.BLOCK_ATLAS, WashingMachineMenu.EMPTY_ARMOR_SLOT_LEGGINGS);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 80, 76) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(WashingMachineMenu.BLOCK_ATLAS, WashingMachineMenu.EMPTY_ARMOR_SLOT_BOOTS);
                }
            });
            this.addSlot(new SlotItemHandler(iItemHandler, 4, 125, 7));

            this.addSlot(new SlotItemHandler(iItemHandler, 4, 125, 7) {
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(WashingMachineMenu.BLOCK_ATLAS, WashingMachineMenu.EMPTY_TOOL_SLOT_BUCKET);
                }
            });
        });

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 146));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(inventory, i, i * 18 + 8, 204));
        }

    }

    public void setFluid(FluidStack fluidStack) {
        this.fluidStack = fluidStack;
    }

    public FluidStack getFluidStack() {
        return fluidStack;
    }

    public WashingMachineBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public void setWashing(boolean washing) {
        this.washing = washing;
    }

    public boolean getWashing() {
        return washing;
    }

    public int getFluidType() {
        int fluidType = 0;
        if (getFluidStack().getFluid() == ModFluids.SOAPY_WATER.get()) {
            fluidType = 192;
        } else if (getFluidStack().getFluid() == ModFluids.SUPER_SOAPY_WATER.get()) {
            fluidType = 212;
        }
        return fluidType;
    }

    public int getFluidRenderAmount() {
        int actualAmount = getFluidStack().getAmount();
        int actualMaxAmount = 64000;
        int tankSize = 73;
        return actualAmount * tankSize / actualMaxAmount;
    }

    @Override
    public ItemStack quickMoveStack(Player playerEntity, int index)
    {
        ItemStack clickedStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            clickedStack = slotStack.copy();
            if(index < this.blockEntity.getContainerSize())
            {
                if(!this.moveItemStackTo(slotStack, this.blockEntity.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.moveItemStackTo(slotStack, 0, this.blockEntity.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }

            if(slotStack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }
        return clickedStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
    }

}
