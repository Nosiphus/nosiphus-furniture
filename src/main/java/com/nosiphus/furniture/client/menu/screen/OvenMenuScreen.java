package com.nosiphus.furniture.client.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.blockentity.OvenBlockEntity;
import com.nosiphus.furniture.client.menu.OvenMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OvenMenuScreen extends AbstractContainerScreen<OvenMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "textures/gui/oven.png");
    public OvenBlockEntity blockEntity;

    public OvenMenuScreen(OvenMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 228;
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
        renderProgressFire1(poseStack, startX, startY);
        renderProgressFire2(poseStack, startX, startY);
        renderProgressFire3(poseStack, startX, startY);
        renderProgressFire4(poseStack, startX, startY);
    }

    private void renderProgressFire1(PoseStack poseStack, int x, int y) {
        if (menu.isCooking1()) {
            blit(poseStack, x + 54, y + 55, 176, 0, 14, menu.getScaledProgress1());
        }
    }

    private void renderProgressFire2(PoseStack poseStack, int x, int y) {
        if (menu.isCooking2()) {
            blit(poseStack, x + 72, y + 55, 176, 0, 14, menu.getScaledProgress2());
        }
    }

    private void renderProgressFire3(PoseStack poseStack, int x, int y) {
        if (menu.isCooking3()) {
            blit(poseStack, x + 90, y + 55, 176, 0, 14, menu.getScaledProgress3());
        }
    }

    private void renderProgressFire4(PoseStack poseStack, int x, int y) {
        if (menu.isCooking4()) {
            blit(poseStack, x + 108, y + 55, 176, 0, 14, menu.getScaledProgress4());
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
