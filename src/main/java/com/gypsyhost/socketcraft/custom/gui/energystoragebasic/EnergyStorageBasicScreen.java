package com.gypsyhost.socketcraft.custom.gui.energystoragebasic;

import com.gypsyhost.socketcraft.SocketCraft;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EnergyStorageBasicScreen extends AbstractContainerScreen<EnergyStorageBasicMenu> {
        private static final ResourceLocation TEXTURE = new ResourceLocation(SocketCraft.MOD_ID, "textures/gui/energy_storage_basic_gui.png");

        int titleLabelX = 8;
        int titleLabelY = 6;
        int inventoryLabelX = 8;
        int inventoryLabelY = this.imageHeight - 90;

        public EnergyStorageBasicScreen(EnergyStorageBasicMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
                        blit(pPoseStack, x + 65, y + 63, 176, 0, menu.getCurrentEnergy(), 7); // Power Capacity Display
                }

        }
        
        @Override
        public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
                renderBackground(pPoseStack);
                super.render(pPoseStack, mouseX, mouseY, delta);
                renderTooltip(pPoseStack, mouseX, mouseY);
                }
        }