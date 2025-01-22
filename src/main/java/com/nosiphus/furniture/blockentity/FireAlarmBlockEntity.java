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

    private static Random random = new Random();
    private static int timer = 20;

    public FireAlarmBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FIRE_ALARM.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FireAlarmBlockEntity entity)
    {
        level.getServer().addTickable(() -> {
            int radius = 9;
            scanner:
            {
                for (int x = 0; x < radius; x++) {
                    for (int y = 0; y < radius; y++) {
                        for (int z = 0; z < radius; z++) {
                            if (level.getBlockState(pos.offset(-4 + x, -4 + y, -4 + z)).getBlock() == Blocks.FIRE) {
                                level.playSound(null, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, ModSounds.BLOCK_FIRE_ALARM_BEEP.get(), SoundSource.BLOCKS, 5.0F, 1.0F);
                                state.setValue(FireAlarmBlock.ACTIVATED, true);
                                break scanner;
                            }
                            if (x == 8 && y == 8 && z == 8) {
                                level.shouldTickBlocksAt(pos);
                                break scanner;
                            }
                        }
                    }
                }
            }
        });
    }

}
