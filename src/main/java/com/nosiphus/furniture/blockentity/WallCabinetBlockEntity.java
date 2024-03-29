package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.core.ModSounds;
import com.mrcrayfish.furniture.tileentity.BasicLootBlockEntity;
import com.nosiphus.furniture.block.WallCabinetBlock;
import com.nosiphus.furniture.client.menu.WallCabinetMenu;
import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WallCabinetBlockEntity extends BasicLootBlockEntity {

    protected WallCabinetBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public WallCabinetBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.WALL_CABINET.get(), pos, state); }

    @Override
    public int getContainerSize() {
        return 9;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.nfm.wall_cabinet");
    }

    @Override
    protected AbstractContainerMenu createMenu(int ID, Inventory inventory) {
        return new WallCabinetMenu(ID, inventory, this);
    }

    @Override
    public boolean isMatchingContainerMenu(AbstractContainerMenu menu) {
        return menu instanceof WallCabinetMenu wallCabinetMenu && wallCabinetMenu.getBlockEntity() == this;
    }

    @Override
    public void onOpen(Level level, BlockPos pos, BlockState state) {
        this.playWallCabinetSound(state, ModSounds.BLOCK_CABINET_OPEN.get());
        this.setWallCabinetState(state, true);
    }

    @Override
    public void onClose(Level level, BlockPos pos, BlockState state)
    {
        this.playWallCabinetSound(state, ModSounds.BLOCK_CABINET_CLOSE.get());
        this.setWallCabinetState(state, false);
    }

    private void playWallCabinetSound(BlockState state, SoundEvent event)
    {
        Vec3i directionVec = state.getValue(WallCabinetBlock.DIRECTION).getOpposite().getNormal();
        double x = this.worldPosition.getX() + 0.5D + directionVec.getX() / 2.0D;
        double y = this.worldPosition.getY() + 0.5D + directionVec.getY() / 2.0D;
        double z = this.worldPosition.getZ() + 0.5D + directionVec.getZ() / 2.0D;
        Level level = this.getLevel();
        if(level != null)
        {
            level.playSound(null, x, y, z, event, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    private void setWallCabinetState(BlockState state, boolean open)
    {
        Level level = this.getLevel();
        if(level != null)
        {
            level.setBlock(this.getBlockPos(), state.setValue(WallCabinetBlock.OPEN, open), 3);
        }
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
