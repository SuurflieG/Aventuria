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

public class EquipmentSelectButton extends AbstractWidget {

    public final Predicate<Boolean> onPress;
    public boolean currentlySelected;
    public final ResourceLocation texture;

    public EquipmentSelectButton(int xIn, int yIn, Component pMessage, ResourceLocation texture, Predicate<Boolean> onPress) {
        super(xIn, yIn, 41, 14, pMessage);

        this.texture = texture;
        this.onPress = onPress;
        this.currentlySelected = this.onPress.test(false);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {


        Color selectedColor = this.currentlySelected ? Color.orange : Color.gray;

        fill(pPoseStack, this.x, this.y, this.x + this.width, this.y + this.height, ((this.currentlySelected ? 0xFFC800 : 0x808080)) + selectedColor.getRGB());

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, texture);
        blit(pPoseStack, this.x, this.y, 0, 0, 41, 14, 41, 14);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public List<FormattedCharSequence> getTooltip() {
        return Language.getInstance().getVisualOrder(Arrays.asList(this.getMessage(), new TextComponent("Currently Selected: " + this.currentlySelected).withStyle(this.currentlySelected ? ChatFormatting.YELLOW : ChatFormatting.GRAY)));
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        this.currentlySelected = !this.currentlySelected;
        this.onPress.test(true);
    }

    public void setCurrentlySelected(boolean currentlySelected) {
        this.currentlySelected = currentlySelected;
    }

    public boolean isCurrentlySelected() {
        return currentlySelected;
    }

    @Override
    public void setMessage(Component pMessage) {
        super.setMessage(pMessage);
    }

}
