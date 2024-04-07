package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.OvenBlockEntity;
import com.nosiphus.furniture.core.ModBlockEntities;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class OvenBlock extends FurnitureHorizontalBlock implements EntityBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public OvenBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] TOP_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 13.0, 3.0, 14.0, 16.0, 13.0), Direction.EAST));
        final VoxelShape[] LEFT_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 1.0, 14.0, 16.0, 3.0), Direction.EAST));
        final VoxelShape[] BACK_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(13.0, 1.0, 3.0, 14.0, 13.0, 13.0), Direction.EAST));
        final VoxelShape[] RIGHT_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 13.0, 14.0, 16.0, 15.0), Direction.EAST));
        final VoxelShape[] BOTTOM_FRAME = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(2.0, 0.0, 3.0, 14.0, 3.0, 13.0), Direction.EAST));
        final VoxelShape[] DIAL_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 2.0, 2.0, 15.0, 3.0), Direction.EAST));
        final VoxelShape[] DIAL_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 7.0, 2.0, 15.0, 8.0), Direction.EAST));
        final VoxelShape[] DIAL_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 9.0, 2.0, 15.0, 10.0), Direction.EAST));
        final VoxelShape[] DIAL_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 11.0, 2.0, 15.0, 12.0), Direction.EAST));
        final VoxelShape[] DIAL_5 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 14.0, 13.0, 2.0, 15.0, 14.0), Direction.EAST));
        final VoxelShape[] DOOR_GLASS = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.5, 3.0, 4.0, 2.5, 11.0, 12.0), Direction.EAST));
        final VoxelShape[] DOOR_HANDLE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(0.5, 11.5, 6.0, 1.5, 12.5, 10.0), Direction.EAST));
        final VoxelShape[] DOOR_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 11.0, 2.0, 12.0, 13.0, 14.0), Direction.EAST));
        final VoxelShape[] DOOR_LEFT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 3.0, 2.0, 2.0, 11.0, 4.0), Direction.EAST));
        final VoxelShape[] DOOR_RIGHT = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 3.0, 12.0, 2.0, 11.0, 14.0), Direction.EAST));
        final VoxelShape[] DOOR_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(1.0, 1.0, 2.0, 2.0, 3.0, 14.0), Direction.EAST));
        final VoxelShape[] TRAY_BOTTOM = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 4.0, 3.0, 13.0, 5.0, 13.0), Direction.EAST));
        final VoxelShape[] TRAY_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 7.0, 3.0, 13.0, 8.0, 13.0), Direction.EAST));
        final VoxelShape[] BURNER_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 15.5, 3.0, 7.0, 16.5, 7.0), Direction.EAST));
        final VoxelShape[] BURNER_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(3.0, 15.5, 9.0, 7.0, 16.5, 13.0), Direction.EAST));
        final VoxelShape[] BURNER_3 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(9.0, 15.5, 9.0, 13.0, 16.5, 13.0), Direction.EAST));
        final VoxelShape[] BURNER_4 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(9.0, 15.5, 3.0, 13.0, 16.5, 7.0), Direction.EAST));
        final VoxelShape[] BACKING = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 0.0, 0.9, 16.0, 16.0, 15.0), Direction.EAST));
        final VoxelShape[] BACKING_TOP = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(14.0, 16.0, 0.9, 16.0, 18.0, 15.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states)
        {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(TOP_FRAME[direction.get2DDataValue()]);
            shapes.add(LEFT_FRAME[direction.get2DDataValue()]);
            shapes.add(BACK_FRAME[direction.get2DDataValue()]);
            shapes.add(RIGHT_FRAME[direction.get2DDataValue()]);
            shapes.add(BOTTOM_FRAME[direction.get2DDataValue()]);
            shapes.add(DIAL_1[direction.get2DDataValue()]);
            shapes.add(DIAL_2[direction.get2DDataValue()]);
            shapes.add(DIAL_3[direction.get2DDataValue()]);
            shapes.add(DIAL_4[direction.get2DDataValue()]);
            shapes.add(DIAL_5[direction.get2DDataValue()]);
            shapes.add(DOOR_GLASS[direction.get2DDataValue()]);
            shapes.add(DOOR_HANDLE[direction.get2DDataValue()]);
            shapes.add(DOOR_TOP[direction.get2DDataValue()]);
            shapes.add(DOOR_LEFT[direction.get2DDataValue()]);
            shapes.add(DOOR_RIGHT[direction.get2DDataValue()]);
            shapes.add(DOOR_BOTTOM[direction.get2DDataValue()]);
            shapes.add(TRAY_BOTTOM[direction.get2DDataValue()]);
            shapes.add(TRAY_TOP[direction.get2DDataValue()]);
            shapes.add(BURNER_1[direction.get2DDataValue()]);
            shapes.add(BURNER_2[direction.get2DDataValue()]);
            shapes.add(BURNER_3[direction.get2DDataValue()]);
            shapes.add(BURNER_4[direction.get2DDataValue()]);
            shapes.add(BACKING[direction.get2DDataValue()]);
            shapes.add(BACKING_TOP[direction.get2DDataValue()]);
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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof OvenBlockEntity) {
                ((OvenBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof OvenBlockEntity blockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, pos);
            } else {
                throw new IllegalStateException("Oven container provider is absent.");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OvenBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.OVEN.get(), OvenBlockEntity::tick);
    }

}