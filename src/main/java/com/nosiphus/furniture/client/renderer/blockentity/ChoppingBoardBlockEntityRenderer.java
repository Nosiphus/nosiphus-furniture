package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nosiphus.furniture.blockentity.ChoppingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardBlockEntityRenderer implements BlockEntityRenderer<ChoppingBoardBlockEntity> {

    public ChoppingBoardBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ChoppingBoardBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int overlay) {

    }

}