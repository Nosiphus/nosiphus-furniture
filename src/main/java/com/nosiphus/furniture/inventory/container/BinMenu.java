package com.nosiphus.furniture.inventory.container;

import com.nosiphus.furniture.blockentity.BinBlockEntity;
import com.nosiphus.furniture.core.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class BinMenu extends AbstractContainerMenu {

    protected final BinBlockEntity blockEntity;

    public BinMenu(int ID, Inventory inventory, BinBlockEntity blockEntity) {
        super(ModMenuTypes.BIN.get(), ID);
        this.blockEntity = blockEntity;
        blockEntity.startOpen(inventory.player);

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.addSlot(new Slot(blockEntity, j + i * 3, j * 18 + 62, i * 18 + 18));
            }
        }

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 115));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(inventory, i, i * 18 + 8, 173));
        }

    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.stillValid(player);
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
    public void removed(Player player) {
        super.removed(player);
        this.blockEntity.stopOpen(player);
    }

    public BinBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public static void emptyBin(BinMenu binMenu) {
        binMenu.getSlot(0).set(ItemStack.EMPTY);
        binMenu.getSlot(1).set(ItemStack.EMPTY);
        binMenu.getSlot(2).set(ItemStack.EMPTY);
        binMenu.getSlot(3).set(ItemStack.EMPTY);
        binMenu.getSlot(4).set(ItemStack.EMPTY);
        binMenu.getSlot(5).set(ItemStack.EMPTY);
        binMenu.getSlot(6).set(ItemStack.EMPTY);
        binMenu.getSlot(7).set(ItemStack.EMPTY);
        binMenu.getSlot(8).set(ItemStack.EMPTY);
        binMenu.getSlot(9).set(ItemStack.EMPTY);
        binMenu.getSlot(10).set(ItemStack.EMPTY);
        binMenu.getSlot(11).set(ItemStack.EMPTY);
    }

}