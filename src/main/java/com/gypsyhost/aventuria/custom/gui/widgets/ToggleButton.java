package com.gypsyhost.aventuria.custom.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ToggleButton extends AbstractWidget {
    public final Predicate<Boolean> onPress;
    public boolean enabled;
    public final ResourceLocation texture;

    public ToggleButton(int xIn, int yIn, Component msg, ResourceLocation texture, Predicate<Boolean> onPress) {
        super(xIn, yIn, 16, 16, msg);

        this.texture = texture;
        this.onPress = onPress;

        this.enabled = this.onPress.test(false);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {


        Color activeColor = this.enabled ? Color.GREEN : Color.LIGHT_GRAY;

        fill(pPoseStack, this.x, this.y, this.x + this.width, this.y + this.height, ((this.enabled ? 0x68000000 : 0x9B000000)) + activeColor.getRGB());

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);
        blit(pPoseStack, this.x + 1, this.y + 1, 0, 0, 14, 14, 14, 14);
    }

    public List<FormattedCharSequence> getTooltip() {
        return Language.getInstance().getVisualOrder(Arrays.asList(this.getMessage(), new TextComponent("Enabled: " + this.enabled).withStyle(this.enabled ? ChatFormatting.GREEN : ChatFormatting.GRAY)));
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        this.enabled = !this.enabled;
        this.onPress.test(true);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void updateNarration(NarrationElementOutput p_169152_) {

    }
}
