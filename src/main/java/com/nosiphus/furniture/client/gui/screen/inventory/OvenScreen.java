package com.nosiphus.furniture.client.gui.screen.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.blockentity.OvenBlockEntity;
import com.nosiphus.furniture.inventory.container.OvenMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OvenScreen extends AbstractContainerScreen<OvenMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/oven.png");
    public OvenBlockEntity blockEntity;

    public OvenScreen(OvenMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 228;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int startX = (this.width - this.imageWidth) / 2;
        int startY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, startX, startY, 0, 0, this.imageWidth, this.imageHeight);
        renderProgressFire1(guiGraphics, startX, startY);
        renderProgressFire2(guiGraphics, startX, startY);
        renderProgressFire3(guiGraphics, startX, startY);
        renderProgressFire4(guiGraphics, startX, startY);
    }

    private void renderProgressFire1(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCooking1()) {
            guiGraphics.blit(TEXTURE, x + 54, y + 55 + (14 - menu.getScaledProgress1()), 176, (14 - menu.getScaledProgress1()), 14, menu.getScaledProgress1());
        }
    }

    private void renderProgressFire2(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCooking2()) {
            guiGraphics.blit(TEXTURE, x + 72, y + 55 + (14 - menu.getScaledProgress2()), 176, (14 - menu.getScaledProgress2()), 14, menu.getScaledProgress2());
        }
    }

    private void renderProgressFire3(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCooking3()) {
            guiGraphics.blit(TEXTURE, x + 90, y + 55 + (14 - menu.getScaledProgress3()), 176, (14 - menu.getScaledProgress3()), 14, menu.getScaledProgress3());
        }
    }

    private void renderProgressFire4(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCooking4()) {
            guiGraphics.blit(TEXTURE, x + 108, y + 55 + (14 - menu.getScaledProgress4()), 176, (14 - menu.getScaledProgress4()), 14, menu.getScaledProgress4());
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Component title = this.title;
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, (this.imageHeight - 96 + 2), 4210752);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
