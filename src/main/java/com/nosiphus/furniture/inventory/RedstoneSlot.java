package com.nosiphus.furniture.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RedstoneSlot extends Slot {

    public RedstoneSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
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
