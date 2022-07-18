package com.gypsyhost.aventuria.custom.gui.upgradestation;

import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.item.tool.ToolMiningProperties;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class UpgradeStationCommands {

    public static boolean insertButton(UpgradeStationMenu container, ItemStack upgrade) {
        Slot upgradeSlot = container.slots.get(0);
        ItemStack tool = upgradeSlot.getItem();

        if (isPickaxe(upgrade, tool) || isShovel(upgrade, tool)) {
            Upgrade card = ((UpgradeCardItem) upgrade.getItem()).getCard();
            if (card == Upgrade.BLANK)
                return false; //Don't allow inserting empty cards.

            List<Upgrade> upgrades = UpgradeTools.getUpgrades(tool);

            // Fortune has to be done slightly differently as it requires us to check
            // against all fortune tiers and not just it's existence.
            boolean hasFortune = UpgradeTools.containsUpgradeFromList(upgrades, Upgrade.FORTUNE_1);
            boolean hasSilk = UpgradeTools.containsUpgradeFromList(upgrades, Upgrade.SILK);

            if (UpgradeTools.containsUpgrade(tool, card))
                return false;

            if (hasFortune && card.getBaseName().equals(Upgrade.SILK.getBaseName()) || hasSilk && card.getBaseName().equals(Upgrade.FORTUNE_1.getBaseName()))
                ((UpgradeCardItem) upgrade.getItem()).getCard().setEnabled(false);

            CustomPickaxeItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());
            CustomShovelItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());


            return true;
        }

        return false;
    }

    public static void extractButton(UpgradeStationMenu container, ServerPlayer player, String upgradeName) {
        Slot upgradeSlot = container.slots.get(0);
        ItemStack tool = upgradeSlot.getItem();

        if (!isPickaxe(tool) || !isShovel(tool))
            return;

        if (!UpgradeTools.containsUpgrades(tool))
            return;

        UpgradeTools.getUpgrades(tool).forEach(upgrade -> {
            if(!upgrade.getName().equals(upgradeName))
                return;

            UpgradeTools.removeUpgrade(tool, upgrade);

            boolean success = player.getInventory().add(new ItemStack(upgrade.getCard(), 1));
            if (!success) {
                player.drop(new ItemStack(upgrade.getCard(), 1), true);
            }

            if (upgrade == Upgrade.THREE_BY_THREE)
                ToolMiningProperties.setRange(tool, 1);

        });
    }

    private static boolean isPickaxe(ItemStack upgrade, ItemStack tool) {
        return tool.getItem() instanceof CustomPickaxeItem && upgrade.getItem() instanceof UpgradeCardItem;
    }

    private static boolean isShovel(ItemStack upgrade, ItemStack tool) {
        return tool.getItem() instanceof CustomShovelItem && upgrade.getItem() instanceof UpgradeCardItem;
    }

    private static boolean isPickaxe(ItemStack tool) {
        return tool.getItem() instanceof CustomPickaxeItem;
    }

    private static boolean isShovel(ItemStack tool) {
        return tool.getItem() instanceof CustomShovelItem;
    }
}