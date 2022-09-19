package com.gypsyhost.aventuria.custom.item.tool;

import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class UpgradeHelper {

    public UpgradeHelper() {

    }

    // Single Level Enchants Begin
    public static void applySilkTouch(ItemStack pStack){
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.SILK)){
            addSilkTouch(pStack, 1);
        }
    }

    public static ItemStack addSilkTouch(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.put(Enchantments.SILK_TOUCH, level);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeSilkTouch(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.SILK_TOUCH);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyMending(ItemStack pStack){
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.MENDING)){
            addMending(pStack, 1);
        }
    }

    public static ItemStack addMending(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.put(Enchantments.MENDING, level);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeMending(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.MENDING);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyAquaAffinity(ItemStack pStack){
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.AQUA_AFFINITY)){
            addAquaAffinity(pStack, 1);
        }
    }

    public static ItemStack addAquaAffinity(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.put(Enchantments.AQUA_AFFINITY, level);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeAquaAffinity(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.AQUA_AFFINITY);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    // Single Level Enchants End

    // Multi Level Enchants Begin

    public static void applyBaneOfArthropods(ItemStack pStack){
        int baneOfArthropodsLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.BANE_OF_ARTHROPODS_1)){
            baneOfArthropodsLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.BANE_OF_ARTHROPODS_1).get().getTier();
            addBaneOfArthropods(pStack, baneOfArthropodsLevel);
        }
    }

    public static ItemStack addBaneOfArthropods(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.BANE_OF_ARTHROPODS, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeBaneOfArthropods(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.BANE_OF_ARTHROPODS);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyBlastProtection(ItemStack pStack){
        int blastProtectionLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.BLAST_PROTECTION_1)){
            blastProtectionLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.BLAST_PROTECTION_1).get().getTier();
            addBlastProtection(pStack, blastProtectionLevel);
        }
    }

    public static ItemStack addBlastProtection(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.BLAST_PROTECTION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeBlastProtection(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.BLAST_PROTECTION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyDepthStrider(ItemStack pStack){
        int depthStriderLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.DEPTH_STRIDER_1)){
            depthStriderLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.DEPTH_STRIDER_1).get().getTier();
            addDepthStrider(pStack, depthStriderLevel);
        }
    }

    public static ItemStack addDepthStrider(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.DEPTH_STRIDER, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeDepthStrider(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.DEPTH_STRIDER);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyEfficiency(ItemStack pStack){
        int efficiencyLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.EFFICIENCY_1)){
            efficiencyLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.EFFICIENCY_1).get().getTier();
            addEfficiency(pStack, efficiencyLevel);
        }
    }

    public static ItemStack addEfficiency(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.BLOCK_EFFICIENCY, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeEfficiency(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.BLOCK_EFFICIENCY);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyFeatherFalling(ItemStack pStack){
        int featherFallingLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.FEATHER_FALLING_1)){
            featherFallingLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FEATHER_FALLING_1).get().getTier();
            addFeatherFalling(pStack, featherFallingLevel);
        }
    }

    public static ItemStack addFeatherFalling(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.FALL_PROTECTION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeFeatherFalling(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.FALL_PROTECTION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyFireAspect(ItemStack pStack){
        int fireAspectLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.FIRE_ASPECT_1)){
            fireAspectLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FIRE_ASPECT_1).get().getTier();
            addFireAspect(pStack, fireAspectLevel);
        }
    }

    public static ItemStack addFireAspect(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.FIRE_ASPECT, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeFireAspect(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.FIRE_ASPECT);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyFireProtection(ItemStack pStack){
        int fireProtectionLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.FIRE_PROTECTION_1)){
            fireProtectionLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FIRE_PROTECTION_1).get().getTier();
            addFireProtection(pStack, fireProtectionLevel);
        }
    }

    public static ItemStack addFireProtection(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.FIRE_PROTECTION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeFireProtection(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.FIRE_PROTECTION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyFortune(ItemStack pStack){
        int fortuneLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.FORTUNE_1)){
            fortuneLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FORTUNE_1).get().getTier();
            addFortune(pStack, fortuneLevel);
        }
    }

    public static ItemStack addFortune(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.BLOCK_FORTUNE, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeFortune(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.BLOCK_FORTUNE);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyFrostWalker(ItemStack pStack){
        int frostWalkerLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.FROST_WALKER_1)){
            frostWalkerLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.FROST_WALKER_1).get().getTier();
            addFrostWalker(pStack, frostWalkerLevel);
        }
    }

    public static ItemStack addFrostWalker(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.FROST_WALKER, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeFrostWalker(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.FROST_WALKER);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyKnockback(ItemStack pStack){
        int knockbackLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.KNOCKBACK_1)){
            knockbackLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.KNOCKBACK_1).get().getTier();
            addKnockback(pStack, knockbackLevel);
        }
    }

    public static ItemStack addKnockback(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.KNOCKBACK, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeKnockback(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.KNOCKBACK);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyMobLooting(ItemStack pStack){
        int mobLootingLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.MOB_LOOTING_1)){
            mobLootingLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.MOB_LOOTING_1).get().getTier();
            addMobLooting(pStack, mobLootingLevel);
        }
    }

    public static ItemStack addMobLooting(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.MOB_LOOTING, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeMobLooting(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.MOB_LOOTING);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyProjectileProtection(ItemStack pStack){
        int projectileProtectionLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.PROJECTILE_PROTECTION_1)){
            projectileProtectionLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.PROJECTILE_PROTECTION_1).get().getTier();
            addProjectileProtection(pStack, projectileProtectionLevel);
        }
    }

    public static ItemStack addProjectileProtection(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.PROJECTILE_PROTECTION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeProjectileProtection(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.PROJECTILE_PROTECTION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyProtection(ItemStack pStack){
        int protectionLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.PROTECTION_1)){
            protectionLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.PROTECTION_1).get().getTier();
            addProtection(pStack, protectionLevel);
        }
    }

    public static ItemStack addProtection(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.ALL_DAMAGE_PROTECTION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeProtection(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.ALL_DAMAGE_PROTECTION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyRespiration(ItemStack pStack){
        int respirationLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.RESPIRATION_1)){
            respirationLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.RESPIRATION_1).get().getTier();
            addRespiration(pStack, respirationLevel);
        }
    }

    public static ItemStack addRespiration(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.RESPIRATION, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeRespiration(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.RESPIRATION);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applySharpness(ItemStack pStack){
        int sharpnessLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.SHARPNESS_1)){
            sharpnessLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.SHARPNESS_1).get().getTier();
            addSharpness(pStack, sharpnessLevel);
        }
    }

    public static ItemStack addSharpness(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.SHARPNESS, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeSharpness(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.SHARPNESS);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applySmite(ItemStack pStack){
        int smiteLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.SMITE_1)){
            smiteLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.SMITE_1).get().getTier();
            addSmite(pStack, smiteLevel);
        }
    }

    public static ItemStack addSmite(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.SMITE, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeSmite(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.SMITE);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applySoulSpeed(ItemStack pStack){
        int soulSpeedLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.SOUL_SPEED_1)){
            soulSpeedLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.SOUL_SPEED_1).get().getTier();
            addSoulSpeed(pStack, soulSpeedLevel);
        }
    }

    public static ItemStack addSoulSpeed(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.SOUL_SPEED, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeSoulSpeed(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.SOUL_SPEED);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applySweepingEdge(ItemStack pStack){
        int sweepingEdgeLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.SWEEPING_EDGE_1)){
            sweepingEdgeLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.SWEEPING_EDGE_1).get().getTier();
            addSweepingEdge(pStack, sweepingEdgeLevel);
        }
    }

    public static ItemStack addSweepingEdge(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.SWEEPING_EDGE, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeSweepingEdge(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.SWEEPING_EDGE);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyThorns(ItemStack pStack){
        int thornsLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.THORNS_1)){
            thornsLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.THORNS_1).get().getTier();
            addThorns(pStack, thornsLevel);
        }
    }

    public static ItemStack addThorns(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.THORNS, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeThorns(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.THORNS);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static void applyUnbreaking(ItemStack pStack){
        int unbreakingLevel = 0;
        if(UpgradeTools.containsUpgrade(pStack, Upgrade.UNBREAKING_1)){
            unbreakingLevel = UpgradeTools.getUpgradeFromTool((pStack), Upgrade.UNBREAKING_1).get().getTier();
            addUnbreaking(pStack, unbreakingLevel);
        }
    }

    public static ItemStack addUnbreaking(ItemStack pStack, int level){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        if (level > 0){
            map.put(Enchantments.UNBREAKING, level);
        }
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }

    public static ItemStack removeUnbreaking(ItemStack pStack){
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(pStack);
        map.remove(Enchantments.UNBREAKING);
        EnchantmentHelper.setEnchantments(map, pStack);
        return pStack;
    }
}

