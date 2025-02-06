package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.furniture.block.DigitalClockBlock;
import com.nosiphus.furniture.blockentity.DigitalClockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.block.state.BlockState;

public class DigitalClockBlockEntityRenderer implements BlockEntityRenderer<DigitalClockBlockEntity> {

    private final Font font;

    public DigitalClockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.getFont();
    }

    @Override
    public void render(DigitalClockBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {

        BlockState state = blockEntity.getBlockState();
        if(state.getBlock() instanceof DigitalClockBlock) {

            poseStack.pushPose();

            poseStack.translate(0.5, 0.5, 0.5);

            int rotation = state.getValue(DigitalClockBlock.DIRECTION).get2DDataValue();
            poseStack.mulPose(Axis.YP.rotationDegrees(-90F * rotation + 180F));

            poseStack.translate(0.0675, 0.005, -0.032);
            poseStack.translate(-4.2 * 0.0625, -5.0 * 0.0625, 1.55 * 0.0625);
            poseStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
            poseStack.scale(1.5F, 1.5F, 1.5F);

            this.font.drawInBatch(FormattedCharSequence.forward(DigitalClockBlockEntity.getFormattedTime(Minecraft.getInstance().level.getDayTime()), Style.EMPTY.withColor(blockEntity.getFromColor(blockEntity.getTextColor()))), 0, 0, overlay, false, poseStack.last().pose(), source, Font.DisplayMode.NORMAL, 0, light);

            poseStack.popPose();

        }

    }

}
