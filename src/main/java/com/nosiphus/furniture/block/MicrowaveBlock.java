package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.MicrowaveBlockEntity;
import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.mrcrayfish.furniture.block.FurnitureHorizontalBlock.DIRECTION;

public class MicrowaveBlock extends AbstractFurnaceBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public MicrowaveBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MicrowaveBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, ModBlockEntities.MICROWAVE.get());
    }

    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof MicrowaveBlockEntity) {
            player.openMenu((MenuProvider) blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] TOP_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 7.0, 2.0, 13.0, 8.0, 12.0), Direction.WEST));
        final VoxelShape[] LEFT_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.0, 1.0, 13.0, 8.0, 2.0), Direction.WEST));
        final VoxelShape[] BACK_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.0, 0.0, 2.0, 13.0, 8.0, 12.0), Direction.WEST));
        final VoxelShape[] RIGHT_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.0, 12.0, 13.0, 8.0, 15.0), Direction.WEST));
        final VoxelShape[] BOTTOM_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.0, 2.0, 13.0, 1.0, 12.0), Direction.WEST));
        final VoxelShape[] TOP_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 6.0, 2.0, 4.0, 7.0, 11.0), Direction.WEST));
        final VoxelShape[] LEFT_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 2.0, 2.0, 4.0, 6.0, 3.0), Direction.WEST));
        final VoxelShape[] RIGHT_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 2.0, 10.0, 4.0, 6.0, 11.0), Direction.WEST));
        final VoxelShape[] BOTTOM_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 1.0, 2.0, 4.0, 2.0, 11.0), Direction.WEST));
        final VoxelShape[] TOP_COVER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 7.0, 1.0, 4.0, 8.0, 12.0), Direction.WEST));
        final VoxelShape[] LEFT_COVER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 1.0, 1.0, 4.0, 7.0, 2.0), Direction.WEST));
        final VoxelShape[] RIGHT_COVER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 1.0, 11.0, 4.0, 7.0, 12.0), Direction.WEST));
        final VoxelShape[] BOTTOM_COVER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 0.0, 1.0, 4.0, 1.0, 12.0), Direction.WEST));
        final VoxelShape[] CONTROLS = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 0.0, 12.5, 4.0, 8.0, 15.0), Direction.WEST));
        final VoxelShape[] GLASS = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.5, 2.0, 3.0, 3.6, 6.0, 10.0), Direction.WEST));
        final VoxelShape[] PLATE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.25, 1.0, 3.0, 10.0, 2.0, 10.25), Direction.WEST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(TOP_SIDE[direction.get2DDataValue()]);
            shapes.add(LEFT_SIDE[direction.get2DDataValue()]);
            shapes.add(BACK_SIDE[direction.get2DDataValue()]);
            shapes.add(RIGHT_SIDE[direction.get2DDataValue()]);
            shapes.add(BOTTOM_SIDE[direction.get2DDataValue()]);
            shapes.add(TOP_FRAME[direction.get2DDataValue()]);
            shapes.add(LEFT_FRAME[direction.get2DDataValue()]);
            shapes.add(RIGHT_FRAME[direction.get2DDataValue()]);
            shapes.add(BOTTOM_FRAME[direction.get2DDataValue()]);
            shapes.add(TOP_COVER[direction.get2DDataValue()]);
            shapes.add(LEFT_COVER[direction.get2DDataValue()]);
            shapes.add(RIGHT_COVER[direction.get2DDataValue()]);
            shapes.add(BOTTOM_COVER[direction.get2DDataValue()]);
            shapes.add(CONTROLS[direction.get2DDataValue()]);
            shapes.add(GLASS[direction.get2DDataValue()]);
            shapes.add(PLATE[direction.get2DDataValue()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return SHAPES.get(state);
    }

}