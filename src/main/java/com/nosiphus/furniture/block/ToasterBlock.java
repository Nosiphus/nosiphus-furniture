package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.ToasterBlockEntity;
import com.nosiphus.furniture.core.ModItems;
import com.nosiphus.furniture.item.crafting.ToastingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToasterBlock extends FurnitureHorizontalBlock implements EntityBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public ToasterBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {

        final VoxelShape[] LEVER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 4.0, 2.5, 9.0, 4.8, 3.5), Direction.EAST));
        final VoxelShape[] NORTH_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.5, 0.0, 3.5, 9.5, 6.4, 4.5), Direction.EAST));
        final VoxelShape[] WEST_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.5, 0.0, 3.5, 6.5, 6.4, 12.5), Direction.EAST));
        final VoxelShape[] CENTER = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.5, 0.0, 4.5, 8.5, 6.4, 11.5), Direction.EAST));
        final VoxelShape[] EAST_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(9.5, 0.0, 3.5, 10.5, 6.4, 12.5), Direction.EAST));
        final VoxelShape[] SOUTH_SIDE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(6.5, 0.0, 11.5, 9.5, 6.4, 12.5), Direction.EAST));
        final VoxelShape[] BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.0, 0.0, 3.0, 11.0, 1.0, 13.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(LEVER[direction.get2DDataValue()]);
            shapes.add(NORTH_SIDE[direction.get2DDataValue()]);
            shapes.add(WEST_SIDE[direction.get2DDataValue()]);
            shapes.add(CENTER[direction.get2DDataValue()]);
            shapes.add(EAST_SIDE[direction.get2DDataValue()]);
            shapes.add(SOUTH_SIDE[direction.get2DDataValue()]);
            shapes.add(BASE[direction.get2DDataValue()]);
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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof ToasterBlockEntity blockEntity) {
                Containers.dropContents(level, pos, blockEntity.getToaster());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide() && result.getDirection() == Direction.UP) {
            if(level.getBlockEntity(pos) instanceof ToasterBlockEntity blockEntity) {
                ItemStack heldItem = player.getItemInHand(hand);
                if(heldItem.getItem() == ModItems.KNIFE.get()) {
                    if(blockEntity.toastItem()) {
                        if(!player.getAbilities().instabuild) {
                            heldItem.setDamageValue(heldItem.getDamageValue() + 1);
                        }
                    }
                } else if(!heldItem.isEmpty()) {
                    Optional<ToastingRecipe> optional = blockEntity.findMatchingRecipe(heldItem);
                    if(optional.isPresent()) {
                        if(blockEntity.addItem(heldItem)) {
                            if(!player.getAbilities().instabuild) {
                                heldItem.shrink(1);
                            }
                        }
                    } else {
                        if(!level.isClientSide()) {
                            blockEntity.removeItem();
                        }
                    }
                } else {
                    blockEntity.removeItem();
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ToasterBlockEntity(pos, state);
    }

}