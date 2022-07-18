package com.gypsyhost.aventuria.custom.item.tool;


import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.util.OurKeys;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.registry.ModScreens;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;


public class CustomPickaxeItem extends PickaxeItem {



    private List<Upgrade> toolUpgrades;

    public CustomPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }



/*    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        //Server and Client side
        Level world = player.level;


        int range = 2;
        BlockHitResult lookingAt = VectorHelper.getLookingAt((Player) player, ClipContext.Fluid.NONE, range);
        if (world.getBlockState(VectorHelper.getLookingAt((Player) player, stack, range).getBlockPos()) == Blocks.AIR.defaultBlockState())
            return;

        List<BlockPos> coords = MiningCollect.collect((Player) player, lookingAt, world, ToolMiningProperties.getRange(stack));

        // Server Side
        if (!world.isClientSide) {
            // As all upgrade types with tiers contain the same name, we can check for a single
            // type in the enum and produce a result that we can then pull the tier from
            int efficiency = 0;
            if (UpgradeTools.containsActiveUpgrade((stack), Upgrade.EFFICIENCY_1))
                efficiency = UpgradeTools.getUpgradeFromTool((stack), Upgrade.EFFICIENCY_1).get().getTier();

            float hardness = getHardness(coords, (Player) player, efficiency);
            hardness = hardness * ToolMiningProperties.getRange(stack) * 1;
            hardness = (float) Math.floor(hardness);
            if (hardness == 0) hardness = 1;

                List<Upgrade> toolUpgrades = UpgradeTools.getUpgrades(stack);

                setToolUpgrades(toolUpgrades);
        }
    }*/

    public void setToolUpgrades(List<Upgrade> toolUpgrades) {
        this.toolUpgrades = toolUpgrades;
    }

/*    private static float getHardness(List<BlockPos> coords, Player player, int efficiency) {
        float hardness = 0;
        float toolSpeed = 8;
        if (efficiency > 0) {
            toolSpeed = toolSpeed + ((efficiency * efficiency + 1));
        }

        MobEffectInstance hasteEffect = player.getEffect(MobEffects.DIG_SPEED);
        if (hasteEffect != null) {
            int hasteLevel = hasteEffect.getAmplifier() + 1;
            toolSpeed = toolSpeed + (toolSpeed * ((hasteLevel * 20f) / 100));
        }

        MobEffectInstance miningFatigue = player.getEffect(MobEffects.DIG_SLOWDOWN);
        if (miningFatigue != null) {
            toolSpeed = toolSpeed / 3f;
        }

        Level world = player.level;
        for (BlockPos coord : coords) {
            BlockState state = world.getBlockState(coord);
            float temphardness = state.getDestroySpeed(world, coord);

*//*            if (state.getBlock() instanceof RenderBlock) {
                RenderBlockEntity blockEntity = (RenderBlockEntity) world.getBlockEntity(coord);
                if (blockEntity != null) {
                    temphardness = blockEntity.getRenderBlock().getDestroySpeed(world, coord);
                }
            }*//*


            //if (state.getMaterial() == Material.EARTH) temphardness = temphardness * 4;
            hardness += (temphardness * 30) / toolSpeed;
        }

        return ((hardness / coords.size()));
    }*/

    public static ItemStack getPickaxe(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof CustomPickaxeItem)) {
            heldItem = player.getOffhandItem();
            if (!(heldItem.getItem() instanceof CustomPickaxeItem)) {
                return ItemStack.EMPTY;
            }
        }

        return heldItem;
    }

    public static void applyUpgrade(ItemStack tool, UpgradeCardItem upgradeCardItem) {
        if (UpgradeTools.containsUpgrade(tool, upgradeCardItem.getCard()))
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

    public static void changeRange(ItemStack tool) {
        if (ToolMiningProperties.getRange(tool) == 1)
            ToolMiningProperties.setRange(tool, 3);
        else
            ToolMiningProperties.setRange(tool, 1);
    }


}
