package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.block.FireAlarmBlock;
import com.nosiphus.furniture.core.ModBlockEntities;
import com.nosiphus.furniture.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class FireAlarmBlockEntity extends BlockEntity {

    public FireAlarmBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIRE_ALARM.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FireAlarmBlockEntity entity) {
    }

}
