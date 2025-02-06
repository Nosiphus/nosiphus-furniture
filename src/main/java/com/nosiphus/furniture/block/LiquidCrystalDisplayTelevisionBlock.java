package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class LiquidCrystalDisplayTelevisionBlock extends FurnitureHorizontalBlock
{
    public static final BooleanProperty MOUNTED = BooleanProperty.create("mounted");

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public LiquidCrystalDisplayTelevisionBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.EAST).setValue(MOUNTED, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        //Standing
        final VoxelShape[] FRAME_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 3.0, 7.5, 12.0, 4.0, 8.5), Direction.SOUTH));
        final VoxelShape[] FRAME_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.0, 3.0, 7.5, 20.0, 4.0, 8.5), Direction.SOUTH));
        final VoxelShape[] FRAME_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 16.0, 7.5, 12.0, 17.0, 8.5), Direction.SOUTH));
        final VoxelShape[] FRAME_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.0, 16.0, 7.5, 20.0, 17.0, 8.5), Direction.SOUTH));
        final VoxelShape[] FRAME_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 4.0, 7.5, -3.0, 16.0, 8.5), Direction.SOUTH));
        final VoxelShape[] FRAME_6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(19.0, 4.0, 7.5, 20.0, 16.0, 8.5), Direction.SOUTH));
        final VoxelShape[] SCREEN_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 4.0, 7.5, 13.0, 16.0, 8.3), Direction.SOUTH));
        final VoxelShape[] SCREEN_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(13.0, 4.0, 7.5, 19.0, 16.0, 8.3), Direction.SOUTH));
        final VoxelShape[] CONNECTION = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.5, 5.0, 6.7, 10.5, 7.0, 7.3), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 4.0, 6.5, 13.0, 16.0, 7.5), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(13.0, 4.0, 6.5, 19.0, 16.0, 7.5), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 16.0, 6.5, 13.0, 17.4, 7.5), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(10.0, 16.0, 6.5, 19.0, 17.4, 7.5), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 2.6, 6.5, 10.0, 4.0, 7.5), Direction.SOUTH));
        final VoxelShape[] BACK_COVER_6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(10.0, 2.6, 6.5, 19.0, 4.0, 7.5), Direction.SOUTH));
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 5.0, 14.0, 1.0, 11.0), Direction.SOUTH));
        final VoxelShape[] STAND = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 1.0, 6.8, 10.0, 4.0, 7.3), Direction.SOUTH));

        //Mounted
        final VoxelShape[] MOUNTED_FRAME_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 3.0, 2.5, 12.0, 4.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_FRAME_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.0, 3.0, 2.5, 20.0, 4.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_FRAME_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 16.0, 2.5, 12.0, 17.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_FRAME_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.0, 16.0, 2.5, 20.0, 17.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_FRAME_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-4.0, 4.0, 2.5, -3.0, 16.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_FRAME_6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(19.0, 4.0, 2.5, 20.0, 16.0, 3.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_SCREEN_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 4.0, 2.5, 13.0, 16.0, 3.3), Direction.SOUTH));
        final VoxelShape[] MOUNTED_SCREEN_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(13.0, 4.0, 2.5, 19.0, 16.0, 3.3), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 4.0, 1.5, 13.0, 16.0, 2.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(13.0, 4.0, 1.5, 19.0, 16.0, 2.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 16.0, 1.5, 10.0, 17.4, 2.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(10.0, 16.0, 1.5, 19.0, 17.4, 2.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(-3.0, 2.6, 1.5, 10.0, 4.0, 2.5), Direction.SOUTH));
        final VoxelShape[] MOUNTED_BACK_COVER_6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(10.0, 2.6, 1.5, 19.0, 4.0, 2.5), Direction.SOUTH));
        final VoxelShape[] WALL_MOUNT_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 5.0, 0.0, 1.0, 15.0, 1.5), Direction.SOUTH));
        final VoxelShape[] WALL_MOUNT_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(15.0, 5.0, 0.0, 16.0, 15.0, 1.5), Direction.SOUTH));
        final VoxelShape[] WALL_MOUNT_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 9.5, 0.3, 15.0, 10.5, 1.3), Direction.SOUTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            boolean mounted = state.getValue(MOUNTED);
            List<VoxelShape> shapes = new ArrayList<>();

            if(mounted) {
                shapes.add(MOUNTED_FRAME_1[direction.get2DDataValue()]);
                shapes.add(MOUNTED_FRAME_2[direction.get2DDataValue()]);
                shapes.add(MOUNTED_FRAME_3[direction.get2DDataValue()]);
                shapes.add(MOUNTED_FRAME_4[direction.get2DDataValue()]);
                shapes.add(MOUNTED_FRAME_5[direction.get2DDataValue()]);
                shapes.add(MOUNTED_FRAME_6[direction.get2DDataValue()]);
                shapes.add(MOUNTED_SCREEN_1[direction.get2DDataValue()]);
                shapes.add(MOUNTED_SCREEN_2[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_1[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_2[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_3[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_4[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_5[direction.get2DDataValue()]);
                shapes.add(MOUNTED_BACK_COVER_6[direction.get2DDataValue()]);
                shapes.add(WALL_MOUNT_1[direction.get2DDataValue()]);
                shapes.add(WALL_MOUNT_2[direction.get2DDataValue()]);
                shapes.add(WALL_MOUNT_3[direction.get2DDataValue()]);
            } else {
                shapes.add(FRAME_1[direction.get2DDataValue()]);
                shapes.add(FRAME_2[direction.get2DDataValue()]);
                shapes.add(FRAME_3[direction.get2DDataValue()]);
                shapes.add(FRAME_4[direction.get2DDataValue()]);
                shapes.add(FRAME_5[direction.get2DDataValue()]);
                shapes.add(FRAME_6[direction.get2DDataValue()]);
                shapes.add(SCREEN_1[direction.get2DDataValue()]);
                shapes.add(SCREEN_2[direction.get2DDataValue()]);
                shapes.add(CONNECTION[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_1[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_2[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_3[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_4[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_5[direction.get2DDataValue()]);
                shapes.add(BACK_COVER_6[direction.get2DDataValue()]);
                shapes.add(BASE[direction.get2DDataValue()]);
                shapes.add(STAND[direction.get2DDataValue()]);
            }

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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        Direction facing = context.getClickedFace();
        if(facing.getAxis().isHorizontal()) {
            state = state.setValue(MOUNTED, true);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(MOUNTED);
    }

}