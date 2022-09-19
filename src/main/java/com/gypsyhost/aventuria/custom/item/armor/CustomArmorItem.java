package com.gypsyhost.aventuria.custom.item.armor;

import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.util.OurKeys;
import com.gypsyhost.aventuria.registry.ModScreens;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//TODO Figure out how to setup hotkey G to open menu for armor when its equiped, if its held in the hand then shift right click

public class CustomArmorItem extends ArmorItem {


    public CustomArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    public static ItemStack getArmor(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof CustomArmorItem)) {
            heldItem = player.getOffhandItem();
            if (!(heldItem.getItem() instanceof CustomArmorItem)) {
                return ItemStack.EMPTY;
            }
        }

        return heldItem;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack pStack, ItemStack pBook) {
        return false;
    }

    public static void applyUpgrade(ItemStack tool, UpgradeCardItem upgradeCardItem) {
        if (UpgradeTools.containsActiveUpgrade(tool, upgradeCardItem.getCard()))
            return;

        UpgradeTools.setUpgrade(tool, upgradeCardItem);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

        // Only perform the shift action
        if (pPlayer.isShiftKeyDown()) {
            if (pLevel.isClientSide) {
                if (OurKeys.shiftClickGuiBinding.getKey() == InputConstants.UNKNOWN) {
                    ModScreens.openArmorSettingsScreen(itemstack);
                    return InteractionResultHolder.pass(itemstack);
                }
            }

            return InteractionResultHolder.pass(itemstack);
        }

        pPlayer.startUsingItem(pUsedHand);
        return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
    }
}
