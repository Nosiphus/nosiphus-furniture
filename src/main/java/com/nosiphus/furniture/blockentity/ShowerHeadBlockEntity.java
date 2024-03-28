package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class ShowerHeadBlockEntity extends BlockEntity {

    private static Random random = new Random();
    private static int timer = 20;

    public ShowerHeadBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHOWER_HEAD.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ShowerHeadBlockEntity entity) {
        if(level.isClientSide) {
            double posX = (double) pos.getX() + 0.35D + (random.nextDouble() / 3);
            double posZ = (double) pos.getZ() + 0.35D + (random.nextDouble() / 3);
            level.addParticle(ModParticleTypes.SHOWER_PARTICLE.get(), posX, pos.getY(), posZ, 0.0D, 0.0D, 0.0D);
        }
    }

}
