package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.entity.SeatEntity;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class ModernSofaBlock extends FurnitureHorizontalBlock
{
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public ModernSofaBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(TYPE, Type.SINGLE));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] BACKBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 5.0, 1.0, 14.0, 19.0, 2.0), Direction.SOUTH));
        final VoxelShape[] LEFT_BACKBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 5.0, 1.0, 16.0, 19.0, 2.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_BACKBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 5.0, 1.0, 2.0, 19.0, 2.0), Direction.SOUTH));
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 5.0, 2.0, 14.0, 9.0, 15.0), Direction.SOUTH));
        final VoxelShape[] LEFT_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 5.0, 2.0, 16.0, 9.0, 15.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 5.0, 2.0, 2.0, 9.0, 15.0), Direction.SOUTH));
        final VoxelShape[] UNDERBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 3.0, 1.0, 14.0, 5.0, 15.0), Direction.SOUTH));
        final VoxelShape[] LEFT_UNDERBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 3.0, 1.0, 16.0, 5.0, 15.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_UNDERBASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 3.0, 1.0, 2.0, 5.0, 15.0), Direction.SOUTH));
        final VoxelShape[] BACKREST = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 9.0, 2.0, 14.0, 19.0, 5.0), Direction.SOUTH));
        final VoxelShape[] LEFT_BACKREST = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 9.0, 2.0, 16.0, 19.0, 5.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_BACKREST = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 9.0, 2.0, 2.0, 19.0, 5.0), Direction.SOUTH));
        final VoxelShape[] LEFT_ARM_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 11.0, 1.0, 2.0, 13.0, 15.0), Direction.SOUTH));
        final VoxelShape[] LEFT_ARM_FRONT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 2.0, 13.0, 2.0, 11.0, 15.0), Direction.SOUTH));
        final VoxelShape[] LEFT_ARM_BACK = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 2.0, 1.0, 2.0, 11.0, 3.0), Direction.SOUTH));
        final VoxelShape[] LEFT_ARM_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.0, 0.0, 1.0, 2.0, 2.0, 15.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_ARM_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 11.0, 1.0, 16.0, 13.0, 15.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_ARM_FRONT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 2.0, 13.0, 16.0, 11.0, 15.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_ARM_BACK = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 2.0, 1.0, 16.0, 11.0, 3.0), Direction.SOUTH));
        final VoxelShape[] RIGHT_ARM_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 0.0, 1.0, 16.0, 2.0, 15.0), Direction.SOUTH));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state: states)
        {
            Direction direction = state.getValue(DIRECTION);
            Type type = state.getValue(TYPE);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BASE[direction.get2DDataValue()]);
            shapes.add(BACKREST[direction.get2DDataValue()]);
            shapes.add(BACKBASE[direction.get2DDataValue()]);
            shapes.add(UNDERBASE[direction.get2DDataValue()]);
            switch(type)
            {
                case SINGLE:
                    shapes.add(LEFT_ARM_TOP[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_FRONT[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_BACK[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_BOTTOM[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_TOP[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_FRONT[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_BACK[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_BOTTOM[direction.get2DDataValue()]);
                    break;
                case LEFT:
                    shapes.add(LEFT_ARM_TOP[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_FRONT[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_BACK[direction.get2DDataValue()]);
                    shapes.add(LEFT_ARM_BOTTOM[direction.get2DDataValue()]);
                    shapes.add(LEFT_BACKREST[direction.get2DDataValue()]);
                    shapes.add(LEFT_BASE[direction.get2DDataValue()]);
                    shapes.add(LEFT_BACKBASE[direction.get2DDataValue()]);
                    shapes.add(LEFT_UNDERBASE[direction.get2DDataValue()]);
                    break;
                case RIGHT:
                    shapes.add(RIGHT_ARM_TOP[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_FRONT[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_BACK[direction.get2DDataValue()]);
                    shapes.add(RIGHT_ARM_BOTTOM[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BACKREST[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BASE[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BACKBASE[direction.get2DDataValue()]);
                    shapes.add(RIGHT_UNDERBASE[direction.get2DDataValue()]);
                    break;
                case MIDDLE:
                    shapes.add(LEFT_BACKREST[direction.get2DDataValue()]);
                    shapes.add(LEFT_BASE[direction.get2DDataValue()]);
                    shapes.add(LEFT_BACKBASE[direction.get2DDataValue()]);
                    shapes.add(LEFT_UNDERBASE[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BACKREST[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BASE[direction.get2DDataValue()]);
                    shapes.add(RIGHT_BACKBASE[direction.get2DDataValue()]);
                    shapes.add(RIGHT_UNDERBASE[direction.get2DDataValue()]);
                    break;

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
        return this.getSofaState(state, context.getLevel(), context.getClickedPos(), state.getValue(DIRECTION));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult result)
    {
        if(!level.isClientSide())
        {
            ItemStack stack = playerEntity.getItemInHand(hand);
            return SeatEntity.create(level, pos, 0.4, playerEntity, state.getValue(DIRECTION));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos)
    {
        return this.getSofaState(state, level, pos, state.getValue(DIRECTION));
    }

    private BlockState getSofaState(BlockState state, LevelAccessor level, BlockPos pos, Direction dir)
    {
        boolean left = this.isSofa(level, pos, dir.getCounterClockWise(), dir) || this.isSofa(level, pos, dir.getCounterClockWise(), dir.getCounterClockWise());
        boolean right = this.isSofa(level, pos, dir.getClockWise(), dir) || this.isSofa(level, pos, dir.getClockWise(), dir.getClockWise());

        if(left && right)
        {
            return state.setValue(TYPE, Type.MIDDLE);
        }
        else if(left)
        {
            return state.setValue(TYPE, Type.RIGHT);
        }
        else if(right)
        {
            return state.setValue(TYPE, Type.LEFT);
        }
        return state.setValue(TYPE, Type.SINGLE);
    }

    private boolean isSofa(LevelAccessor level, BlockPos source, Direction direction, Direction targetDirection)
    {
        BlockState state = level.getBlockState(source.relative(direction));
        if(state.getBlock() == this)
        {
            Direction sofaDirection = state.getValue(DIRECTION);
            return sofaDirection.equals(targetDirection);
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE);
    }

    public enum Type implements StringRepresentable
    {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle");

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