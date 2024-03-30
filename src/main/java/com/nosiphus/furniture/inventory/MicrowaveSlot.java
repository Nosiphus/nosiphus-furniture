package com.nosiphus.furniture.inventory;

import com.nosiphus.furniture.blockentity.MicrowaveBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MicrowaveSlot extends Slot {

    private MicrowaveBlockEntity microwave;

    public MicrowaveSlot(MicrowaveBlockEntity microwave, int index, int x, int y) {
        super(microwave, index, x, y);
        this.microwave = microwave;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return !microwave.isCooking();
    }

    @Override
    public boolean mayPickup(Player player) {
        return !microwave.isCooking();
    }
}
