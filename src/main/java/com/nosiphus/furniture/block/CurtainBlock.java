package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CurtainBlock extends FurnitureHorizontalBlock
{
    public static final BooleanProperty CLOSED = BooleanProperty.create("closed");
    public static final BooleanProperty LEFT_CLOSED = BooleanProperty.create("left_closed");
    public static final BooleanProperty LEFT_EXISTS = BooleanProperty.create("left_exists");
    public static final BooleanProperty RIGHT_CLOSED = BooleanProperty.create("right_closed");
    public static final BooleanProperty RIGHT_EXISTS = BooleanProperty.create("right_exists");

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CurtainBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.SOUTH).setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] ROD = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 14.0, 0.0, 16.0, 16.0, 16.0), Direction.EAST));
        final VoxelShape[] CURTAIN_AREA_OPEN = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(15.0, 2.0, 0.0, 16.0, 14.0, 16.0), Direction.EAST));
        final VoxelShape[] CURTAIN_AREA_CLOSED = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(15.0, 1.0, 0.0, 16.0, 14.0, 16.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state: states)
        {
            boolean closed = state.getValue(CLOSED);
            Direction direction = state.getValue(DIRECTION);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(ROD[direction.get2DDataValue()]);
            if(closed) {
                shapes.add(CURTAIN_AREA_CLOSED[direction.get2DDataValue()]);
            } else {
                shapes.add(CURTAIN_AREA_OPEN[direction.get2DDataValue()]);
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
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos)
    {
        return SHAPES.get(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockState state = super.getStateForPlacement(context);
        return this.getCurtainState(state, context.getLevel(), context.getClickedPos(), state.getValue(DIRECTION));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult result)
    {
        if(state.getValue(CLOSED)) {
            level.setBlock(pos, state.setValue(CLOSED, false), 2);
        } else {
            level.setBlock(pos, state.setValue(CLOSED, true), 2);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos)
    {
        return this.getCurtainState(state, level, pos, state.getValue(DIRECTION));
    }

    private BlockState getCurtainState(BlockState state, LevelAccessor level, BlockPos pos, Direction dir)
    {
        boolean leftClosed = false;
        boolean leftExists = false;
        boolean rightClosed = false;
        boolean rightExists = false;

        boolean closed = state.getValue(CLOSED);

        BlockState leftState = level.getBlockState(pos.relative(dir.getCounterClockWise(Direction.Axis.Y)));
        if(leftState.getBlock() instanceof CurtainBlock) {
            leftExists = true;
            if(leftState.getValue(CLOSED)) {
                leftClosed = true;
            }
        }

        BlockState rightState = level.getBlockState(pos.relative(dir.getClockWise(Direction.Axis.Y)));
        if(rightState.getBlock() instanceof CurtainBlock) {
            rightExists = true;
            if(rightState.getValue(CLOSED)) {
                rightClosed = true;
            }
        }

        if(closed && leftClosed && leftExists && rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (!closed && leftClosed && leftExists && rightClosed && rightExists) {
            //middle with both neighbors closed
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (closed && !leftClosed && leftExists && rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (!closed && !leftClosed && leftExists && rightClosed && rightExists) {
            //middle with right neighbor closed
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (closed && leftClosed && leftExists && !rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (!closed && leftClosed && leftExists && !rightClosed && rightExists) {
            //middle with left neighbor closed
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (closed && !leftClosed && leftExists && !rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (!closed && !leftClosed && leftExists && !rightClosed && rightExists) {
            //middle with both neighbors open
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (closed && !leftClosed && !leftExists && rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (!closed && !leftClosed && !leftExists && rightClosed && rightExists) {
            //left with right neighbor closed
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, true).setValue(RIGHT_EXISTS, true);
        } else if (closed && !leftClosed && !leftExists && !rightClosed && rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (!closed && !leftClosed && !leftExists && !rightClosed && rightExists) {
            //left with right neighbor open
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, true);
        } else if (closed && leftClosed && leftExists && !rightClosed && !rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        } else if (!closed && leftClosed && leftExists && !rightClosed && !rightExists) {
            //right with left neighbor closed
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, true).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        } else if (closed && !leftClosed && leftExists && !rightClosed && !rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        } else if (!closed && !leftClosed && leftExists && !rightClosed && !rightExists) {
            //right with left neighbor open
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, true).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        } else if (closed && !leftClosed && !leftExists && !rightClosed && !rightExists) {
            //closed
            return state.setValue(CLOSED, true).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        } else if (!closed && !leftClosed && !leftExists && !rightClosed && !rightExists) {
            //single open
            return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
        }
        return state.setValue(CLOSED, false).setValue(LEFT_CLOSED, false).setValue(LEFT_EXISTS, false).setValue(RIGHT_CLOSED, false).setValue(RIGHT_EXISTS, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(CLOSED);
        builder.add(LEFT_CLOSED);
        builder.add(LEFT_EXISTS);
        builder.add(RIGHT_CLOSED);
        builder.add(RIGHT_EXISTS);
    }

}