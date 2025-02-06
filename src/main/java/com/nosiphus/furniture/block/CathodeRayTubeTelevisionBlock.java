package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CathodeRayTubeTelevisionBlock extends FurnitureHorizontalBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CathodeRayTubeTelevisionBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] BORDER_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 0.0, 1.5, 9.5, 1.5, 14.5), Direction.EAST));
        final VoxelShape[] BORDER_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 11.5, 1.5, 9.5, 13.0, 14.5), Direction.EAST));
        final VoxelShape[] BORDER_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 1.5, 1.5, 9.5, 11.5, 3.0), Direction.EAST));
        final VoxelShape[] BORDER_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 1.5, 13.0, 9.5, 11.5, 14.5), Direction.EAST));
        final VoxelShape[] SCREEN = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 1.6, 3.0, 6.8, 11.6, 13.0), Direction.EAST));
        final VoxelShape[] BACK = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(8.0, 0.5, 2.5, 14.4, 12.2, 13.5), Direction.EAST));
        final VoxelShape[] ANTENNA_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.5, 13.0, 6.0, 5.5, 13.5, 10.0), Direction.EAST));
        final VoxelShape[] POWER_BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.1, 0.4, 12.0, 1.5, 1.2, 12.8), Direction.EAST));
        final VoxelShape[] BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.1, 0.4, 10.8, 1.5, 1.2, 11.6), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BORDER_1[direction.get2DDataValue()]);
            shapes.add(BORDER_2[direction.get2DDataValue()]);
            shapes.add(BORDER_3[direction.get2DDataValue()]);
            shapes.add(BORDER_4[direction.get2DDataValue()]);
            shapes.add(SCREEN[direction.get2DDataValue()]);
            shapes.add(BACK[direction.get2DDataValue()]);
            shapes.add(ANTENNA_BASE[direction.get2DDataValue()]);
            shapes.add(POWER_BUTTON[direction.get2DDataValue()]);
            shapes.add(BUTTON[direction.get2DDataValue()]);

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