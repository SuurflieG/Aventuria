package com.gypsyhost.aventuria.custom.item.upgradecards;

import com.gypsyhost.aventuria.registry.ModCreativeModeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpgradeCardItem extends Item {

    public final Upgrade upgradeCard;

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pStack.getItem() instanceof UpgradeCardItem) {
            if (upgradeCard == Upgrade.SILK)
                pTooltipComponents.add(new TranslatableComponent("aventuria.tooltip.upgrades.silk_touch", Upgrade.SILK.getBaseName()).withStyle(ChatFormatting.GREEN));
            if (upgradeCard == Upgrade.FORTUNE_1)
                pTooltipComponents.add(new TranslatableComponent("aventuria.tooltip.upgrades.fortune_1", Upgrade.FORTUNE_1.getBaseName()).withStyle(ChatFormatting.GREEN));
            if (upgradeCard == Upgrade.FORTUNE_2)
                pTooltipComponents.add(new TranslatableComponent("aventuria.tooltip.upgrades.fortune_2", Upgrade.FORTUNE_2.getBaseName()).withStyle(ChatFormatting.GREEN));
            if (upgradeCard == Upgrade.FORTUNE_3)
                pTooltipComponents.add(new TranslatableComponent("aventuria.tooltip.upgrades.fortune_3", Upgrade.FORTUNE_3.getBaseName()).withStyle(ChatFormatting.GREEN));

            pTooltipComponents.add(new TranslatableComponent(this.upgradeCard.getToolTip()).withStyle(ChatFormatting.GREEN));
        }
    }



    public UpgradeCardItem(Upgrade upgrade){

        super(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB));
        this.upgradeCard = upgrade;
    }

    public Upgrade getCard() {
        return upgradeCard;
    }

}
