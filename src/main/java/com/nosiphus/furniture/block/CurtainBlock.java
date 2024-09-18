package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CurtainBlock extends FurnitureHorizontalBlock
{
    public static final BooleanProperty CLOSED = BooleanProperty.create("closed");
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CurtainBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.SOUTH).setValue(CLOSED, false).setValue(TYPE, Type.SINGLE_OPEN));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] ROD = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 14.0, 0.0, 16.0, 16.0, 16.0), Direction.EAST));
        final VoxelShape[] CURTAIN_AREA = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(15.0, 1.0, 0.0, 16.0, 14.0, 16.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state: states)
        {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(ROD[direction.get2DDataValue()]);
            shapes.add(CURTAIN_AREA[direction.get2DDataValue()]);
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
            level.setBlock(pos, state.setValue(CLOSED, Boolean.valueOf(false)), 2);
        } else {
            level.setBlock(pos, state.setValue(CLOSED, Boolean.valueOf(true)), 2);
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
        boolean leftClosed = this.isClosedCurtain(level, pos, dir.getCounterClockWise(), dir) || this.isClosedCurtain(level, pos, dir.getCounterClockWise(), dir.getCounterClockWise());
        boolean rightClosed = this.isClosedCurtain(level, pos, dir.getClockWise(), dir) || this.isClosedCurtain(level, pos, dir.getClockWise(), dir.getClockWise());

        boolean left = this.isCurtain(level, pos, dir.getCounterClockWise(), dir) || this.isCurtain(level, pos, dir.getCounterClockWise(), dir.getCounterClockWise());
        boolean right = this.isCurtain(level, pos, dir.getClockWise(), dir) || this.isCurtain(level, pos, dir.getClockWise(), dir.getClockWise());

        boolean closed = state.getValue(CLOSED);

        if (closed) {
            return state.setValue(TYPE, Type.CLOSED);
        } else if (leftClosed && rightClosed) {
            return state.setValue(TYPE, Type.MIDDLE_WITH_BOTH_NEIGHBORS_CLOSED);
        } else if (leftClosed) {
            return state.setValue(TYPE, Type.RIGHT_WITH_LEFT_NEIGHBOR_CLOSED);
        } else if (rightClosed) {
            return state.setValue(TYPE, Type.LEFT_WITH_RIGHT_NEIGHBOR_CLOSED);
        } else if (left && right) {
            return state.setValue(TYPE, Type.MIDDLE_WITH_BOTH_NEIGHBORS_OPEN);
        } else if (left) {
            return state.setValue(TYPE, Type.RIGHT_WITH_LEFT_NEIGHBOR_OPEN);
        } else if (right) {
            return state.setValue(TYPE, Type.LEFT_WITH_RIGHT_NEIGHBOR_OPEN);
        }
        return state.setValue(TYPE, Type.SINGLE_OPEN);

    }

    private boolean isCurtain(LevelAccessor level, BlockPos source, Direction direction, Direction targetDirection)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        if(state.getBlock() == this)
        {
            Direction curtainDirection = state.getValue(DIRECTION);
            return curtainDirection.equals(targetDirection);
        }
        return false;
    }

    private boolean isClosedCurtain(LevelAccessor level, BlockPos source, Direction direction, Direction targetDirection)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        if(state.getBlock() == this)
        {
            boolean closed = state.getValue(CLOSED);
            if(closed) {
                Direction curtainDirection = state.getValue(DIRECTION);
                return curtainDirection.equals(targetDirection);
            }
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(CLOSED);
        builder.add(TYPE);
    }

    public enum Type implements StringRepresentable
    {
        CLOSED("closed"),
        LEFT_WITH_RIGHT_NEIGHBOR_CLOSED("left_with_right_neighbor_closed"),
        LEFT_WITH_RIGHT_NEIGHBOR_OPEN("left_with_right_neighbor_open"),
        MIDDLE_WITH_BOTH_NEIGHBORS_CLOSED("middle_with_both_neighbors_closed"),
        MIDDLE_WITH_BOTH_NEIGHBORS_OPEN("middle_with_both_neighbors_open"),
        MIDDLE_WITH_LEFT_NEIGHBOR_CLOSED("middle_with_left_neighbor_closed"),
        MIDDLE_WITH_RIGHT_NEIGHBOR_CLOSED("middle_with_right_neighbor_closed"),
        RIGHT_WITH_LEFT_NEIGHBOR_CLOSED("right_with_left_neighbor_closed"),
        RIGHT_WITH_LEFT_NEIGHBOR_OPEN("right_with_left_neighbor_open"),
        SINGLE_OPEN("single_open");

        private final String id;

        Type(String id)
        {
            this.id = id;
        }

        @Override
        public String getSerializedName()
        {
            return id;
        }

        @Override
        public String toString()
        {
            return id;
        }
    }

}