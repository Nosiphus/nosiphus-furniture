package com.nosiphus.furniture.network.message;

import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.client.menu.DishwasherMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CMessageFluidSync {

    private final FluidStack fluidStack;
    private final BlockPos pos;

    public S2CMessageFluidSync(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public S2CMessageFluidSync(FriendlyByteBuf friendlyByteBuf) {
        this.fluidStack = friendlyByteBuf.readFluidStack();
        this.pos = friendlyByteBuf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeFluidStack(fluidStack);
        friendlyByteBuf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof DishwasherBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof DishwasherMenu menu &&
                menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }

            }
        });
        return true;
    }


}
