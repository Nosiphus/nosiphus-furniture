package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CupBlock extends FurnitureBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CupBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any());
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(Block.box(5.5, 0.01, 5.5, 6.0, 7.0, 10.5));
            shapes.add(Block.box(10.0, 0.01, 5.5, 10.5, 7.0, 10.5));
            shapes.add(Block.box(6.0, 0.01, 5.5, 10.0, 7.0, 6.0));
            shapes.add(Block.box(6.0, 0.01, 10.0, 10.0, 7.0, 10.5));
            shapes.add(Block.box(6.0, 0.01, 6.0, 10.0, 0.5, 10.0));
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