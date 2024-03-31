package com.nosiphus.furniture.client.menu;

import com.nosiphus.furniture.core.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.crafting.RecipeType;

public class MicrowaveMenu extends AbstractFurnaceMenu {

    public MicrowaveMenu(int ID, Inventory inventory) {
        this(ID, inventory, new SimpleContainer(3), new SimpleContainerData(4));
    }

    public MicrowaveMenu(int ID, Inventory inventory, Container container, ContainerData data) {
        super(ModMenuTypes.MICROWAVE.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, ID, inventory, container, data);
    }

}
