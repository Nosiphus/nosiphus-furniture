package com.nosiphus.furniture.inventory;

import com.nosiphus.furniture.core.ModItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SoapyWaterSlot extends Slot {

    public SoapyWaterSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(stack == null) return false;
        return stack.getItem() == ModItems.SOAPY_WATER_BUCKET.get() || stack.getItem() == ModItems.SUPER_SOAPY_WATER_BUCKET.get();
    }

}