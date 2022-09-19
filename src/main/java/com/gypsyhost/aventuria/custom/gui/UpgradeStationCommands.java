package com.gypsyhost.aventuria.custom.gui;

import com.gypsyhost.aventuria.custom.gui.menu.UpgradeStationMenu;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeHelper;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class UpgradeStationCommands {

    public static boolean insertButton(UpgradeStationMenu container, ItemStack upgrade) {
        Slot upgradeSlot = container.slots.get(0);
        ItemStack tool = upgradeSlot.getItem();

        if (tool.getItem() instanceof CustomPickaxeItem
            || tool.getItem() instanceof CustomShovelItem
            || tool.getItem() instanceof CustomAxeItem
            || tool.getItem() instanceof CustomHoeItem
            || tool.getItem() instanceof CustomSwordItem
            || tool.getItem() instanceof CustomArmorItem && upgrade.getItem() instanceof UpgradeCardItem) {
            Upgrade card = ((UpgradeCardItem) upgrade.getItem()).getCard();
            if (card == Upgrade.BLANK)
                return false; //Don't allow inserting empty cards.

            if(tool.getItem() instanceof CustomSwordItem &&
                    card == Upgrade.SILK
                    || card == Upgrade.SOUL_SPEED_1){
                return false;
            }

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
/*            CustomShovelItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());
            CustomAxeItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());
            CustomHoeItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());
            CustomSwordItem.applyUpgrade(tool, (UpgradeCardItem) upgrade.getItem());*/

            applyUpgradeWhenInserted(tool, card);

            return true;
        }

        return false;
    }

    public static void extractButton(UpgradeStationMenu container, ServerPlayer player, String upgradeName) {
        Slot upgradeSlot = container.slots.get(0);
        ItemStack tool = upgradeSlot.getItem();

        if (!(tool.getItem() instanceof CustomPickaxeItem)
                && !(tool.getItem() instanceof CustomShovelItem)
                && !(tool.getItem() instanceof CustomAxeItem)
                && !(tool.getItem() instanceof CustomHoeItem)
                && !(tool.getItem() instanceof CustomSwordItem)
                && !(tool.getItem() instanceof CustomArmorItem))
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

            removeUpgradeWhenRemoved(tool, upgrade);

        });
    }

    private static void applyUpgradeWhenInserted(ItemStack tool, Upgrade card) {
        //boolean flag = card.isEnabled();

        if(card == Upgrade.THREE_BY_THREE)
            ToolMiningProperties.setMiningSize(tool, 1);
        if(card == Upgrade.MENDING){
            UpgradeHelper.applyMending(tool);
        }
        if(card == Upgrade.AQUA_AFFINITY){
            UpgradeHelper.applyAquaAffinity(tool);
        }
        if(card == Upgrade.BANE_OF_ARTHROPODS_1 || card == Upgrade.BANE_OF_ARTHROPODS_2 || card == Upgrade.BANE_OF_ARTHROPODS_3 || card == Upgrade.BANE_OF_ARTHROPODS_4 || card == Upgrade.BANE_OF_ARTHROPODS_5){
            UpgradeHelper.applyBaneOfArthropods(tool);
        }
        if(card == Upgrade.BLAST_PROTECTION_1 || card == Upgrade.BLAST_PROTECTION_2 || card == Upgrade.BLAST_PROTECTION_3 || card == Upgrade.BLAST_PROTECTION_4){
            card.setEnabled(false);
        }
        if(card == Upgrade.DEPTH_STRIDER_1 || card == Upgrade.DEPTH_STRIDER_2 || card == Upgrade.DEPTH_STRIDER_3){
            UpgradeHelper.applyDepthStrider(tool);
        }
        if(card == Upgrade.EFFICIENCY_1 || card == Upgrade.EFFICIENCY_2 || card == Upgrade.EFFICIENCY_3 || card == Upgrade.EFFICIENCY_4 || card == Upgrade.EFFICIENCY_5){
            UpgradeHelper.applyEfficiency(tool);
        }
        if(card == Upgrade.FEATHER_FALLING_1 || card == Upgrade.FEATHER_FALLING_2 || card == Upgrade.FEATHER_FALLING_3 || card == Upgrade.FEATHER_FALLING_4){
            UpgradeHelper.applyFeatherFalling(tool);
        }
        if(card == Upgrade.FIRE_ASPECT_1 || card == Upgrade.FIRE_ASPECT_2){
            UpgradeHelper.applyFireAspect(tool);
        }
        if(card == Upgrade.FIRE_PROTECTION_1 || card == Upgrade.FIRE_PROTECTION_2 || card == Upgrade.FIRE_PROTECTION_3 || card == Upgrade.FIRE_PROTECTION_4){
            card.setEnabled(false);
        }
        if(card == Upgrade.FORTUNE_1 || card == Upgrade.FORTUNE_2 || card == Upgrade.FORTUNE_3){
            UpgradeHelper.applyFortune(tool);
        }
        if(card == Upgrade.FROST_WALKER_1 || card == Upgrade.FROST_WALKER_2){
            UpgradeHelper.applyFrostWalker(tool);
        }
        if(card == Upgrade.KNOCKBACK_1 || card == Upgrade.KNOCKBACK_2){
            UpgradeHelper.applyKnockback(tool);
        }
        if(card == Upgrade.MOB_LOOTING_1 || card == Upgrade.MOB_LOOTING_2 || card == Upgrade.MOB_LOOTING_3){
            UpgradeHelper.applyMobLooting(tool);
        }
        if(card == Upgrade.PROJECTILE_PROTECTION_1 || card == Upgrade.PROJECTILE_PROTECTION_2 || card == Upgrade.PROJECTILE_PROTECTION_3 || card == Upgrade.PROJECTILE_PROTECTION_4){
            card.setEnabled(false);
        }
        if(card == Upgrade.PROTECTION_1 || card == Upgrade.PROTECTION_2 || card == Upgrade.PROTECTION_3 || card == Upgrade.PROTECTION_4){
            card.setEnabled(false);
        }
        if(card == Upgrade.RESPIRATION_1 || card == Upgrade.RESPIRATION_2 || card == Upgrade.RESPIRATION_3){
            UpgradeHelper.applyMobLooting(tool);
        }
        if(card == Upgrade.SHARPNESS_1 || card == Upgrade.SHARPNESS_2 || card == Upgrade.SHARPNESS_3 || card == Upgrade.SHARPNESS_4 || card == Upgrade.SHARPNESS_5){
            UpgradeHelper.applySharpness(tool);
        }
        if(card == Upgrade.SMITE_1 || card == Upgrade.SMITE_2 || card == Upgrade.SMITE_3 || card == Upgrade.SMITE_4 || card == Upgrade.SMITE_5){
            UpgradeHelper.applySmite(tool);
        }
        if(card == Upgrade.SOUL_SPEED_1 || card == Upgrade.SOUL_SPEED_2 || card == Upgrade.SOUL_SPEED_3){
            UpgradeHelper.applySoulSpeed(tool);
        }
        if(card == Upgrade.SWEEPING_EDGE_1 || card == Upgrade.SWEEPING_EDGE_2 || card == Upgrade.SWEEPING_EDGE_3){
            UpgradeHelper.applySweepingEdge(tool);
        }
        if(card == Upgrade.THORNS_1 || card == Upgrade.THORNS_2 || card == Upgrade.THORNS_3){
            UpgradeHelper.applyThorns(tool);
        }
        if(card == Upgrade.UNBREAKING_1 || card == Upgrade.UNBREAKING_2 || card == Upgrade.UNBREAKING_3){
            UpgradeHelper.applyUnbreaking(tool);
        }

        if(card == Upgrade.SILK){
            card.setEnabled(false);
        }
    }

    private static void removeUpgradeWhenRemoved(ItemStack tool, Upgrade card) {
        //boolean flag = card.isEnabled();

        if(card == Upgrade.THREE_BY_THREE)
            ToolMiningProperties.setMiningSize(tool, 1);
        if(card == Upgrade.MENDING){
            UpgradeHelper.removeMending(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.AQUA_AFFINITY){
            UpgradeHelper.removeAquaAffinity(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.BANE_OF_ARTHROPODS_1 || card == Upgrade.BANE_OF_ARTHROPODS_2 || card == Upgrade.BANE_OF_ARTHROPODS_3 || card == Upgrade.BANE_OF_ARTHROPODS_4 || card == Upgrade.BANE_OF_ARTHROPODS_5){
            UpgradeHelper.removeBaneOfArthropods(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.BLAST_PROTECTION_1 || card == Upgrade.BLAST_PROTECTION_2 || card == Upgrade.BLAST_PROTECTION_3 || card == Upgrade.BLAST_PROTECTION_4){
            UpgradeHelper.removeBlastProtection(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.DEPTH_STRIDER_1 || card == Upgrade.DEPTH_STRIDER_2 || card == Upgrade.DEPTH_STRIDER_3){
            UpgradeHelper.removeDepthStrider(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.EFFICIENCY_1 || card == Upgrade.EFFICIENCY_2 || card == Upgrade.EFFICIENCY_3 || card == Upgrade.EFFICIENCY_4 || card == Upgrade.EFFICIENCY_5){
            UpgradeHelper.removeEfficiency(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.FEATHER_FALLING_1 || card == Upgrade.FEATHER_FALLING_2 || card == Upgrade.FEATHER_FALLING_3 || card == Upgrade.FEATHER_FALLING_4){
            UpgradeHelper.removeFeatherFalling(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.FIRE_ASPECT_1 || card == Upgrade.FIRE_ASPECT_2){
            UpgradeHelper.removeFireAspect(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.FIRE_PROTECTION_1 || card == Upgrade.FIRE_PROTECTION_2 || card == Upgrade.FIRE_PROTECTION_3 || card == Upgrade.FIRE_PROTECTION_4){
            UpgradeHelper.removeFireProtection(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.FORTUNE_1 || card == Upgrade.FORTUNE_2 || card == Upgrade.FORTUNE_3){
            UpgradeHelper.removeFortune(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.FROST_WALKER_1 || card == Upgrade.FROST_WALKER_2){
            UpgradeHelper.removeFrostWalker(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.KNOCKBACK_1 || card == Upgrade.KNOCKBACK_2){
            UpgradeHelper.removeKnockback(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.MOB_LOOTING_1 || card == Upgrade.MOB_LOOTING_2 || card == Upgrade.MOB_LOOTING_3){
            UpgradeHelper.removeMobLooting(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.PROJECTILE_PROTECTION_1 || card == Upgrade.PROJECTILE_PROTECTION_2 || card == Upgrade.PROJECTILE_PROTECTION_3 || card == Upgrade.PROJECTILE_PROTECTION_4){
            UpgradeHelper.removeProjectileProtection(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.PROTECTION_1 || card == Upgrade.PROTECTION_2 || card == Upgrade.PROTECTION_3 || card == Upgrade.PROTECTION_4){
            UpgradeHelper.removeProtection(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.RESPIRATION_1 || card == Upgrade.RESPIRATION_2 || card == Upgrade.RESPIRATION_3){
            UpgradeHelper.removeMobLooting(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.SHARPNESS_1 || card == Upgrade.SHARPNESS_2 || card == Upgrade.SHARPNESS_3 || card == Upgrade.SHARPNESS_4 || card == Upgrade.SHARPNESS_5){
            UpgradeHelper.removeSharpness(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.SMITE_1 || card == Upgrade.SMITE_2 || card == Upgrade.SMITE_3 || card == Upgrade.SMITE_4 || card == Upgrade.SMITE_5){
            UpgradeHelper.removeSmite(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.SOUL_SPEED_1 || card == Upgrade.SOUL_SPEED_2 || card == Upgrade.SOUL_SPEED_3){
            UpgradeHelper.removeSoulSpeed(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.SWEEPING_EDGE_1 || card == Upgrade.SWEEPING_EDGE_2 || card == Upgrade.SWEEPING_EDGE_3){
            UpgradeHelper.removeSweepingEdge(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.THORNS_1 || card == Upgrade.THORNS_2 || card == Upgrade.THORNS_3){
            UpgradeHelper.removeThorns(tool);
            card.setEnabled(true);
        }
        if(card == Upgrade.UNBREAKING_1 || card == Upgrade.UNBREAKING_2 || card == Upgrade.UNBREAKING_3){
            UpgradeHelper.removeUnbreaking(tool);
            card.setEnabled(true);
        }

        if(card == Upgrade.SILK){
            UpgradeHelper.removeSilkTouch(tool);
            card.setEnabled(false);
        }
    }
}