package com.nosiphus.furniture.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.util.VoxelShapeHelper;
import com.nosiphus.furniture.common.ModDamageTypes;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class ElectricFenceBlock extends FenceBlock {

    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public ElectricFenceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any());
        SHAPES = this.generateShapes(this.getStateDefinition().getPossibleStates());
    }

    private ImmutableMap<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states) {

        final VoxelShape POST = Block.box(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);

        final VoxelShape NORTH_SIDE = Block.box(7.3, 0.0, 0.0, 8.7, 16.0, 8.0);
        final VoxelShape EAST_SIDE = Block.box(8.0, 0.0, 7.3, 16.0, 16.0, 8.7);
        final VoxelShape SOUTH_SIDE = Block.box(7.3, 0.0, 8.0, 8.7, 16.0, 16.0);
        final VoxelShape WEST_SIDE = Block.box(0.0, 0.0, 7.3, 8.0, 16.0, 8.7);

        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(BlockState state : states) {

            boolean north = state.getValue(NORTH);
            boolean east = state.getValue(EAST);
            boolean south = state.getValue(SOUTH);
            boolean west = state.getValue(WEST);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(POST);

            if(north) {
                shapes.add(NORTH_SIDE);
            }

            if(east) {
                shapes.add(EAST_SIDE);
            }

            if(south) {
                shapes.add(SOUTH_SIDE);
            }

            if(west) {
                shapes.add(WEST_SIDE);
            }

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
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return SHAPES.get(state);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        if(entity instanceof LivingEntity && !((LivingEntity) entity).isDeadOrDying()) {
            if(entity instanceof Creeper) {
                Creeper creeper = (Creeper) entity;
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                level.addFreshEntity(lightningBolt);
                if(!creeper.isPowered()) {
                    creeper.setSecondsOnFire(1);
                    creeper.thunderHit((ServerLevel) level, lightningBolt);
                }
            } else if (entity instanceof Player) {
                Player player = (Player) entity;
                if(!player.isCreative()) {
                    player.hurt(level.damageSources().source(ModDamageTypes.ELECTRIC_FENCE), 2.0F);
                    level.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, ModSounds.BLOCK_ELECTRIC_FENCE_ZAP.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
                }
            }
            else {
                entity.hurt(level.damageSources().source(ModDamageTypes.ELECTRIC_FENCE), 2.0F);
                level.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, ModSounds.BLOCK_ELECTRIC_FENCE_ZAP.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
            }
        }
    }
}
