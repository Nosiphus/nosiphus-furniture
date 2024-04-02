package com.nosiphus.furniture.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class MicrowaveSlot extends Slot {

    public MicrowaveSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

}
