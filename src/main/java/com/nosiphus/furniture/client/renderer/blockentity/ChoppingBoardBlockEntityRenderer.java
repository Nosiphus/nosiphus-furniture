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
            ItemStack stack = choppingBoard.get(0);
            if(!stack.isEmpty()) {

                poseStack.pushPose();

                float xOffset = 0.0F;
                float zOffset = 0.0F;

                poseStack.translate(0.5F + xOffset, 0.02F, 0.3F + zOffset);
                poseStack.mulPose(Vector3f.XP.rotation(0.1F * -90F));
                poseStack.mulPose(Vector3f.ZP.rotation(180F));
                poseStack.scale(0, -0.15F, 0);
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, light, overlay, poseStack, source, 0);
                
                poseStack.popPose();
            }
    }

}
