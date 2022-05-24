package com.gypsyhost.socketcraft;

import com.gypsyhost.socketcraft.custom.gui.catalyzer.CatalyzerScreen;
import com.gypsyhost.socketcraft.custom.gui.press.PressScreen;
import com.gypsyhost.socketcraft.registry.*;
import com.gypsyhost.socketcraft.config.SocketCraftCommonConfigs;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(SocketCraft.MOD_ID)
public class SocketCraft
{
    public static final String MOD_ID = "socketcraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SocketCraft()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModSocketCards.register(eventBus);
        ModHandleItems.register(eventBus);
        ModPickaxeHeadItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModArmorItems.register(eventBus);
        ModToolItems.register(eventBus);
        ModWeaponItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModTags.register();
        ModRecipes.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SocketCraftCommonConfigs.SPEC, "socketcraft-common.toml");

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenuTypes.PRESS_MENU.get(), PressScreen::new);
        MenuScreens.register(ModMenuTypes.CATALYZER_MENU.get(), CatalyzerScreen::new);

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
