package com.gypsyhost.aventuria.custom.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class OurKeys {
    public static final KeyMapping shiftClickGuiBinding = new KeyMapping("aventuria.text.open_gui", InputConstants.UNKNOWN.getValue(), "itemGroup.aventuriatab");

    public static void register() {
        ClientRegistry.registerKeyBinding(shiftClickGuiBinding);
    }
}
