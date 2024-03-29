package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.ShowerHeadBlockEntity;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ShowerHeadBlock extends FurnitureHorizontalBlock implements EntityBlock
{

    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public ShowerHeadBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(ACTIVATED, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] TOP_PIPE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 5.6, 7.2, 16.0, 7.2, 8.8), Direction.EAST));
        final VoxelShape[] CENTER_PIPE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 4.0, 7.2, 8.8, 5.6, 8.8), Direction.EAST));
        final VoxelShape[] MAIN_WATER_OUTPUT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.6, 2.4, 5.6, 10.4, 4.0, 10.4), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(TOP_PIPE[direction.get2DDataValue()]);
            shapes.add(CENTER_PIPE[direction.get2DDataValue()]);
            shapes.add(MAIN_WATER_OUTPUT[direction.get2DDataValue()]);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVATED);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(state.getValue(ACTIVATED)) {
            level.setBlock(pos, state.setValue(ACTIVATED, Boolean.valueOf(false)), 2);
            level.playSound(null,pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.6F);
        } else {
            level.setBlock(pos, state.setValue(ACTIVATED, Boolean.valueOf(true)), 2);
            level.playSound(null,pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.5F);
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShowerHeadBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.SHOWER_HEAD.get(), ShowerHeadBlockEntity::tick);
    }

}