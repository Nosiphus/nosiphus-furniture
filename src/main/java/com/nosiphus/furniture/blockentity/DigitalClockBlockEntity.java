package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DigitalClockBlockEntity extends BlockEntity {

    public DigitalClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIGITAL_CLOCK.get(), pos, state);
    }

    public static String getFormattedTime(long ticks) {
        int hours = (int) ((Math.floor(ticks / 1000.0) + 6) % 24);
        int minutes = (int) Math.floor((ticks % 1000) / 1000.0 * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

}
