package com.gypsyhost.aventuria.custom.gui.screen;

import com.google.common.collect.Lists;
import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.menu.UpgradeStationMenu;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.network.PacketHandler;
import com.gypsyhost.aventuria.network.packets.PacketExtractUpgrade;
import com.gypsyhost.aventuria.network.packets.PacketInsertUpgrade;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.common.ForgeI18n;



public class UpgradeStationScreen extends AbstractContainerScreen<UpgradeStationMenu> {

    private final ResourceLocation GUI = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/upgrade_station.png");
    private final BlockPos tePos;
    private final UpgradeStationMenu menu;
    private Inventory playerInventory;
    private ScrollingUpgrades scrollingUpgrades;


    public UpgradeStationScreen(UpgradeStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.tePos = pMenu.getBlockEntity().getBlockPos();
        this.menu = pMenu;
        this.playerInventory = pPlayerInventory;
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, partialTicks);

        this.scrollingUpgrades.render(pPoseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(pPoseStack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip

        int relX = (this.width) / 2;
        int relY = (this.height) / 2;

        drawCenteredString(pPoseStack, font, ForgeI18n.getPattern(String.format("%s.%s", Aventuria.MOD_ID, "text.modification_table")), relX, relY - 100, 0xFFFFFF);

        if (this.menu.getUpgradesCache().size() == 0) {
            String string = ForgeI18n.getPattern(String.format("%s.%s", Aventuria.MOD_ID, "text.empty_table_helper"));
            String[] parts = string.split("\n");
            for (int i = 0; i < parts.length; i++) {
                drawScaledCenteredString(pPoseStack, (relX + 30) - (font.width(parts[0]) / 2), (relY - 68) + (i * font.lineHeight), .8f, parts[i], 0xFFFFFF);
            }
        }
    }


    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
    }

    private void drawScaledCenteredString(PoseStack matrices, int x, int y, float scale, String textComponent, int color) {
        matrices.pushPose();
        matrices.translate(x, y, 0);
        matrices.scale(0.5f, 0.5f, 0.5f);
        drawString(matrices, font, textComponent, 0, 0, color);
        matrices.popPose();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(stack, relX - 23, relY, 0, 0, this.imageWidth + 23, this.imageHeight + 20);
    }

    @Override
    public void init() {
        super.init();

        this.scrollingUpgrades = new ScrollingUpgrades(Minecraft.getInstance(), this.imageWidth - 14, 70, topPos + 7, leftPos + 7, this);
        this.addRenderableWidget(this.scrollingUpgrades);
    }

    @Override
    public boolean mouseClicked(double mouseXIn, double mouseYIn, int p_231044_5_) {
        ItemStack heldStack = this.menu.getCarried();
        ItemStack toolItem = this.menu.slots.get(0).getItem();
        if (pickaxePresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        if (shovelPresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        if (axePresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        if (hoePresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        if (swordPresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        if (armorPresent(mouseXIn, mouseYIn, heldStack, toolItem)) return false;
        return super.mouseClicked(mouseXIn, mouseYIn, p_231044_5_);
    }

    private boolean pickaxePresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomPickaxeItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private boolean shovelPresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomShovelItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private boolean axePresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomAxeItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private boolean hoePresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomHoeItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private boolean swordPresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomSwordItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private boolean armorPresent(double mouseXIn, double mouseYIn, ItemStack heldStack, ItemStack toolItem) {
        if (!toolItem.isEmpty() && toolItem.getItem() instanceof CustomArmorItem && !heldStack.isEmpty() && heldStack.getItem() instanceof UpgradeCardItem) {
            if (scrollingUpgrades.isMouseOver(mouseXIn, mouseYIn)) {
                // Send packet to remove the item from the inventory and add it to the table
                if (UpgradeTools.containsUpgrade(toolItem, ((UpgradeCardItem) heldStack.getItem()).getCard())) {
                    return true;
                }

                PacketHandler.sendToServer(new PacketInsertUpgrade(this.tePos, heldStack));
                this.menu.setCarried(ItemStack.EMPTY);
            }
        }
        return false;
    }

    private static class ScrollingUpgrades extends ScrollPanel implements NarratableEntry {
        UpgradeStationScreen parent;
        Upgrade upgrade = null;



        ScrollingUpgrades(Minecraft client, int width, int height, int top, int left, UpgradeStationScreen parent) {
            super(client, width, height, top, left);
            this.parent = parent;
        }

        // Fixes a forge bug where the screen will screen when no scroll is available
        @Override
        public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
            if (this.getContentHeight() < this.height)
                return false;

            return super.mouseScrolled(mouseX, mouseY, scroll);
        }

        @Override
        protected int getContentHeight() {
            return (int) Math.ceil(this.parent.menu.getUpgradesCache().size() / 7f) * 20;
        }

        @Override
        protected void drawPanel(PoseStack mStack, int entryRight, int relativeY, Tesselator tess, int mouseX, int mouseY) {
            Upgrade currentUpgrade = null;
            int x = (entryRight - this.width) + 3;
            int y = relativeY;

            int index = 0;
            for (Upgrade upgrade : this.parent.menu.getUpgradesCache()) {
                Minecraft.getInstance().getItemRenderer().renderGuiItem(new ItemStack(upgrade.getCard()), x, y);

                if (isMouseOver(mouseX, mouseY) && (mouseX > x && mouseX < x + 15 && mouseY > y && mouseY < y + 15))
                    currentUpgrade = upgrade;

                x += 22;
                index ++;
                if( index % 7 == 0 ) {
                    y += 20;
                    x = (entryRight - this.width) + 3;
                }
            }

            if(currentUpgrade == null || !currentUpgrade.equals(this.upgrade))
                this.upgrade = currentUpgrade;
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if( !isMouseOver(mouseX, mouseY) || this.upgrade == null )
                return false;

            PacketHandler.sendToServer(new PacketExtractUpgrade(this.parent.tePos, this.upgrade.getName(), this.upgrade.getName().length()));
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
            super.render(stack, mouseX, mouseY, partialTicks);

            if( this.upgrade != null  )
                this.parent.renderTooltip(stack, Lists.transform(this.upgrade.getStack().getTooltipLines(this.parent.getMinecraft().player, TooltipFlag.Default.NORMAL), Component::getVisualOrderText), mouseX, mouseY);
        }

        @Override
        public NarrationPriority narrationPriority() {
            return NarrationPriority.HOVERED;
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
        }
    }
}