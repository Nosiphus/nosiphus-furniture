package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nosiphus.furniture.blockentity.ChoppingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardBlockEntityRenderer implements BlockEntityRenderer<ChoppingBoardBlockEntity> {

    public ChoppingBoardBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ChoppingBoardBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {
        NonNullList<ItemStack> choppingBoard = blockEntity.getChoppingBoard();
        for(int j = 0; j < choppingBoard.size(); j++) {
            ItemStack stack = choppingBoard.get(j);
            if(!stack.isEmpty()) {
                poseStack.pushPose();

                poseStack.translate(0.5, 1.0, 0.5);
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90F));
                poseStack.translate(-0.2 + 0.4 * (j % 2), -0.2 + 0.4 * (j / 2), 0.0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(90F * blockEntity.getRotations()[j]));

                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, light, overlay, poseStack, source, 0);
                poseStack.popPose();
            }
        }
    }


}
