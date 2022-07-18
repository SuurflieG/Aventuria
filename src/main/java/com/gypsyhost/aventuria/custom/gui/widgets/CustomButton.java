package com.gypsyhost.aventuria.custom.gui.widgets;

import com.gypsyhost.aventuria.Aventuria;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomButton extends Button {

    private static final ResourceLocation WIDGET = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/widgets.png");

    public int width;
    public int height;
    public int x;
    public int y;
    public Component message;
    public boolean isHovered;
    public boolean active = true;
    public boolean visible = true;
    public float alpha = 1.0F;
    public boolean focused;

    public final Button.OnPress onPress;

    public CustomButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, Button.OnPress pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;
        this.message = pMessage;
        this.onPress = pOnPress;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGET);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHoveredOrFocused());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(pPoseStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.blit(pPoseStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(pPoseStack, minecraft, pMouseX, pMouseY);
        int j = getFGColor();
        drawCenteredString(pPoseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    public void setMessage(Component pMessage) {
        this.message = pMessage;
    }

    @Override
    public Component getMessage() {
        return message;
    }

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(pButton)) {
                boolean flag = this.clicked(pMouseX, pMouseY);
                if (flag) {
                    this.playDownSound(Minecraft.getInstance().getSoundManager());
                    this.onClick(pMouseX, pMouseY);
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
