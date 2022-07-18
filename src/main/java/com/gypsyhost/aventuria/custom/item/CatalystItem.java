package com.gypsyhost.aventuria.custom.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class CatalystItem extends Item {
    public CatalystItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if(container.hurt(1, new Random(), null)){
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }


}
