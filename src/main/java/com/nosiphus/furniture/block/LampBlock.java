package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureBlock;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class LampBlock extends FurnitureBlock
{
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public LampBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TYPE, Type.SINGLE).setValue(LIT, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state: states)
        {
            Type type = state.getValue(TYPE);
            List<VoxelShape> shapes = new ArrayList<>();
            switch(type)
            {
                case SINGLE:
                    shapes.add(Block.box(4.0, 0.0, 4.0, 12.0, 5.0, 12.0));
                    shapes.add(Block.box(7.0, 9.5, 7.0, 9.0, 12.5, 9.0));
                    shapes.add(Block.box(1.5, 8.0, 1.5, 14.5, 15.0, 14.5));
                    shapes.add(Block.box(7.2, 5.0, 7.2, 8.8, 9.6, 8.8));
                    break;
                case TOP:
                    shapes.add(Block.box(7.0, 9.5, 7.0, 9.0, 12.5, 9.0));
                    shapes.add(Block.box(1.5, 8.0, 1.5, 14.5, 15.0, 14.5));
                    shapes.add(Block.box(7.2, 0.0, 7.2, 8.8, 9.6, 8.8));
                    break;
                case MIDDLE:
                    shapes.add(Block.box(7.2, 0.0, 7.2, 8.8, 16.0, 8.8));
                    break;
                case BOTTOM:
                    shapes.add(Block.box(4.0, 0.0, 4.0, 12.0, 2.0, 12.0));
                    shapes.add(Block.box(7.2, 2.0, 7.2, 8.8, 16.0, 8.8));
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult result)
    {
        if(level.getBlockState(pos.above()).getBlock() instanceof LampBlock) {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
        } else {
            if(state.getValue(LIT)) {
                level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
            } else {
                level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 2);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos)
    {
        return this.getLampState(state, level, pos);
    }

    private BlockState getLampState(BlockState state, LevelAccessor level, BlockPos pos)
    {
        boolean top = false;
        BlockState upState = level.getBlockState(pos.above());
        if(upState.getBlock() instanceof LampBlock) {
            top = true;
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
        }

        boolean bottom = false;
        BlockState downState = level.getBlockState(pos.below());
        if(downState.getBlock() instanceof LampBlock) {
            bottom = true;
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
        }

        if(top && bottom)
        {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
            return state.setValue(TYPE, Type.MIDDLE);
        }
        else if(bottom)
        {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
            return state.setValue(TYPE, Type.TOP);
        }
        else if(top)
        {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
            return state.setValue(TYPE, Type.BOTTOM);
        }
        level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
        return state.setValue(TYPE, Type.SINGLE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE);
        builder.add(LIT);
    }

    public enum Type implements StringRepresentable
    {
        SINGLE("single"),
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

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