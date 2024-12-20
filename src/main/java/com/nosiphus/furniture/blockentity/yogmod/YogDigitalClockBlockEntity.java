package com.nosiphus.furniture.blockentity.yogmod;

import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class YogDigitalClockBlockEntity extends BlockEntity {

    public YogDigitalClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.YOGMOD_DIGITAL_CLOCK.get(), pos, state);
    }

    public static String getFormattedTime(long ticks) {
        int hours = (int) ((Math.floor(ticks / 1000.0) + 6) % 24);
        int minutes = (int) Math.floor((ticks % 1000) / 1000.0 * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

}
