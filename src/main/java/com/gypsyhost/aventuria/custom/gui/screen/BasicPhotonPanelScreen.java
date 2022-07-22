package com.gypsyhost.aventuria.custom.gui.screen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.menu.BasicPhotonPanelMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BasicPhotonPanelScreen extends AbstractContainerScreen<BasicPhotonPanelMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/basic_photon_panel.png");

    int imageHeight = 178;
    int imageWidth = 176;
    int titleLabelX = 12;
    int titleLabelY = 12;
    int inventoryLabelX = 8;
    int inventoryLabelY = this.imageHeight - 76;

    public BasicPhotonPanelScreen(BasicPhotonPanelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2; // this is needed so that the Menu class knows what the width and height of the gui is in order to draw the slots in the correct positions
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        pPoseStack.pushPose();
        pPoseStack.scale(0.8F, 0.8F, 0.8F);
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 8234367);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.8F, 0.8F, 0.8F);
        this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 8234367);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.5F, 0.5F, 0.5F);
        drawString(pPoseStack, this.minecraft.font, "Energy:" + menu.getEnergy() + "/" + menu.getMaxCapacity(), 80,82,8234367);
        pPoseStack.popPose();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);


        int x = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
        int y = (height / 2 ) - (imageHeight / 2);

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.hasEnergy()) {
            blit(pPoseStack, x + 40, y + 50, 0, 178, menu.getPanelCapacityScaled(), 12); // Power Capacity Display
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}