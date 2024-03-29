package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasTreeTopBlock extends FurnitureHorizontalBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    private final Supplier<RegistryObject<Block>> bottom;

    public ChristmasTreeTopBlock(Properties properties, Supplier<RegistryObject<Block>> bottom)
    {
        super(properties);
        this.bottom = bottom;
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] LEAVES_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.0, 4.0, 12.0, 3.0, 12.0), Direction.EAST));
        final VoxelShape[] LEAVES_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.0, 3.0, 5.0, 11.0, 6.0, 11.0), Direction.EAST));
        final VoxelShape[] LEAVES_6 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 6.0, 6.0, 10.0, 9.0, 10.0), Direction.EAST));
        final VoxelShape[] LEAVES_7 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 9.0, 7.0, 9.0, 12.0, 9.0), Direction.EAST));
        final VoxelShape[] STAR = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 11.0, 7.5, 10.0, 15.0, 8.5), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(LEAVES_4[direction.get2DDataValue()]);
            shapes.add(LEAVES_5[direction.get2DDataValue()]);
            shapes.add(LEAVES_6[direction.get2DDataValue()]);
            shapes.add(LEAVES_7[direction.get2DDataValue()]);
            shapes.add(STAR[direction.get2DDataValue()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
        }
        return builder.build();
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player)
    {
        BlockState belowState = level.getBlockState(pos.below());
        if(belowState.getBlock() instanceof ChristmasTreeBottomBlock)
        {
            level.setBlock(pos.below(), Blocks.AIR.defaultBlockState(), 35);
            level.levelEvent(player, 2001, pos.below(), Block.getId(belowState));
        }
        super.playerWillDestroy(level, pos, state, player);
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

}