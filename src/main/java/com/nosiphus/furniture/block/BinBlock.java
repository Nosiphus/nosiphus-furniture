package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.BinBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BinBlock extends FurnitureHorizontalBlock implements EntityBlock
{
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public BinBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH).setValue(OPEN, false));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] BIN = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.2, 0.0, 3.2, 12.8, 12.8, 12.8), Direction.EAST));
        final VoxelShape[] BIN_LIP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.6, 12.16, 1.6, 14.4, 12.8, 14.4), Direction.EAST));
        final VoxelShape[] BIN_LID = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.6, 12.8, 1.6, 14.4, 14.4, 14.4), Direction.EAST));
        final VoxelShape[] HANDLE_RIGHT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 14.4, 4.0, 8.8, 16.0, 5.6), Direction.EAST));
        final VoxelShape[] HANDLE_CENTER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 15.2, 5.6, 8.8, 16.0, 10.4), Direction.EAST));
        final VoxelShape[] HANDLE_LEFT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.2, 14.4, 10.4, 8.8, 16.0, 12.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(BIN[direction.get2DDataValue()]);
            shapes.add(BIN_LIP[direction.get2DDataValue()]);
            shapes.add(BIN_LID[direction.get2DDataValue()]);
            shapes.add(HANDLE_RIGHT[direction.get2DDataValue()]);
            shapes.add(HANDLE_CENTER[direction.get2DDataValue()]);
            shapes.add(HANDLE_LEFT[direction.get2DDataValue()]);
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
        builder.add(OPEN);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof BinBlockEntity blockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, pos);
            } else {
                throw new IllegalStateException("Bin container provider is absent.");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BinBlockEntity(pos, state);
    }

}