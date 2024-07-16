package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.inventory.container.DishwasherMenu;
import net.minecraft.client.Minecraft;
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
    public void encode(S2CMessageFluidSync s2CMessageFluidSync, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeFluidStack(s2CMessageFluidSync.fluidStack);
        friendlyByteBuf.writeBlockPos(s2CMessageFluidSync.pos);
    }

    @Override
    public S2CMessageFluidSync decode(FriendlyByteBuf friendlyByteBuf) {
        return new S2CMessageFluidSync(friendlyByteBuf.readFluidStack(), friendlyByteBuf.readBlockPos());
    }

    @Override
    public void handle(S2CMessageFluidSync s2CMessageFluidSync, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() ->
        {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof DishwasherBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);
                if(Minecraft.getInstance().player.containerMenu instanceof DishwasherMenu menu &&
                menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        supplier.get().setPacketHandled(true);
    }

}
