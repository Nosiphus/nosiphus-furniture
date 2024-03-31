package com.nosiphus.furniture.client.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.client.menu.BinMenu;
import com.nosiphus.furniture.network.PacketHandler;
import com.nosiphus.furniture.network.message.C2SMessageEmptyBin;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BinMenuScreen extends AbstractContainerScreen<BinMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "textures/gui/bin.png");

    public BinMenuScreen(BinMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.imageWidth = 176;
        this.imageHeight = 197;
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new Button(this.getGuiLeft() + 128, this.getGuiTop() + 48, 40, 20, Component.translatable("gui.button.nfm.bin.empty"), button -> PacketHandler.getPlayChannel()
                .sendToServer(new C2SMessageEmptyBin(getMenu().getBlockEntity().getBlockPos()))));
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int startX = (this.width - this.imageWidth) / 2;
        int startY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, startX, startY, 0, 0, this.imageWidth, this.imageHeight);
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
