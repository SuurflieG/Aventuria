package com.gypsyhost.aventuria.custom.item.tool;

import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.util.AventuriaKeyBinding;
import com.gypsyhost.aventuria.registry.ModScreens;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.FakePlayer;

public class CustomShovelItem extends ShovelItem {

    public CustomShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public static ItemStack getShovel(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof CustomShovelItem)) {
            heldItem = player.getOffhandItem();
            if (!(heldItem.getItem() instanceof CustomShovelItem)) {
                return ItemStack.EMPTY;
            }
        }

        return heldItem;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        miningSize(pStack, pLevel, pPos, pEntityLiving);
        return true;
    }

    private void miningSize(ItemStack pStack, Level pLevel, BlockPos pPos, LivingEntity pEntityLiving) {
        Player pPlayer = (Player) pEntityLiving;

        int miningSize = ToolProperties.getMiningSize(pStack);
        int miningDepth = ToolProperties.getMiningDepth(pStack);
        int blockX = pPos.getX();
        int blockZ = pPos.getZ();
        int blockY = pPos.getY();

        int start = -1;
        if(miningSize == 5){
            start = -2;
        }else if (miningSize == 7){
            start = -3;
        }else if(miningSize == 1){
            start = 0;
        }

        int startD = 0;

        int startX = start;
        int startY = start;
        int startZ = start;

        int startxD = startD;
        int startyD = startD;
        int startzD = startD;

        if (!pLevel.isClientSide && !(pEntityLiving instanceof FakePlayer) && ToolProperties.getMiningSize(pStack) > 2 || ToolProperties.getMiningDepth(pStack) > 2) {

            if (pPlayer.getXRot() > 40) {
                for (int x = 0; x < miningSize; x++) {
                    startZ = start;
                    for (int z = 0; z < miningSize; z++) {
                        startyD = startD;
                        for (int y = 0; y < miningDepth; y++) {
                            if (isCorrectToolForDrops(pStack ,pLevel.getBlockState(new BlockPos(blockX + startX, blockY - startyD, blockZ + startZ)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX + startX, blockY - startyD, blockZ + startZ)), pLevel, new BlockPos(blockX + startX, blockY - startyD, blockZ + startZ));
                                pLevel.destroyBlock(new BlockPos(blockX + startX, blockY - startyD, blockZ + startZ), false);
                            }
                            startyD++;
                        }
                        startZ++;
                    }
                    startX++;
                }
            }

            else if (pPlayer.getXRot() < -40) {
                for (int x = 0; x < miningSize; x++) {
                    startZ = start;
                    for (int z = 0; z < miningSize; z++) {
                        startyD = startD;
                        for (int y = 0; y < miningDepth; y++) {
                            if (isCorrectToolForDrops(pStack ,pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startyD, blockZ + startZ)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startyD, blockZ + startZ)), pLevel, new BlockPos(blockX + startX, blockY + startyD, blockZ + startZ));
                                pLevel.destroyBlock(new BlockPos(blockX + startX, blockY + startyD, blockZ + startZ), false);
                            }
                            startyD++;
                        }
                        startZ++;
                    }
                    startX++;
                }
            }

            else if (pPlayer.getDirection() == Direction.NORTH) {
                for (int x = 0; x < miningSize; x++) {
                    startY = start;
                    for (int y = 0; y < miningSize; y++){
                        startzD = startD;
                        for(int z = 0; z < miningDepth; z++) {
                            if (isCorrectToolForDrops(pStack, pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startY, blockZ - startzD)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startY, blockZ - startzD)), pLevel, new BlockPos(blockX + startX, blockY + startY, blockZ - startzD));
                                pLevel.destroyBlock(new BlockPos(blockX + startX, blockY + startY, blockZ - startzD), false);
                            }
                            startzD++;
                        }
                        startY++;
                    }
                    startX++;
                }
            }

            else if (pPlayer.getDirection() == Direction.SOUTH) {
                for (int x = 0; x < miningSize; x++) {
                    startY = start;
                    for (int y = 0; y < miningSize; y++){
                        startzD = startD;
                        for(int z = 0; z < miningDepth; z++) {
                            if (isCorrectToolForDrops(pStack, pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startY, blockZ + startzD)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX + startX, blockY + startY, blockZ + startzD)), pLevel, new BlockPos(blockX + startX, blockY + startY, blockZ + startzD));
                                pLevel.destroyBlock(new BlockPos(blockX + startX, blockY + startY, blockZ + startzD), false);
                            }
                            startzD++;
                        }
                        startY++;
                    }
                    startX++;
                }
            }

            else if (pPlayer.getDirection() == Direction.WEST) {
                for (int z = 0; z < miningSize; z++) {
                    startY = start;
                    for (int y = 0; y < miningSize; y++) {
                        startxD = startD;
                        for (int x = 0;x < miningDepth; x++) {
                            if (isCorrectToolForDrops(pStack, pLevel.getBlockState(new BlockPos(blockX - startxD, blockY + startY, blockZ + startZ)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX - startxD, blockY + startY, blockZ + startZ)), pLevel, new BlockPos(blockX - startxD, blockY + startY, blockZ + startZ));
                                pLevel.destroyBlock(new BlockPos(blockX - startxD, blockY + startY, blockZ + startZ), false);
                            }
                            startxD++;
                        }
                        startY++;
                    }
                    startZ++;
                }
            }

            else if (pPlayer.getDirection() == Direction.EAST) {
                for (int z = 0; z < miningSize; z++) {
                    startY = start;
                    for (int y = 0; y < miningSize; y++) {
                        startxD = startD;
                        for (int x = 0;x < miningDepth; x++) {
                            if (isCorrectToolForDrops(pStack, pLevel.getBlockState(new BlockPos(blockX + startxD, blockY + startY, blockZ + startZ)))) {
                                Block.dropResources(pLevel.getBlockState(new BlockPos(blockX + startxD, blockY + startY, blockZ + startZ)), pLevel, new BlockPos(blockX + startxD, blockY + startY, blockZ + startZ));
                                pLevel.destroyBlock(new BlockPos(blockX + startxD, blockY + startY, blockZ + startZ), false);
                            }
                            startxD++;
                        }
                        startY++;
                    }
                    startZ++;
                }
            }

        }
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
        int currentMiningSize = ToolProperties.getMiningSize(tool);
        if (currentMiningSize <= 5) {
            ToolProperties.setMiningSize(tool, currentMiningSize + 2);
        }
        else {
            ToolProperties.setMiningSize(tool, 1);
        }
    }

    public static void changeDepth(ItemStack tool) {
        int currentMiningDepth = ToolProperties.getMiningDepth(tool);
        if (currentMiningDepth <= 5) {
            ToolProperties.setMiningDepth(tool, currentMiningDepth + 2);
        }
        else {
            ToolProperties.setMiningDepth(tool, 1);
        }
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
                if (AventuriaKeyBinding.GUI_KEY_SHIFT_RIGHT_CLICK.getKey() == InputConstants.UNKNOWN) {
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
