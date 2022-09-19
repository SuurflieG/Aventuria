package com.gypsyhost.aventuria.custom.gui.screen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.widgets.ToggleButton;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomSwordItem;
import com.gypsyhost.aventuria.custom.item.tool.UpgradeHelper;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.network.PacketHandler;
import com.gypsyhost.aventuria.network.packets.PacketUpdateUpgrade;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("InstantiationOfUtilityClass")
public class CustomWeaponScreen extends Screen {
    private final ItemStack customWeaponItem;

    public List<Upgrade> toggleableList = new ArrayList<>();
    public static HashMap<Upgrade, ToggleButton> toggleButton = new HashMap<>();
    public static ToggleButton Btn;

    public CustomWeaponScreen(ItemStack customWeaponItem) {
        super(new TextComponent("title"));

        this.customWeaponItem = customWeaponItem;
    }

    @Override
    public void init() {
        List<AbstractWidget> leftWidgets = new ArrayList<>();

        int baseX = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
        int baseY = (height / 2 ) - (imageHeight / 2);

        // Filters out the non-toggleable options
        toggleableList.clear();
        toggleableList = UpgradeTools.getUpgrades(this.customWeaponItem).stream().filter(Upgrade::isToggleable).collect(Collectors.toList());

        // Bottom Row
        // Remove 6 from x to center it as the padding on the right pushes off center
        int index = 0, x = baseX + 13, y = baseY + 98;
        for (Upgrade upgrade : toggleableList) {
            Btn = new ToggleButton(x + (index * 18), y, UpgradeTools.getName(upgrade),
                    new ResourceLocation(Aventuria.MOD_ID, "textures/item/upgrade_" + upgrade.getName() + ".png"), send -> this.toggleUpgrade(upgrade, send));
            addRenderableWidget(Btn);
            toggleButton.put(upgrade, Btn);

            // Spaces the upgrades
            index ++;
            if( index % 9 == 0 ) {
                index = 0;
                y += 20;
            }
        }

        // Lay the buttons out, too lazy to figure out the math every damn time.
        // Ordered by where you add them.
        for(int i = 0; i < leftWidgets.size(); i ++) {
            leftWidgets.get(i).y = (baseY + 20) + (i * 25);
            addRenderableWidget(leftWidgets.get(i));
        }
    }

    public boolean toggleUpgrade(Upgrade upgrade, boolean update) {
        // When the button is clicked we toggle
        if(update){
            updateButtons(upgrade, customWeaponItem);
            PacketHandler.sendToServer(new PacketUpdateUpgrade(upgrade.getName()));
        }
        // When we're just init the gui, we check if it's on or off.
        return upgrade.isEnabled();
    }

    public static void updateButtons(Upgrade upgrade, ItemStack tool) {
        for(Map.Entry<Upgrade, ToggleButton> btn : toggleButton.entrySet()) {
            Upgrade btnUpgrade = btn.getKey();

            if((btnUpgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.SHARPNESS_1))
            || ((btnUpgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.SMITE_1)))
            || ((btnUpgrade.lazyIs(Upgrade.SHARPNESS_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1)))
            || ((btnUpgrade.lazyIs(Upgrade.SHARPNESS_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.SMITE_1)))
            || ((btnUpgrade.lazyIs(Upgrade.SMITE_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1)))
            || ((btnUpgrade.lazyIs(Upgrade.SMITE_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.SHARPNESS_1)))) {
                toggleButton.get(btn.getKey()).setEnabled(false);
            }

            toggleUpgradeEnchants(tool, btn, btnUpgrade);
        }
    }

    private static void toggleUpgradeEnchants(ItemStack tool, Map.Entry<Upgrade, ToggleButton> btn, Upgrade btnUpgrade) {
        if((btnUpgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyBaneOfArthropods(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeBaneOfArthropods(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.FIRE_ASPECT_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyFireAspect(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.FIRE_ASPECT_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeFireAspect(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.KNOCKBACK_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyKnockback(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.KNOCKBACK_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeKnockback(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.MOB_LOOTING_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyMobLooting(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.MOB_LOOTING_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeMobLooting(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.MENDING) && btn.getValue().isEnabled())){
            UpgradeHelper.applyMending(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.MENDING) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeMending(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SHARPNESS_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applySharpness(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SHARPNESS_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeSharpness(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SMITE_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applySmite(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SMITE_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeSmite(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SWEEPING_EDGE_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applySweepingEdge(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SWEEPING_EDGE_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeSweepingEdge(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.UNBREAKING_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyUnbreaking(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.UNBREAKING_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeUnbreaking(tool);
        }

    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/tool_pickaxe_screen.png");

    int imageHeight = 121;
    int imageWidth = 186;

    @Override
    public void renderBackground(PoseStack pPoseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
        int y = (height / 2 ) - (imageHeight / 2);

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        if(isSword(customWeaponItem)){
            drawString(pPoseStack, getMinecraft().font, getTrans("tooltip.screen.sword_settings"), x + 12, y + 10, Color.ORANGE.getRGB());
            drawString(pPoseStack, getMinecraft().font, getTrans("tooltip.screen.toggle_upgrades"), x + 12, y + 75, Color.ORANGE.getRGB());
        }

        if(toggleableList.size() == 0)
            drawString(pPoseStack, getMinecraft().font, getTrans("tooltip.screen.no_upgrades"), x + 12, y + 85, Color.RED.brighter().getRGB());

    }

    private static boolean isSword(ItemStack tool) {
        return tool.getItem() instanceof CustomSwordItem;
    }


    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, partialTicks);

        this.children().forEach(e -> {
            if (e instanceof ToggleButton){
                ToggleButton btn = ((ToggleButton) e);
                if (btn.isMouseOver(mouseX, mouseY))
                    renderTooltip(pPoseStack, btn.getTooltip(), mouseX, mouseY);
            }
        });
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        InputConstants.Key mouseKey = InputConstants.getKey(p_keyPressed_1_, p_keyPressed_2_);
        if (p_keyPressed_1_ == 256 || minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
            onClose();

            return true;
        }

        return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }

    private static TranslatableComponent getTrans(String key, Object... args) {
        return new TranslatableComponent(Aventuria.MOD_ID + "." + key, args);
    }
}