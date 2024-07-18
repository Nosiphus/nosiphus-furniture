package com.nosiphus.furniture.network.play;

import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.inventory.container.DishwasherMenu;
import com.nosiphus.furniture.network.message.S2CMessageFluidSync;
import net.minecraft.client.Minecraft;

public class ClientPlayHandler {

    public static void handleFluidSyncMessage(S2CMessageFluidSync message) {
        Minecraft minecraft = Minecraft.getInstance();
        if(minecraft.level.getBlockEntity(message.getPos()) instanceof DishwasherBlockEntity dishwasher) {
            dishwasher.setFluid(message.getFluidStack());
            dishwasher.setWashing(message.getWashing());
            if (minecraft.player.containerMenu instanceof DishwasherMenu menu && menu.getBlockEntity().getBlockPos().equals(message.getPos())) {
                menu.setFluid(message.getFluidStack());
                menu.setWashing(message.getWashing());
            }
        }
    }

}
