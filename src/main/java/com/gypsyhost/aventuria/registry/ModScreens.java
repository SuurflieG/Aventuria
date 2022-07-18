package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.custom.gui.tools.CustomToolScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class ModScreens {
    public static void openToolSettingsScreen(ItemStack itemstack) {
        Minecraft.getInstance().setScreen(new CustomToolScreen(itemstack));
    }
}
