package com.nosiphus.furniture.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ToolSlot extends SlotItemHandler {

    private int toolType;

    public ToolSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, int toolType) {
        super(itemHandler, index, xPosition, yPosition);
        this.toolType = toolType;
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack == null) return false;
        Item item = stack.getItem();
        switch(toolType) {
            case 0:
                if(item instanceof PickaxeItem) {
                    return true;
                }
                break;
            case 1:
                if(item instanceof ShovelItem) {
                    return true;
                }
                break;
            case 2:
                if(item instanceof SwordItem) {
                    return true;
                }
                break;
            case 3:
                if(item instanceof AxeItem) {
                    return true;
                }
                break;
            case 4:
                if(item instanceof HoeItem) {
                    return true;
                }
                break;
            case 5:
                return !(item instanceof PickaxeItem || item instanceof ShovelItem || item instanceof SwordItem || item instanceof AxeItem || item instanceof HoeItem);
        }
        return false;
    }

}
