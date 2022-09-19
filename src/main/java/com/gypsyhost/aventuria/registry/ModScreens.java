package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.custom.gui.screen.CustomArmorScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomToolScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomWeaponScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class ModScreens {
    public static void openToolSettingsScreen(ItemStack itemstack) {
        Minecraft.getInstance().setScreen(new CustomToolScreen(itemstack));
    }

    public static void openArmorSettingsScreen(ItemStack itemstack) {
        Minecraft.getInstance().setScreen(new CustomArmorScreen(itemstack));
    }

    public static void openWeaponSettingsScreen(ItemStack itemstack) {
        Minecraft.getInstance().setScreen(new CustomWeaponScreen(itemstack));
    }
}
