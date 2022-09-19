package com.gypsyhost.aventuria.custom.item.tool;


import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.util.OurKeys;
import com.gypsyhost.aventuria.registry.ModScreens;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;


public class CustomHoeItem extends HoeItem {


    public CustomHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public static ItemStack getHoe(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof CustomHoeItem)) {
            heldItem = player.getOffhandItem();
            if (!(heldItem.getItem() instanceof CustomHoeItem)) {
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

    public static void changeRange(ItemStack tool) {
        if (ToolMiningProperties.getMiningSize(tool) == 1)
            ToolMiningProperties.setMiningSize(tool, 3);
        else
            ToolMiningProperties.setMiningSize(tool, 1);
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
                    ModScreens.openToolSettingsScreen(itemstack);
                    return InteractionResultHolder.pass(itemstack);
                }
            }

            return InteractionResultHolder.pass(itemstack);
        }

        pPlayer.startUsingItem(pUsedHand);
        return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
    }

}
