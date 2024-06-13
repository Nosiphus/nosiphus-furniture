package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CandleBlock extends FurnitureHorizontalBlock
{
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CandleBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(LIT, true));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] LOWER_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(4.0, 0.0, 4.0, 12.0, 1.0, 12.0), Direction.EAST));
        final VoxelShape[] UPPER_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.0, 1.0, 5.0, 11.0, 2.0, 11.0), Direction.EAST));
        final VoxelShape[] CENTER_COLUMN = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 2.0, 7.0, 9.0, 13.0, 9.0), Direction.EAST));
        final VoxelShape[] CROSSBAR_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 7.0, 2.0, 9.0, 9.0, 14.0), Direction.EAST));
        final VoxelShape[] CROSSBAR_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 7.0, 7.0, 14.0, 9.0, 9.0), Direction.EAST));
        final VoxelShape[] CANDLE_1_SUPPORT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 9.0, 6.0, 5.0, 10.0, 10.0), Direction.EAST));
        final VoxelShape[] CANDLE_2_SUPPORT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 9.0, 11.0, 10.0, 10.0, 15.0), Direction.EAST));
        final VoxelShape[] CANDLE_3_SUPPORT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 9.0, 1.0, 10.0, 10.0, 5.0), Direction.EAST));
        final VoxelShape[] CANDLE_4_SUPPORT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(11.0, 9.0, 6.0, 15.0, 10.0, 10.0), Direction.EAST));
        final VoxelShape[] CANDLE_5_SUPPORT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.0, 13.0, 6.0, 10.0, 14.0, 10.0), Direction.EAST));
        final VoxelShape[] CANDLE_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.2, 10.0, 7.2, 3.7, 17.0, 8.7), Direction.EAST));
        final VoxelShape[] CANDLE_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(12.2, 10.0, 7.2, 13.7, 17.0, 8.7), Direction.EAST));
        final VoxelShape[] CANDLE_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 10.0, 12.2, 8.7, 17.0, 13.7), Direction.EAST));
        final VoxelShape[] CANDLE_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 10.0, 2.2, 8.7, 17.0, 3.7), Direction.EAST));
        final VoxelShape[] CANDLE_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 14.0, 7.2, 8.7, 21.0, 8.7), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(LOWER_BASE[direction.get2DDataValue()]);
            shapes.add(UPPER_BASE[direction.get2DDataValue()]);
            shapes.add(CENTER_COLUMN[direction.get2DDataValue()]);
            shapes.add(CROSSBAR_1[direction.get2DDataValue()]);
            shapes.add(CROSSBAR_2[direction.get2DDataValue()]);
            shapes.add(CANDLE_1_SUPPORT[direction.get2DDataValue()]);
            shapes.add(CANDLE_2_SUPPORT[direction.get2DDataValue()]);
            shapes.add(CANDLE_3_SUPPORT[direction.get2DDataValue()]);
            shapes.add(CANDLE_4_SUPPORT[direction.get2DDataValue()]);
            shapes.add(CANDLE_5_SUPPORT[direction.get2DDataValue()]);
            shapes.add(CANDLE_1[direction.get2DDataValue()]);
            shapes.add(CANDLE_2[direction.get2DDataValue()]);
            shapes.add(CANDLE_3[direction.get2DDataValue()]);
            shapes.add(CANDLE_4[direction.get2DDataValue()]);
            shapes.add(CANDLE_5[direction.get2DDataValue()]);
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
        builder.add(LIT);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {

        if(state.getValue(LIT)) {

            level.addParticle(ParticleTypes.FLAME, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.45, pos.getZ() + (7.95 * 0.0625), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (12.95 * 0.0625), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (2.95 * 0.0625), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, pos.getX() + (12.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (7.95 * 0.0625), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, pos.getX() + (2.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (7.95 * 0.0625), 0.0D, 0.0D, 0.0D);

            if(randomSource.nextBoolean()) {
                double xRand = 0.001D * (randomSource.nextInt(4) - 2);
                double zRand = 0.001D * (randomSource.nextInt(4) - 2);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.45, pos.getZ() + (7.95 * 0.0625), xRand + (randomSource.nextInt(3) - 1) * 0.01, 0.01D, zRand + (randomSource.nextInt(3) - 1) * 0.01);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (12.95 * 0.0625), xRand + (randomSource.nextInt(3) - 1) * 0.01, 0.01D, zRand + (randomSource.nextInt(3) - 1) * 0.01);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + (7.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (2.95 * 0.0625), xRand + (randomSource.nextInt(3) - 1) * 0.01, 0.01D, zRand + (randomSource.nextInt(3) - 1) * 0.01);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + (12.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (7.95 * 0.0625), xRand + (randomSource.nextInt(3) - 1) * 0.01, 0.01D, zRand + (randomSource.nextInt(3) - 1) * 0.01);
                level.addParticle(ParticleTypes.SMOKE, pos.getX() + (2.95 * 0.0625), pos.getY() + 1.2, pos.getZ() + (7.95 * 0.0625), xRand + (randomSource.nextInt(3) - 1) * 0.01, 0.01D, zRand + (randomSource.nextInt(3) - 1) * 0.01);
            }

        } else {

        }

    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 2);
        } else {
            level.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 2);
        }
        return InteractionResult.SUCCESS;
    }

}