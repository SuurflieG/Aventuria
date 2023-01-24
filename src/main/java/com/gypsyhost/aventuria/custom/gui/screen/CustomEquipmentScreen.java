package com.gypsyhost.aventuria.custom.gui.screen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.widgets.EquipmentSelectButton;
import com.gypsyhost.aventuria.custom.gui.widgets.ToggleButton;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeHelper;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.network.PacketHandler;
import com.gypsyhost.aventuria.network.packets.PacketUpdateUpgrade;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEquipmentScreen extends Screen {

    private final ItemStack equipment;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Aventuria.MOD_ID, "textures/gui/equipment_gui.png");
    public List<EquipmentSelectButton> equipmentSelectButton = new ArrayList<>();
    public List<Upgrade> toggleableList = new ArrayList<>();
    public static HashMap<Upgrade, ToggleButton> toggleButton = new HashMap<>();
    public static HashMap<ItemStack, EquipmentSelectButton> equipButton = new HashMap<>();
    public static ToggleButton Btn;
    public static EquipmentSelectButton equipBtn;

    int imageHeight = 157;
    int imageWidth = 255;

    public CustomEquipmentScreen(Component pTitle, ItemStack equipment) {
        super(pTitle);
        this.equipment = equipment;
    }



    @Override
    public void init() {

        List<AbstractWidget> rightWidgets = new ArrayList<>();

        int baseX = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
        int baseY = (height / 2 ) - (imageHeight / 2);

/*        EquipmentSelectButton pickaxe;
        EquipmentSelectButton shovel;
        EquipmentSelectButton axe;
        EquipmentSelectButton hoe;
        EquipmentSelectButton helmet;
        EquipmentSelectButton chestplate;
        EquipmentSelectButton leggings;
        EquipmentSelectButton boots;*/

        for (EquipmentSelectButton equipButtonBtn : equipmentSelectButton) {
            equipButtonBtn = new EquipmentSelectButton(baseX + 203,baseY + 12, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send));
            addRenderableWidget(equipButtonBtn);
            equipButton.put(equipment, equipBtn);
        }

/*
        rightWidgets.add(shovel = new EquipmentSelectButton(baseX + 203,baseY + 27, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(axe = new EquipmentSelectButton(baseX + 203,baseY + 42, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(hoe = new EquipmentSelectButton(baseX + 203,baseY + 57, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(helmet = new EquipmentSelectButton(baseX + 203,baseY + 72, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(chestplate = new EquipmentSelectButton(baseX + 203,baseY + 87, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(leggings = new EquipmentSelectButton(baseX + 203,baseY + 102, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
        rightWidgets.add(boots = new EquipmentSelectButton(baseX + 203,baseY + 117, this.getName(equipment), (new ResourceLocation(Aventuria.MOD_ID, "textures/gui/select_button.png")), send -> this.selectEquipment(equipment, send)));
*/

        super.init();
    }

    private void pickaxeScreen(int baseX, int baseY) {
        // Bottom Row
        // Remove 6 from x to center it as the padding on the right pushes off center
        int index = 0, x = baseX + 13, y = baseY + 98;
        for (Upgrade upgrade : toggleableList) {
            Btn = new ToggleButton(x + (index * 18), y, UpgradeTools.getName(upgrade), new ResourceLocation(Aventuria.MOD_ID, "textures/item/upgrade_" + upgrade.getName() + ".png"), send -> this.toggleUpgrade(upgrade, send));
            addRenderableWidget(Btn);
            toggleButton.put(upgrade, Btn);

            // Spaces the upgrades
            index ++;
            if( index % 9 == 0 ) {
                index = 0;
                y += 20;
            }
        }
    }

    public boolean selectEquipment(ItemStack equipment, boolean select){
        if(select){
            isPickaxe(equipment);
        }
        return select;
    }

    //TODO work on adding the EquipmentSelectButton when equipment is in inventory or when armor is equipped and draw buttons for each

    @Override
    public void renderBackground(PoseStack pPoseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width / 2 )  - (imageWidth / 2);/* find center of screen */ /*move image over by half its width */
        int y = (height / 2 ) - (imageHeight / 2);
        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    private static void isPickaxe(ItemStack tool) {
        tool.getItem();
    }
    private static boolean isShovel(ItemStack tool) {
        return tool.getItem() instanceof CustomShovelItem;
    }
    private static boolean isAxe(ItemStack tool) {
        return tool.getItem() instanceof CustomAxeItem;
    }
    private static boolean isHoe(ItemStack tool) {
        return tool.getItem() instanceof CustomHoeItem;
    }
    private static boolean isArmor(ItemStack tool) {
        return tool.getItem() instanceof CustomArmorItem;
    }
    private static boolean isSword(ItemStack tool) {
        return tool.getItem() instanceof CustomSwordItem;
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, partialTicks);

        this.children().forEach(e -> {
            if (e instanceof EquipmentSelectButton btn){
                if (btn.isMouseOver(mouseX, mouseY))
                    renderTooltip(pPoseStack, btn.getTooltip(), mouseX, mouseY);
            }
        });
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public Component getName(ItemStack equipment) {
        return new TextComponent("test" + equipment);
    }

    public boolean toggleUpgrade(Upgrade upgrade, boolean update) {
        // When the button is clicked we toggle
        if(update){
            updateButtons(upgrade, equipment);
            PacketHandler.sendToServer(new PacketUpdateUpgrade(upgrade.getName()));
        }
        // When we're just init the gui, we check if it's on or off.
        return upgrade.isEnabled();
    }

    public static void updateButtons(Upgrade upgrade, ItemStack tool) {
        for(Map.Entry<Upgrade, ToggleButton> btn : toggleButton.entrySet()) {
            Upgrade btnUpgrade = btn.getKey();

            if((btnUpgrade.lazyIs(Upgrade.FORTUNE_1) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.SILK)) || ((btnUpgrade.lazyIs(Upgrade.SILK)) && btn.getValue().isEnabled() && upgrade.lazyIs(Upgrade.FORTUNE_1))) {
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
        if((btnUpgrade.lazyIs(Upgrade.EFFICIENCY_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyEfficiency(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.EFFICIENCY_3) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeEfficiency(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.FORTUNE_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyFortune(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.FORTUNE_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeFortune(tool);
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
        if((btnUpgrade.lazyIs(Upgrade.SILK) && btn.getValue().isEnabled())){
            UpgradeHelper.applySilkTouch(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.SILK) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeSilkTouch(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.UNBREAKING_1) && btn.getValue().isEnabled())){
            UpgradeHelper.applyUnbreaking(tool);
        }
        if((btnUpgrade.lazyIs(Upgrade.UNBREAKING_1) && !btn.getValue().isEnabled())){
            UpgradeHelper.removeUnbreaking(tool);
        }
    }

}
