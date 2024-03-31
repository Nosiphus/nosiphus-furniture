package com.nosiphus.furniture.inventory;

import com.nosiphus.furniture.blockentity.MicrowaveBlockEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MicrowaveSlot extends Slot {

    public MicrowaveSlot(MicrowaveBlockEntity blockEntity, int index, int x, int y) {
        super(blockEntity, index, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

}
