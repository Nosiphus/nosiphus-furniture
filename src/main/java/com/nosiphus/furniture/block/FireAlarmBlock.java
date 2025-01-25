package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class FireAlarmBlock extends FurnitureHorizontalBlock
{

    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public FireAlarmBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(ACTIVATED, false).setValue(ENABLED, true));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] LED = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.4, 14.0, 6.4, 7.4, 14.4, 7.4), Direction.EAST));
        final VoxelShape[] CENTER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.08, 14.4, 6.08, 9.92, 15.0, 9.92), Direction.EAST));
        final VoxelShape[] TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.6, 15.0, 5.6, 10.4, 16.0, 10.4), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(LED[direction.get2DDataValue()]);
            shapes.add(CENTER[direction.get2DDataValue()]);
            shapes.add(TOP[direction.get2DDataValue()]);

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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVATED);
        builder.add(ENABLED);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(state.getValue(ENABLED)) {
            level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, false).setValue(ENABLED, false));
        } else {
            level.setBlockAndUpdate(pos, state.setValue(ENABLED, true));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        level.scheduleTick(pos, this, this.tickRate(state, level));

        if(state.getValue(FireAlarmBlock.ENABLED)) {
            int radius = 9;
            boolean foundFire = false;
            for (int x = 0; x < radius; x++) {
                for (int y = 0; y < radius; y++) {
                    for (int z = 0; z < radius; z++) {
                        if (level.getBlockState(pos.offset(-4 + x, -4 + y, -4 + z)).is(Blocks.FIRE)) {
                            level.playSound(null, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, ModSounds.BLOCK_FIRE_ALARM_BEEP.get(), SoundSource.BLOCKS, 5.0F, 1.0F);
                            level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, true));
                            foundFire = true;
                            break;
                        }
                    }
                    if (foundFire) break;
                }
                if (foundFire) break;
            }
        } else {
            level.playSound(null, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, ModSounds.BLOCK_FIRE_ALARM_BEEP.get(), SoundSource.BLOCKS, 5.0F, 1.0F);
            level.scheduleTick(pos, this, 34);
        }

    }

    private int tickRate(BlockState state, ServerLevel level) {
        return 34;
    }

}