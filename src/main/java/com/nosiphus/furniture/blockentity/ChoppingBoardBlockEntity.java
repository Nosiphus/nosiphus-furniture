package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ChoppingBoardBlockEntity extends BlockEntity implements WorldlyContainer {

    private final NonNullList<ItemStack> choppingBoard = NonNullList.withSize(1, ItemStack.EMPTY);

    protected ChoppingBoardBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
    }

    public NonNullList<ItemStack> getChoppingBoard() {
        return this.choppingBoard;
    }

    public boolean addItem(ItemStack stack, int position) {
        if(this.choppingBoard.get(position).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.choppingBoard.set(position, copy);
            this.resetPosition(position);

            Level level = this.getLevel();
            if(level != null) {
                level.playSound(null, this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 1.0, this.worldPosition.getZ() + 0.5, ModSounds.BLOCK_GRILL_PLACE.get(), SoundSource.BLOCKS, 0.75F, level.random.nextFloat() * 0.2F + 0.9F);
            }

        }
    }

}
