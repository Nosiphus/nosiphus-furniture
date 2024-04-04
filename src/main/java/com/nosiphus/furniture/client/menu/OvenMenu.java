package com.nosiphus.furniture.client.menu;

import com.nosiphus.furniture.blockentity.OvenBlockEntity;
import com.nosiphus.furniture.core.ModMenuTypes;
import com.nosiphus.furniture.inventory.RedstoneSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class OvenMenu extends AbstractContainerMenu {

    protected final OvenBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public OvenMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(10));
    }

    public OvenMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.OVEN.get(), id);
        checkContainerSize(inventory, 10);
        blockEntity = (OvenBlockEntity) entity;
        this.level = inventory.player.level;
        this.data = data;

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new RedstoneSlot(iItemHandler, 0, 34, 106));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 53, 31));
            this.addSlot(new SlotItemHandler(iItemHandler, 2, 53, 73));
            this.addSlot(new SlotItemHandler(iItemHandler, 3, 71, 31));
            this.addSlot(new SlotItemHandler(iItemHandler, 4, 71, 73));
            this.addSlot(new SlotItemHandler(iItemHandler, 5, 89, 31));
            this.addSlot(new SlotItemHandler(iItemHandler, 6, 89, 73));
            this.addSlot(new SlotItemHandler(iItemHandler, 7, 107, 31));
            this.addSlot(new SlotItemHandler(iItemHandler, 8, 107, 73));
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

        addDataSlots(data);
    }

    public boolean isCooking1() {
        return data.get(0) > 0;
    }

    public boolean isCooking2() {
        return data.get(1) > 0;
    }

    public boolean isCooking3() {
        return data.get(2) > 0;
    }

    public boolean isCooking4() {
        return data.get(3) > 0;
    }

    public int getScaledProgress1() {
        int progress1 = this.data.get(0);
        int maxProgress = this.data.get(4);
        int progressFireSize = 14;
        return maxProgress != 0 && progress1 != 0 ? progress1 * progressFireSize / maxProgress : 0;
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
