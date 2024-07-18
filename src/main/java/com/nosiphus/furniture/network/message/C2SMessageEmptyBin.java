package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.inventory.container.BinMenu;
import com.nosiphus.furniture.network.play.ServerPlayHandler;
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
    public void encode(C2SMessageEmptyBin message, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public C2SMessageEmptyBin decode(FriendlyByteBuf buffer) {
        return new C2SMessageEmptyBin(buffer.readBlockPos());
    }

    @Override
    public void handle(C2SMessageEmptyBin message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> IMessage.callServerConsumer(message, supplier, ServerPlayHandler::handleEmptyBinMessage));
        supplier.get().setPacketHandled(true);
    }

    public BlockPos getPos() {
        return this.pos;
    }
}