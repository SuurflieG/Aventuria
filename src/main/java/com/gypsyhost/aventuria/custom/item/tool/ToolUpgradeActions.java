package com.gypsyhost.aventuria.custom.item.tool;

import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class ToolUpgradeActions {

    public ToolUpgradeActions() {

    }




/*    public void test(ItemStack pStack, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        ItemStack tempTool = pEntityLiving.getMainHandItem();
        if ((isPickaxe(tempTool) || isShovel(tempTool))) {
            int silk = 0;
            int fortune = 0;

            if (UpgradeTools.containsActiveUpgradeFromList(toolUpgrades, Upgrade.SILK)) {
                tempTool.enchant(Enchantments.SILK_TOUCH, 1);
                silk = 1;
            }

            if (UpgradeTools.containsActiveUpgradeFromList(toolUpgrades, Upgrade.FORTUNE_1)) {
                Optional<Upgrade> upgrade = UpgradeTools.getUpgradeFromList(toolUpgrades, Upgrade.FORTUNE_1);
                if (upgrade.isPresent()) {
                    fortune = upgrade.get().getTier();
                    tempTool.enchant(Enchantments.BLOCK_FORTUNE, fortune);
                }
            }

            if (!level.isClientSide) {
                int efficiency = 0;
                if (UpgradeTools.containsActiveUpgrade(pStack, Upgrade.EFFICIENCY_1))
                    efficiency = UpgradeTools.getUpgradeFromTool(pStack, Upgrade.EFFICIENCY_1).get().getTier();
                tempTool.enchant(Enchantments.BLOCK_EFFICIENCY, efficiency);


                setToolUpgrades(toolUpgrades);

                List<ItemStack> drops = Block.getDrops(pState, level, pPos, null, pEntityLiving, tempTool);
                for (ItemStack drop : drops) {
                    if (drop != null) {
                        int wasPickedUp = ForgeEventFactory.onItemPickup(new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), drop), player);
                        // 1  = someone allowed the event meaning it's handled,
                        // -1 = someone blocked the event and thus we shouldn't drop it nor insert it
                        // 0  = no body captured the event and we should handle it by hand.
                        if (wasPickedUp == 0) {
                            if (!player.addItem(drop)) {
                                Block.popResource(level, worldPosition, drop);
                            }
                        }
                    } else {
                    assert false;
                    Block.popResource(level, worldPosition, drop);
                    }

                }
            }
        }
    }*/



    private static boolean isPickaxe (ItemStack tool){
        return tool.getItem() instanceof CustomPickaxeItem;
    }

    private static boolean isShovel (ItemStack tool){
        return tool.getItem() instanceof CustomShovelItem;
    }
}

