package com.nosiphus.furniture.client.gui.screen.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.blockentity.DishwasherBlockEntity;
import com.nosiphus.furniture.inventory.container.DishwasherMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DishwasherMenuScreen extends AbstractContainerScreen<DishwasherMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/dishwasher.png");
    public DishwasherBlockEntity blockEntity = getMenu().getBlockEntity();

    public DishwasherMenuScreen(DishwasherMenu menu, Inventory inventory, Component component) {
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
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        renderFluidLevel(guiGraphics, startX, startY);
        renderTankOverlay(guiGraphics, startX, startY);
        renderColor(guiGraphics, startX, startY);
    }

    private void renderFluidLevel(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 129, y + 39 + (55 - menu.getFluidRenderAmount()), menu.getFluidType(), (55 - menu.getFluidRenderAmount()), 7, menu.getFluidRenderAmount());
    }

    private void renderTankOverlay(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 129, y + 39, 192, 0, 7, 55);
    }

    private void renderColor(GuiGraphics guiGraphics, int x, int y) {
        if(menu.getWashing()) {
            guiGraphics.fill(x + 37, y + 9, x + 37 + 11, y + 9 + 11, -16711936);
        } else {
            guiGraphics.fill(x + 37, y + 9, x + 37 + 11, y + 9 + 11, -65280);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Component title = this.title;
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, (this.imageHeight - 96 + 2), 0xFFFFFF);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        if(isHovering(129, 39, 7, 55, mouseX, mouseY)) {
            setTooltipForNextRenderPass(Component.literal(menu.getFluidStack().getAmount() + " / 64000"));
        }

    }

}
