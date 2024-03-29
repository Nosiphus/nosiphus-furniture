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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class WashingMachineBlock extends FurnitureHorizontalBlock
{

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public WashingMachineBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] LEFT_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 1.0, 15.0, 16.0, 3.0), Direction.EAST));
        final VoxelShape[] RIGHT_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 13.0, 15.0, 16.0, 15.0), Direction.EAST));
        final VoxelShape[] TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 13.0, 3.0, 15.0, 16.0, 13.0), Direction.EAST));
        final VoxelShape[] BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 3.0, 15.0, 3.0, 13.0), Direction.EAST));
        final VoxelShape[] BACK = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 0.0, 3.0, 15.0, 13.0, 13.0), Direction.EAST));

        final VoxelShape[] DOOR_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 3.0, 3.0, 2.0, 5.0, 13.0), Direction.EAST));
        final VoxelShape[] DOOR_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 11.0, 3.0, 2.0, 13.0, 13.0), Direction.EAST));
        final VoxelShape[] DOOR_LEFT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 5.0, 3.0, 2.0, 11.0, 5.0), Direction.EAST));
        final VoxelShape[] DOOR_RIGHT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 5.0, 10.96, 2.0, 11.0, 13.0), Direction.EAST));
        final VoxelShape[] HANDLE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 6.0, 11.5, 1.0, 10.0, 12.5), Direction.EAST));
        final VoxelShape[] GLASS = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 5.0, 5.0, 1.6, 11.0, 11.0), Direction.EAST));

        final VoxelShape[] LEFT_BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 2.0, 2.0, 15.0, 3.0), Direction.EAST));
        final VoxelShape[] MIDDLE_BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 11.0, 2.0, 15.0, 12.0), Direction.EAST));
        final VoxelShape[] RIGHT_BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 13.0, 2.0, 15.0, 14.0), Direction.EAST));


        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(LEFT_SIDE[direction.get2DDataValue()]);
            shapes.add(RIGHT_SIDE[direction.get2DDataValue()]);
            shapes.add(TOP[direction.get2DDataValue()]);
            shapes.add(BOTTOM[direction.get2DDataValue()]);
            shapes.add(BACK[direction.get2DDataValue()]);
            shapes.add(DOOR_BOTTOM[direction.get2DDataValue()]);
            shapes.add(DOOR_TOP[direction.get2DDataValue()]);
            shapes.add(DOOR_LEFT[direction.get2DDataValue()]);
            shapes.add(DOOR_RIGHT[direction.get2DDataValue()]);
            shapes.add(HANDLE[direction.get2DDataValue()]);
            shapes.add(GLASS[direction.get2DDataValue()]);
            shapes.add(LEFT_BUTTON[direction.get2DDataValue()]);
            shapes.add(MIDDLE_BUTTON[direction.get2DDataValue()]);
            shapes.add(RIGHT_BUTTON[direction.get2DDataValue()]);
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
    }

}