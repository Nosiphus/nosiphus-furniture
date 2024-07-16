package com.nosiphus.furniture.client.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.client.menu.BinMenu;
import com.nosiphus.furniture.network.PacketHandler;
import com.nosiphus.furniture.network.message.C2SMessageEmptyBin;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BinMenuScreen extends AbstractContainerScreen<BinMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/bin.png");

    private Button emptyButton;

    public BinMenuScreen(BinMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.emptyButton = addRenderableWidget(
                Button.builder(
                        Component.translatable("gui.button.nfm.bin.empty"),
                        this::handleEmptyButton)
                        .bounds(this.getGuiLeft() + 128, this.getGuiTop() + 48, 40, 20)
                        .build());
    }

    private void handleEmptyButton(Button button) {
        PacketHandler.getPlayChannel().sendToServer(new C2SMessageEmptyBin(getMenu().getBlockEntity().getBlockPos()));
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.imageWidth = 176;
        this.imageHeight = 197;
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        Component title = this.title;
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, (this.imageHeight - 96 + 2), 0xFFFFFF);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

}