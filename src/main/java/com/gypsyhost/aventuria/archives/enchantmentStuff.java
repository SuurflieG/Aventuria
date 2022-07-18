/*
package com.gypsyhost.aventuria.archives;

import com.gypsyhost.aventuria.custom.item.tool.ToolMiningProperties;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.util.MiningCollect;
import com.gypsyhost.aventuria.custom.util.VectorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class enchantmentStuff {

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        int range = ToolMiningProperties.getBeamRange(pStack);
        BlockHitResult lookingAt = VectorHelper.getLookingAt((Player) pEntityLiving, ClipContext.Fluid.NONE, range);
        List<BlockPos> coords = MiningCollect.collect((Player) pEntityLiving, lookingAt, pLevel, ToolMiningProperties.getMiningSize(pStack));

        if (!pLevel.isClientSide) {
            // As all upgrade types with tiers contain the same name, we can check for a single
            // type in the enum and produce a result that we can then pull the tier from
            int efficiency = 0;
            if (UpgradeTools.containsActiveUpgrade((pStack), Upgrade.EFFICIENCY_1))
                efficiency = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.EFFICIENCY_1).get().getTier();

            float hardness = getHardness(coords, (Player) pEntityLiving, efficiency);
            hardness = hardness * ToolMiningProperties.getMiningSize(pStack) * 1;
            hardness = (float) Math.floor(hardness);
            if (hardness == 0) hardness = 1;
            for (BlockPos coord : coords) {
                BlockState state = pLevel.getBlockState(coord);

                Direction side = lookingAt.getDirection();
                boolean vertical = side.getAxis().isVertical();
                Direction up = vertical ? pEntityLiving.getDirection() : Direction.UP;
                Direction right = vertical ? up.getClockWise() : side.getCounterClockWise();

                BlockPos pos;
                if (ToolMiningProperties.getMiningSize(pStack) == 1)
                    pos = lookingAt.getBlockPos().relative(side, 4);
                else
                    pos = lookingAt.getBlockPos().relative(side).relative(right);
                //}

                hasFortune(pStack);
                int fortune = 0;
                if(UpgradeTools.containsInactiveUpgrade(pStack, Upgrade.FORTUNE_1)){
                    CompoundTag nbt = pStack.getTag();
                    nbt.remove(TAG_ENCHANTMENTS);
                }
                else if(UpgradeTools.containsActiveUpgrade(pStack, Upgrade.FORTUNE_1)){
                    fortune = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FORTUNE_1).get().getTier();

                    pStack.enchant(Enchantments.BLOCK_FORTUNE, fortune);
                }
            }
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    @Override
    public void onUsingTick(ItemStack pStack, LivingEntity pEntityLiving, int count) {
        setToolUpgrades(toolUpgrades);
        hasFortune(pStack);
        int fortune = 0;
        if(UpgradeTools.containsInactiveUpgrade(pStack, Upgrade.FORTUNE_1)){
            CompoundTag nbt = pStack.getTag();
            nbt.remove(TAG_ENCHANTMENTS);
        }
        else if(UpgradeTools.containsActiveUpgrade(pStack, Upgrade.FORTUNE_1)){
            fortune = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FORTUNE_1).get().getTier();

            pStack.enchant(Enchantments.BLOCK_FORTUNE, fortune);
        }

        super.onUsingTick(pStack, pEntityLiving, count);
    }

    public static void hasFortune(ItemStack pStack){
        if (pLevel == null || pLevel.isClientSide || playerUUID == null) {
            return;
        }

        Player pPlayer = pLevel.getPlayerByUUID(playerUUID);
        if (pPlayer == null) {
            return;
        }

        int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pPlayer.getMainHandItem());
        if(fortuneLevel == 1){
            return;
        }
        if(fortuneLevel == 2){
            return;
        }
        if(fortuneLevel == 3){
            return;
        }
    }

    private static float getHardness(List<BlockPos> coords, Player player, int efficiency) {
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



            //if (state.getMaterial() == Material.EARTH) temphardness = temphardness * 4;
            hardness += (temphardness * 30) / toolSpeed;
        }

        return ((hardness / coords.size()));
    }
}
*/
