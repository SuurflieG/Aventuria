package com.gypsyhost.socketcraft;

import com.gypsyhost.socketcraft.custom.gui.metalformer.PressScreen;
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
        ModBlocks.register(eventBus);
        ModArmorItems.register(eventBus);
        ModToolItems.register(eventBus);
        ModWeaponItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModTags.register();
        ModRecipes.register(eventBus);
        ModMenuTypes.register(eventBus);



//        ModEnchantments.register(eventBus);
//        ModSounds.register(eventBus);
//        ModPaintings.register(eventBus);
//        ModFluids.register(eventBus);
//        ModEffects.register(eventBus);
//        ModPotions.register(eventBus);
//        ModEntityTypes.register(eventBus);

//        GeckoLib.initialize();

//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SocketCraftClientConfigs.SPEC, "socketcraft-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SocketCraftCommonConfigs.SPEC, "socketcraft-common.toml");

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenuTypes.PRESS_MENU.get(), PressScreen::new);

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
