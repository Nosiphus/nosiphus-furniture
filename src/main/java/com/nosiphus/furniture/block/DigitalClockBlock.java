package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.DigitalClockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class DigitalClockBlock extends FurnitureHorizontalBlock implements EntityBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public DigitalClockBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.SOUTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] FRAME_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.5, 0.0, 9.0, 11.5, 0.5, 9.5), Direction.SOUTH));
        final VoxelShape[] FRAME_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.5, 4.0, 9.0, 11.5, 4.5, 9.5), Direction.SOUTH));
        final VoxelShape[] FRAME_BACK = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.5, 0.0, 7.0, 11.5, 4.3, 9.0), Direction.SOUTH));
        final VoxelShape[] FRAME_LEFT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.5, 9.0, 4.5, 4.0, 9.5), Direction.SOUTH));
        final VoxelShape[] FRAME_RIGHT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(11.5, 0.5, 9.0, 12.0, 4.0, 9.5), Direction.SOUTH));
        final VoxelShape[] FRAME_BACK_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.2, 0.3, 7.0, 4.5, 4.0, 9.0), Direction.SOUTH));
        final VoxelShape[] FRAME_BACK_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(11.5, 0.3, 7.0, 11.8, 4.0, 9.0), Direction.SOUTH));
        final VoxelShape[] BUTTON_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.0, 4.3, 8.0, 5.4, 4.5, 8.4), Direction.SOUTH));
        final VoxelShape[] BUTTON_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 4.3, 8.0, 6.4, 4.5, 8.4), Direction.SOUTH));
        final VoxelShape[] BUTTON_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 4.3, 8.0, 7.4, 4.5, 8.4), Direction.SOUTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(FRAME_BOTTOM[direction.get2DDataValue()]);
            shapes.add(FRAME_TOP[direction.get2DDataValue()]);
            shapes.add(FRAME_BACK[direction.get2DDataValue()]);
            shapes.add(FRAME_LEFT[direction.get2DDataValue()]);
            shapes.add(FRAME_RIGHT[direction.get2DDataValue()]);
            shapes.add(FRAME_BACK_2[direction.get2DDataValue()]);
            shapes.add(FRAME_BACK_3[direction.get2DDataValue()]);
            shapes.add(BUTTON_1[direction.get2DDataValue()]);
            shapes.add(BUTTON_2[direction.get2DDataValue()]);
            shapes.add(BUTTON_3[direction.get2DDataValue()]);

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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DigitalClockBlockEntity(pos, state);
    }

}