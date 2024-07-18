package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.network.play.ClientPlayHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CMessageDishwasherSync implements IMessage<S2CMessageDishwasherSync> {

    private FluidStack fluidStack;
    private BlockPos pos;
    private boolean washing;

    public S2CMessageDishwasherSync() {}

    public S2CMessageDishwasherSync(FluidStack fluidStack, BlockPos pos, boolean washing) {
        this.fluidStack = fluidStack;
        this.pos = pos;
        this.washing = washing;
    }

    @Override
    public void encode(S2CMessageDishwasherSync message, FriendlyByteBuf buffer) {
        buffer.writeFluidStack(message.fluidStack);
        buffer.writeBlockPos(message.pos);
        buffer.writeBoolean(message.washing);
    }

    @Override
    public S2CMessageDishwasherSync decode(FriendlyByteBuf buffer) {
        return new S2CMessageDishwasherSync(buffer.readFluidStack(), buffer.readBlockPos(), buffer.readBoolean());
    }

    @Override
    public void handle(S2CMessageDishwasherSync message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> ClientPlayHandler.handleDishwasherSyncMessage(message));
        supplier.get().setPacketHandled(true);
    }

    public FluidStack getFluidStack() {
        return this.fluidStack;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public boolean getWashing() {
        return this.washing;
    }

}