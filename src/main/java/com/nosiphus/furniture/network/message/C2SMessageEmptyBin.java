package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.network.play.ServerPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SMessageEmptyBin implements IMessage<C2SMessageEmptyBin> {

    public C2SMessageEmptyBin() {}

    public void encode(C2SMessageEmptyBin message, FriendlyByteBuf buffer) {}

    public C2SMessageEmptyBin decode(FriendlyByteBuf buffer) {
        return new C2SMessageEmptyBin();
    }

    public void handle(C2SMessageEmptyBin message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> IMessage.callServerConsumer(message, supplier, ServerPlayHandler::handleEmptyBinMessage));
        supplier.get().setPacketHandled(true);
    }

}
