package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.inventory.container.BinMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SMessageEmptyBin implements IMessage<C2SMessageEmptyBin> {

    private BlockPos pos;

    public C2SMessageEmptyBin() {}

    public C2SMessageEmptyBin(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encode(C2SMessageEmptyBin c2SMessageEmptyBin, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(c2SMessageEmptyBin.pos);
    }

    @Override
    public C2SMessageEmptyBin decode(FriendlyByteBuf friendlyByteBuf) {
        return new C2SMessageEmptyBin(friendlyByteBuf.readBlockPos());
    }

    @Override
    public void handle(C2SMessageEmptyBin c2SMessageEmptyBin, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() ->
        {
            Level level = supplier.get().getSender().level();
            ServerPlayer player = supplier.get().getSender();

            if (!(player.containerMenu instanceof BinMenu binMenu)) {
                return;
            }

            if (player.containerMenu instanceof BinMenu) {
                binMenu.emptyBin(binMenu);
            }

        });
        supplier.get().setPacketHandled(true);
    }
}