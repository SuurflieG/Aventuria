package com.gypsyhost.aventuria.custom.gui.press;

import com.gypsyhost.aventuria.Aventuria;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PressScreen extends AbstractContainerScreen<PressMenu> {
        private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/press_gui_new.png");

        int imageHeight = 178;
        int imageWidth = 176;
        int titleLabelX = 10;
        int titleLabelY = 10;
        int inventoryLabelX = 8;
        int inventoryLabelY = this.imageHeight - 78;

        public PressScreen(PressMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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

                this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 8234367);

                pPoseStack.pushPose();
                pPoseStack.scale(0.8F, 0.8F, 0.8F);
                this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 8234367);
                pPoseStack.popPose();

                pPoseStack.pushPose();
                pPoseStack.scale(0.5F, 0.5F, 0.5F);
                drawString(pPoseStack, this.minecraft.font, "Progress:" + menu.getCurrentProgress() + "/" + menu.getMaxProgress(), 188,60,8234367);
                pPoseStack.popPose();
        }

        @Override
        protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, TEXTURE);
                int x = (width - imageWidth) / 2;
                int y = (height - imageHeight) / 2;
        
                this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        
                if(menu.isCrafting()) {
                blit(pPoseStack, x + 94, y + 38, 176, 13, menu.getScaledProgress(), 3);
                }
        
                if(menu.hasFuel()) {
                blit(pPoseStack, x + 49, y + 33 + 12 - menu.getScaledFuelProgress(), 176, 12 - menu.getScaledFuelProgress(), 12, menu.getScaledFuelProgress());
                }
        }
        
        @Override
        public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
                renderBackground(pPoseStack);
                super.render(pPoseStack, mouseX, mouseY, delta);
                renderTooltip(pPoseStack, mouseX, mouseY);
                }
        }