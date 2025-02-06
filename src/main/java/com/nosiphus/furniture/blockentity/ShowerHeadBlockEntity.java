package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.block.ShowerHeadBlock;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModParticleTypes;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class ShowerHeadBlockEntity extends BlockEntity {

    private static Random random = new Random();
    private static int timer = 20;

    public ShowerHeadBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHOWER_HEAD.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ShowerHeadBlockEntity entity) {
        if(level != null) {
            if(state.getValue(ShowerHeadBlock.ACTIVATED)) {
                if(level.isClientSide) {
                    double posX = (double) pos.getX() + 0.35D + (random.nextDouble() / 3);
                    double posZ = (double) pos.getZ() + 0.35D + (random.nextDouble() / 3);
                    level.addParticle(ModParticleTypes.SHOWER_PARTICLE.get(), posX, pos.getY(), posZ, 0.0D, 0.0D, 0.0D);
                    level.addParticle(ModParticleTypes.SHOWER_PARTICLE.get(), posX, pos.getY(), posZ, 0.0D, 0.0D, 0.0D);
                } else {
                    if(timer % 5 == 0) {
                        //
                    }
                    if(timer >= 20) {
                        Vec3i directionVec = state.getValue(ShowerHeadBlock.DIRECTION).getOpposite().getNormal();
                        double x = pos.getX() + 0.5D + directionVec.getX() / 2.0D;
                        double y = pos.getY() + 0.5D + directionVec.getY() / 2.0D;
                        double z = pos.getZ() + 0.5D + directionVec.getZ() / 2.0D;
                        level.playSound(null, x, y, z, ModSounds.BLOCK_SHOWER_RUNNING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        timer = 0;
                    }
                    timer++;
                }
            }
        }
    }

}
