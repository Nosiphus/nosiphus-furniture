package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.util.BlockEntityUtil;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.blockentity.StereoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class StereoBlock extends FurnitureHorizontalBlock implements EntityBlock
{
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public static ArrayList<RecordItem> records = new ArrayList<>();

    public StereoBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.EAST));
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());

        for(Item item : ForgeRegistries.ITEMS) {
            if(item instanceof RecordItem) {
                records.add((RecordItem) item);
            }
        }

    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states)
    {
        final VoxelShape[] CORE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.5, 0.0, 0.5, 10.5, 7.0, 15.5), Direction.EAST));
        final VoxelShape[] SCREEN = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.4, 3.0, 5.6, 5.5, 6.0, 10.6), Direction.EAST));
        final VoxelShape[] ANTENNA_BASE = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(7.0, 7.0, 6.2, 9.0, 7.5, 9.8), Direction.EAST));
        final VoxelShape[] POWER_BUTTON = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.1, 1.0, 6.0, 5.5, 2.0, 7.0), Direction.EAST));
        final VoxelShape[] BUTTON_1 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.1, 1.0, 7.5, 5.5, 2.0, 8.5), Direction.EAST));
        final VoxelShape[] BUTTON_2 = VoxelShapeHelper.getRotatedShapes(VoxelShapeHelper.rotate(Block.box(5.1, 1.0, 9.0, 5.5, 2.0, 10.0), Direction.EAST));

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for (BlockState state : states) {
            Direction direction = state.getValue(DIRECTION);
            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(CORE[direction.get2DDataValue()]);
            shapes.add(SCREEN[direction.get2DDataValue()]);
            shapes.add(ANTENNA_BASE[direction.get2DDataValue()]);
            shapes.add(POWER_BUTTON[direction.get2DDataValue()]);
            shapes.add(BUTTON_1[direction.get2DDataValue()]);
            shapes.add(BUTTON_2[direction.get2DDataValue()]);
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if(blockEntity instanceof StereoBlockEntity) {
            StereoBlockEntity stereoBlockEntity = (StereoBlockEntity) blockEntity;
            if(!player.isSteppingCarefully()) {
                if(stereoBlockEntity.count == 16) {
                    stereoBlockEntity.count = records.size();
                } else {
                    stereoBlockEntity.count++;
                }
                if(stereoBlockEntity.count == records.size())
                {
                    stereoBlockEntity.count = 0;
                }
                level.levelEvent(1010, pos, Item.getId(records.get(stereoBlockEntity.count)));
            } else {
                if(stereoBlockEntity.count != 16) {
                    stereoBlockEntity.count = 16;
                    if(!level.isClientSide()) {
                        //player.sendMessage(new TextComponentTranslation("cfm.message.stereo1"));
                    }
                    this.ejectRecord(level, pos);
                }
            }
            if(!level.isClientSide())
            {
                BlockEntityUtil.sendUpdatePacket(blockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        this.ejectRecord(level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    public void ejectRecord(Level level, BlockPos pos) {
        level.levelEvent(1011, pos, -1);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StereoBlockEntity(pos, state);
    }

}