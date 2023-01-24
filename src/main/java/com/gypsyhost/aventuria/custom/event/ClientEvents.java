package com.gypsyhost.aventuria.custom.event;


import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.screen.CustomEquipmentScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomToolScreen;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.util.AventuriaKeyBinding;
import com.gypsyhost.aventuria.registry.ModScreens;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aventuria.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void handleEventInput(TickEvent.ClientTickEvent event) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || event.phase == TickEvent.Phase.START)
            return;

                KeyMapping mode = AventuriaKeyBinding.GUI_KEY_G;
        if (!(mc.screen instanceof CustomEquipmentScreen) && mode.consumeClick() && ((mode.getKeyModifier() == KeyModifier.NONE
                && KeyModifier.getActiveModifier() == KeyModifier.NONE) || mode.getKeyModifier() != KeyModifier.NONE)) {
            mc.setScreen(new CustomEquipmentScreen(TextComponent.EMPTY, ItemStack.EMPTY));
        }
    }


}
