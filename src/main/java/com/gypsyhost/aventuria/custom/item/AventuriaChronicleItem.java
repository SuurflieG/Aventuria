package com.gypsyhost.aventuria.custom.item;

import com.gypsyhost.aventuria.custom.util.AventuriaKeyBinding;
import com.gypsyhost.aventuria.registry.ModScreens;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AventuriaChronicleItem extends Item {
    public AventuriaChronicleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if(!pLevel.isClientSide){
            return InteractionResultHolder.fail(itemstack);
        }
        else {
            ModScreens.openToolSettingsScreen(itemstack);
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        }
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack pStack) {
        return false;
    }
}
