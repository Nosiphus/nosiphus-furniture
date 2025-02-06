package com.nosiphus.furniture.network.play;

import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.blockentity.WashingMachineBlockEntity;
import com.nosiphus.furniture.inventory.container.DishwasherMenu;
import com.nosiphus.furniture.inventory.container.WashingMachineMenu;
import com.nosiphus.furniture.network.message.S2CMessageDishwasherSync;
import com.nosiphus.furniture.network.message.S2CMessageWashingMachineSync;
import net.minecraft.client.Minecraft;

public class ClientPlayHandler {

    public static void handleDishwasherSyncMessage(S2CMessageDishwasherSync message) {
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

    public static void handleWashingMachineSyncMessage(S2CMessageWashingMachineSync message) {
        Minecraft minecraft = Minecraft.getInstance();
        if(minecraft.level.getBlockEntity(message.getPos()) instanceof WashingMachineBlockEntity washingMachine) {
            washingMachine.setFluid(message.getFluidStack());
            washingMachine.setWashing(message.getWashing());
            if (minecraft.player.containerMenu instanceof WashingMachineMenu menu && menu.getBlockEntity().getBlockPos().equals(message.getPos())) {
                menu.setFluid(message.getFluidStack());
                menu.setWashing(message.getWashing());
            }
        }
    }

}
