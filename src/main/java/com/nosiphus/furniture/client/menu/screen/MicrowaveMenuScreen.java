package com.nosiphus.furniture.client.menu.screen;

import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MicrowaveMenuScreen extends AbstractFurnaceScreen<MicrowaveMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");

    public MicrowaveMenuScreen(MicrowaveMenu menu, Inventory inventory, Component component) {
        super(menu, new SmeltingRecipeBookComponent(), inventory, component, TEXTURE);
    }

    @Override
    public void init() {
        super.init();
    }

}
