package com.nosiphus.furniture.client.renderer.blockentity.yogmod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.furniture.block.yogmod.YogDigitalClockBlock;
import com.nosiphus.furniture.blockentity.yogmod.YogDigitalClockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class YogDigitalClockBlockEntityRenderer implements BlockEntityRenderer<YogDigitalClockBlockEntity> {

    private final Font font;

    public YogDigitalClockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.getFont();
    }

    @Override
    public void render(YogDigitalClockBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {

        BlockState state = blockEntity.getBlockState();
        if(state.getBlock() instanceof YogDigitalClockBlock) {

            poseStack.pushPose();

            poseStack.translate(0.5, 0.5, 0.5);

            int rotation = state.getValue(YogDigitalClockBlock.DIRECTION).get2DDataValue();
            poseStack.mulPose(Axis.YP.rotationDegrees(-90F * rotation + 180F));

            poseStack.translate(0.0675, 0.005, -0.032);
            poseStack.translate(-4.2 * 0.0625, -5.0 * 0.0625, 1.55 * 0.0625);
            poseStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
            poseStack.scale(1.5F, 1.5F, 1.5F);

            this.font.drawInBatch(YogDigitalClockBlockEntity.getFormattedTime(Minecraft.getInstance().level.getDayTime()), 0, 0, overlay, false, poseStack.last().pose(), source, Font.DisplayMode.NORMAL, 0, light);

            poseStack.popPose();

        }

    }

}
