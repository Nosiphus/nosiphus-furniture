package com.nosiphus.furniture.network.play;

import com.nosiphus.furniture.inventory.container.BinMenu;
import com.nosiphus.furniture.network.message.C2SMessageEmptyBin;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ServerPlayHandler {

    public static void handleEmptyBinMessage(ServerPlayer player, C2SMessageEmptyBin message) {
        Level level = player.level();
        if(!level.isLoaded(message.getPos())) {
            return;
        }

        if(player.containerMenu instanceof BinMenu binMenu) {
            BinMenu.emptyBin(binMenu);
        } else {
            return;
        }

    }

}
