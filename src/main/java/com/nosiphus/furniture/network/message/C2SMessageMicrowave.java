package com.nosiphus.furniture.network.message;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.block.MicrowaveBlock;
import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SMessageMicrowave implements IMessage<C2SMessageMicrowave> {

    private BlockPos pos;
    private BlockState state;

    public C2SMessageMicrowave() {}

    public C2SMessageMicrowave(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void encode(C2SMessageMicrowave c2SMessageMicrowave, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(c2SMessageMicrowave.pos).writeBoolean(state.getValue(MicrowaveBlock.COOKING));
    }

    @Override
    public C2SMessageMicrowave decode(FriendlyByteBuf friendlyByteBuf) {
        return new C2SMessageMicrowave(friendlyByteBuf.readBlockPos());
    }

    @Override
    public void handle(C2SMessageMicrowave c2SMessageMicrowave, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() ->
        {
            Level level = supplier.get().getSender().getLevel();
            ServerPlayer player = supplier.get().getSender();

            if (!(player.containerMenu instanceof MicrowaveMenu microwaveMenu)) {
                return;
            }

            if (player.containerMenu instanceof MicrowaveMenu) {
                
            }

        });
        supplier.get().setPacketHandled(true);
    }

}
