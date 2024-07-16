package com.nosiphus.furniture.inventory.container.slot;

import com.nosiphus.furniture.core.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SoapyWaterSlot extends SlotItemHandler {

    public SoapyWaterSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack == null) return false;
        return stack.getItem() == ModItems.SOAPY_WATER_BUCKET.get() || stack.getItem() == ModItems.SUPER_SOAPY_WATER_BUCKET.get();
    }

}