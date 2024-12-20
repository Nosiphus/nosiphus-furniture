package com.nosiphus.furniture.blockentity;

import com.nosiphus.furniture.core.ModBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DigitalClockBlockEntity extends BlockEntity {

    private DyeColor textColor = DyeColor.WHITE;

    public DigitalClockBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIGITAL_CLOCK.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if(tag.contains("textColor", CompoundTag.TAG_BYTE)) {
            this.textColor = DyeColor.byId(tag.getByte("textColor"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putByte("textColor", (byte) textColor.getId());
        super.saveAdditional(tag);
    }

    public DyeColor getTextColor() {
        return textColor;
    }

    public void setTextColor(DyeColor textColor) {
        this.textColor = textColor;
    }

    public static String getFormattedTime(long ticks) {
        int hours = (int) ((Math.floor(ticks / 1000.0) + 6) % 24);
        int minutes = (int) Math.floor((ticks % 1000) / 1000.0 * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static ChatFormatting getFromColor(DyeColor color) {
        switch(color) {
            case ORANGE: return ChatFormatting.GOLD;
            case MAGENTA: return ChatFormatting.LIGHT_PURPLE;
            case LIGHT_BLUE: return ChatFormatting.BLUE;
            case YELLOW: return ChatFormatting.YELLOW;
            case LIME: return ChatFormatting.GREEN;
            case PINK: return ChatFormatting.LIGHT_PURPLE;
            case GRAY: return ChatFormatting.DARK_GRAY;
            case LIGHT_GRAY: return ChatFormatting.GRAY;
            case CYAN: return ChatFormatting.DARK_AQUA;
            case PURPLE: return ChatFormatting.DARK_PURPLE;
            case BLUE: return ChatFormatting.DARK_BLUE;
            case BROWN: return ChatFormatting.GOLD;
            case GREEN: return ChatFormatting.DARK_GREEN;
            case RED: return ChatFormatting.DARK_RED;
            case BLACK: return ChatFormatting.BLACK;
            default: return ChatFormatting.WHITE;
        }
    }

}
