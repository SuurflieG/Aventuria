package com.gypsyhost.socketcraft.custom.gui.press;

import com.gypsyhost.socketcraft.SocketCraft;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PressScreen extends AbstractContainerScreen<PressMenu> {
        private static final ResourceLocation TEXTURE =
        new ResourceLocation(SocketCraft.MOD_ID, "textures/gui/press_gui.png");

        public PressScreen(PressMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        }

        @Override
        protected void init() {
                super.init();
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
                blit(pPoseStack, x + 97, y + 24, 176, 16, menu.getScaledProgress(), 40);
                }
        
                if(menu.hasFuel()) {
                blit(pPoseStack, x + 56, y + 36 + 15 - menu.getScaledFuelProgress(), 176, 15 - menu.getScaledFuelProgress(), 11, menu.getScaledFuelProgress());
                }
        }
        
        @Override
        public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
                renderBackground(pPoseStack);
                super.render(pPoseStack, mouseX, mouseY, delta);
                renderTooltip(pPoseStack, mouseX, mouseY);
                }
        }