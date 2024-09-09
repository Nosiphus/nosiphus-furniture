package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.entity.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class BarStoolBlock extends FurnitureBlock {

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public BarStoolBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any());
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states) {
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(Block.box(3.5, 12.0, 3.5, 12.5, 12.5, 12.5));
            shapes.add(Block.box(3.0, 9.0, 3.0, 13.0, 12.0, 13.0));
            shapes.add(Block.box(4.0, 0.0, 4.0, 6.0, 9.0, 6.0));
            shapes.add(Block.box(10.0, 0.0, 4.0, 12.0, 9.0, 6.0));
            shapes.add(Block.box(4.0, 0.0, 10.0, 6.0, 9.0, 12.0));
            shapes.add(Block.box(10.0, 0.0, 10.0, 12.0, 9.0, 12.0));
            shapes.add(Block.box(4.5, 4.0, 6.0, 5.5, 5.0, 10.0));
            shapes.add(Block.box(10.5, 4.0, 6.0, 11.5, 5.0, 10.0));
            shapes.add(Block.box(6.0, 4.0, 10.5, 10.0, 5.0, 11.5));
            shapes.add(Block.box(6.0, 4.0, 4.5, 10.0, 5.0, 5.5));
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return SHAPES.get(state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        ItemStack stack = player.getItemInHand(hand);
        return SeatEntity.create(level, pos, 0.4, player);
    }

}
