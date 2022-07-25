package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.energy.IKilowattStorage;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aventuria.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IKilowattStorage.class);
    }
}
