package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nosiphus.furniture.blockentity.ChoppingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardBlockEntityRenderer implements BlockEntityRenderer<ChoppingBoardBlockEntity> {

    public ChoppingBoardBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ChoppingBoardBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {

        int rotationData = blockEntity.getDirection(blockEntity.getBlockState());

        ItemStack stack = blockEntity.getFood();
        if(stack != null) {

            poseStack.pushPose();

            float xOffset = 0.0F;
            float zOffset = 0.0F;

            switch(rotationData)
            {
                case 0:
                    zOffset -= 0.1F;
                    break;
                case 1:
                    xOffset += 0.3F;
                    zOffset += 0.2F;
                    break;
                case 2:
                    zOffset += 0.5F;
                    break;
                case 3:
                    xOffset -= 0.3F;
                    zOffset += 0.2F;
                    break;
            }

            poseStack.translate(0.5F + xOffset, 0.02F, 0.3F + zOffset);
            poseStack.mulPose(Vector3f.XP.rotation(rotationData * -90F));
            poseStack.mulPose(Vector3f.ZP.rotation(180));
            poseStack.translate(0, -0.15, 0);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, light, overlay, poseStack, source, 0);
            poseStack.popPose();

        }

    }

}