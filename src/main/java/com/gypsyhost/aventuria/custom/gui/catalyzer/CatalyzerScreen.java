package com.gypsyhost.aventuria.custom.gui.catalyzer;

import com.gypsyhost.aventuria.Aventuria;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CatalyzerScreen extends AbstractContainerScreen<CatalyzerMenu> {
        private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/catalyzer_gui_new.png");

        int imageHeight = 210;
        int imageWidth = 176;
        int titleLabelX = 12;
        int titleLabelY = 12;
        int inventoryLabelX = 8;
        int inventoryLabelY = this.imageHeight - 97;

        public CatalyzerScreen(CatalyzerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
                this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
                this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
        }

        @Override
        protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, TEXTURE);


                int x = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
                int y = (height / 2 ) - (imageHeight / 2);
        
                this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        
                if(menu.isCrafting()) {
                blit(pPoseStack, x + 58, y + 55, 176, 13, menu.getScaledProgress(), 3); // Left Slot Progress Bar
                blit(pPoseStack, x + 81, y + 32, 176, 32, 3, menu.getScaledProgress()); // Top Slot Progress Bar
                blit(pPoseStack, x + 106 - menu.getScaledProgress(), y + 55, 186 - menu.getScaledProgress(), 17, menu.getScaledProgress(), 3); // Right Slot Progress Bar
                blit(pPoseStack, x + 81, y + 80 - menu.getScaledProgress(), 176, 31 - menu.getScaledProgress(), 3, menu.getScaledProgress()); // Bottom Slot Progress Bar
                }
        
                if(menu.hasFuel()) {
                blit(pPoseStack, x + 35, y + 85 + 12 - menu.getScaledFuelProgress(), 176, 12 - menu.getScaledFuelProgress(), 12, menu.getScaledFuelProgress());
                }
        }
        
        @Override
        public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
                renderBackground(pPoseStack);
                super.render(pPoseStack, mouseX, mouseY, delta);
                renderTooltip(pPoseStack, mouseX, mouseY);
                }
        }