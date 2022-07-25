package com.gypsyhost.aventuria.custom.gui.screen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.block.entity.BasicSolarPanelBlockEntity;
import com.gypsyhost.aventuria.custom.gui.menu.BasicSolarPanelMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BasicSolarPanelScreen extends AbstractContainerScreen<BasicSolarPanelMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/basic_solar_panel.png");

    int imageHeight = 166;
    int imageWidth = 176;
    int titleLabelX = 9;
    int titleLabelY = 9;
    int inventoryLabelX = 9;
    int inventoryLabelY = this.imageHeight - 72;

    public BasicSolarPanelScreen(BasicSolarPanelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        drawString(pPoseStack, this.minecraft.font, "Kilowatts:  " + menu.getKilowatts() + "/" + menu.getMaxCapacity(), 120,43,8234367); // 64 55
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.5F, 0.5F, 0.5F);
        drawString(pPoseStack, this.minecraft.font, "Produces:  " + menu.getKilowattPerTick() + " kW/Tick", 120,59,8234367); // 64 55
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(0.5F, 0.5F, 0.5F);
        drawString(pPoseStack, this.minecraft.font, "Output:  " + menu.getExtractRate() + " kW/Tick", 120,75,8234367); // 64 55
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

        if(menu.hasKilowatts()) {
            blit(pPoseStack, x + 65, y + 65, 176, 0, menu.getPanelCapacityScaled(), 7); // Power Capacity Display
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}