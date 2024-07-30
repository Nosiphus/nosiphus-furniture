package com.nosiphus.furniture.blockentity;

import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class StereoBlockEntity extends BlockEntity {

    public int count;

    public StereoBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STEREO.get(), pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.count = compound.getInt("count");
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        compound.putInt("count", count);
        super.saveAdditional(compound);
    }

    @Override
    public CompoundTag getUpdateTag()
    {
        return this.saveWithFullMetadata();
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
    {
        CompoundTag compound = pkt.getTag();
        this.load(compound);
    }

    public void sync() {
        BlockEntityUtil.sendUpdatePacket(this);
    }

}
