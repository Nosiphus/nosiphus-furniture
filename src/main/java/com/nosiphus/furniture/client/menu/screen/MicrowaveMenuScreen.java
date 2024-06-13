package com.nosiphus.furniture.client.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.blockentity.MicrowaveBlockEntity;
import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MicrowaveMenuScreen extends AbstractContainerScreen<MicrowaveMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "textures/gui/microwave.png");
    public MicrowaveBlockEntity blockEntity;

    public MicrowaveMenuScreen(MicrowaveMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 174;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int startX = (this.width - this.imageWidth) / 2;
        int startY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, startX, startY, 0, 0, this.imageWidth, this.imageHeight);
        renderProgressBar(poseStack, startX, startY);
    }

    private void renderProgressBar(PoseStack poseStack, int x, int y) {
        if(menu.isCooking()) {
            blit(poseStack, x + 120, y + 26, 176, 0, menu.getScaledProgress(), 5);
        }
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        Component title = this.title;
        this.font.draw(poseStack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

}
