package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nosiphus.furniture.blockentity.ChoppingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardBlockEntityRenderer implements BlockEntityRenderer<ChoppingBoardBlockEntity> {

    public ChoppingBoardBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ChoppingBoardBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {

        int rotationData = blockEntity.getDirection(blockEntity.getBlockState());

        ItemStack stack = blockEntity.getFood();
        if(stack != null) {

            poseStack.pushPose();
            poseStack.translate(0.5, 0.0625, 0.5);
            poseStack.mulPose(Axis.XP.rotationDegrees(90F));
            poseStack.scale(0.375F, 0.375F, 0.375F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationData * 90F));
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, overlay, poseStack, source, blockEntity.getLevel(), 0);
            poseStack.popPose();

        }

    }

}