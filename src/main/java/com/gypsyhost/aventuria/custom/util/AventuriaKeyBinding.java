package com.gypsyhost.aventuria.custom.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class AventuriaKeyBinding {

    public static final String KEY_CATEGORY_AVENTURIA = "key.category.aventuria.aventuria";
    public static final String KEY_OPEN_GUI = "key.aventuria.open_gui";

    public static final KeyMapping GUI_KEY_G = new KeyMapping(KEY_OPEN_GUI, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_AVENTURIA);

    public static final KeyMapping GUI_KEY_SHIFT_RIGHT_CLICK = new KeyMapping("aventuria.text.open_gui", InputConstants.UNKNOWN.getValue(), KEY_CATEGORY_AVENTURIA);

    public static void register() {
        ClientRegistry.registerKeyBinding(GUI_KEY_G);
        //ClientRegistry.registerKeyBinding(GUI_KEY_SHIFT_RIGHT_CLICK);
    }
}
