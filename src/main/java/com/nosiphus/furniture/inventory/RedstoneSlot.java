package com.nosiphus.furniture.inventory;

import com.nosiphus.furniture.blockentity.MicrowaveBlockEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RedstoneSlot extends Slot {

    public RedstoneSlot(MicrowaveBlockEntity blockEntity, int index, int x, int y) {
        super(blockEntity, index, x, y);
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack == null) return false;
        return stack.getItem() == Items.REDSTONE_BLOCK;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
