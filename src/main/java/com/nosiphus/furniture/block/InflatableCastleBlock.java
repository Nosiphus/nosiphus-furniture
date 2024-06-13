package com.nosiphus.furniture.block;

import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class InflatableCastleBlock extends Block {

    public InflatableCastleBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        if (entity instanceof LivingEntity) {
            float height = entity.fallDistance;
            if (height > 0 && !entity.isSteppingCarefully()) {
                if(height > 4) height = 4;
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.0, 1.0));
                entity.push(0, Math.sqrt(0.22 * (height + 0.25F)), 0);
                if(level.isClientSide) {
                    for(int i = 0; i < 5; i++) {
                        level.addParticle(ParticleTypes.ENTITY_EFFECT, entity.xo, entity.yo, entity.zo, 1.0, 1.0, 1.0);
                    }
                } else {
                    level.playSound(null, blockPos, ModSounds.BLOCK_INFLATABLE_CASTLE_BOUNCE.get(), SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.2F + 0.9F);
                }
            }
            entity.fallDistance = 0;
        }
    }

    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity) {
    }

    public boolean addLandingEffects(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, BlockState blockState1, LivingEntity livingEntity, int numberOfParticles) {
        return true;
    }

}
