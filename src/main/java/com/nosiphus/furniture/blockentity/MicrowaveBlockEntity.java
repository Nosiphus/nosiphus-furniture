package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.tileentity.BasicLootBlockEntity;
import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MicrowaveBlockEntity extends BasicLootBlockEntity {

    private static Random random = new Random();
    private boolean cooking = false;
    public int progress = 0;
    private int timer = 0;

    protected MicrowaveBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MicrowaveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MICROWAVE.get(), pos, state);
    }

    @Override
    public int getContainerSize() { return 1; }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.nfm.microwave");
    }

    @Override
    protected AbstractContainerMenu createMenu(int ID, Inventory inventory) {return new MicrowaveMenu(ID, inventory, this);}

    @Override
    public boolean isMatchingContainerMenu(AbstractContainerMenu menu) {
        return menu instanceof MicrowaveMenu microwaveMenu && microwaveMenu.getBlockEntity() == this;
    }

    @Override
    public void onOpen(Level level, BlockPos pos, BlockState state) {
        //this.playBinSound(state, ModSounds.BLOCK_BIN_OPEN.get());
        //this.setBinState(state, true);
    }

    @Override
    public void onClose(Level level, BlockPos pos, BlockState state)
    {
        //this.playBinSound(state, ModSounds.BLOCK_BIN_CLOSE.get());
        //this.setBinState(state, false);
    }

    public void startCooking() {
        this.cooking = true;
    }

    public void stopCooking() {
        this.cooking = false;
        this.progress = 0;
    }

    public boolean isCooking() {
        return cooking;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MicrowaveBlockEntity entity) {

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

}
