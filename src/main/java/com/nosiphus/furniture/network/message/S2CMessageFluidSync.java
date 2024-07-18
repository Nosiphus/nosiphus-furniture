package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.network.play.ClientPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CMessageFluidSync implements IMessage<S2CMessageFluidSync> {

    private FluidStack fluidStack;
    private BlockPos pos;

    public S2CMessageFluidSync() {}

    public S2CMessageFluidSync(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    @Override
    public void encode(S2CMessageFluidSync message, FriendlyByteBuf buffer) {
        buffer.writeFluidStack(message.fluidStack);
        buffer.writeBlockPos(message.pos);
    }

    @Override
    public S2CMessageFluidSync decode(FriendlyByteBuf buffer) {
        return new S2CMessageFluidSync(buffer.readFluidStack(), buffer.readBlockPos());
    }

    @Override
    public void handle(S2CMessageFluidSync message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> ClientPlayHandler.handleFluidSyncMessage(message));
        supplier.get().setPacketHandled(true);
    }

    public FluidStack getFluidStack() {
        return this.fluidStack;
    }

    public BlockPos getPos() {
        return this.pos;
    }

}