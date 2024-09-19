package com.nosiphus.furniture.block;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFenceBlock extends FenceBlock {

    public ElectricFenceBlock(Properties properties) {
        super(properties);
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
