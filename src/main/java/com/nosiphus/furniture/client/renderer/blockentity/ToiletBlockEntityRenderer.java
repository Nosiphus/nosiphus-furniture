package com.nosiphus.furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mrcrayfish.furniture.block.FurnitureHorizontalBlock;
import com.nosiphus.furniture.blockentity.ToiletBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Matrix4f;

public class ToiletBlockEntityRenderer implements BlockEntityRenderer<ToiletBlockEntity> {

    public ToiletBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ToiletBlockEntity tileEntity, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light, int i1)
    {
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        Direction direction = tileEntity.getBlockState().getValue(FurnitureHorizontalBlock.DIRECTION);
        poseStack.mulPose(Axis.YP.rotationDegrees(direction.get2DDataValue() * -90F - 90F));
        poseStack.translate(-0.5, -0.5, -0.5);
        this.drawFluid(tileEntity, poseStack, source, 3.2F * 0.0625F, 6.4F * 0.0625F, 4.0F * 0.0625F, 10.4F * 0.0625F, 2.8F * 0.0625F, 8.0F * 0.0625F, light);
        poseStack.popPose();
    }

    private void drawFluid(ToiletBlockEntity te, PoseStack poseStack, MultiBufferSource source, float x, float y, float z, float width, float height, float depth, int light) {
        FluidStack fluidStack = te.getTank().getFluid();
        Fluid fluid = fluidStack.getFluid();
        if (fluid != Fluids.EMPTY) {
            IClientFluidTypeExtensions fluidType = IClientFluidTypeExtensions.of(fluid);
            TextureAtlasSprite sprite = (TextureAtlasSprite) Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidType.getStillTexture(fluid.defaultFluidState(), te.getLevel(), te.getBlockPos()));
            float minU = sprite.getU0();
            float maxU = Math.min(minU + (sprite.getU1() - minU) * depth, sprite.getU1());
            float minV = sprite.getV0();
            float maxV = Math.min(minV + (sprite.getV1() - minV) * width, sprite.getV1());
            int waterColor = fluidType.getTintColor(fluid.defaultFluidState(), te.getLevel(), te.getBlockPos());
            float red = (float)(waterColor >> 16 & 255) / 255.0F;
            float green = (float)(waterColor >> 8 & 255) / 255.0F;
            float blue = (float)(waterColor & 255) / 255.0F;
            height = (float)((double)height * ((double)te.getTank().getFluidAmount() / (double)te.getTank().getCapacity()));
            VertexConsumer consumer = source.getBuffer(RenderType.translucent());
            Matrix4f matrix = poseStack.last().pose();
            consumer.vertex(matrix, x, y + height, z).color(red, green, blue, 1.0F).uv(maxU, minV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
            consumer.vertex(matrix, x, y + height, z + depth).color(red, green, blue, 1.0F).uv(minU, minV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
            consumer.vertex(matrix, x + width, y + height, z + depth).color(red, green, blue, 1.0F).uv(minU, maxV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
            consumer.vertex(matrix, x + width, y + height, z).color(red, green, blue, 1.0F).uv(maxU, maxV).uv2(light).normal(0.0F, 1.0F, 0.0F).endVertex();
        }
    }

}
