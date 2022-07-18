package com.gypsyhost.aventuria;

import com.gypsyhost.aventuria.config.ClientConfigs;
import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.custom.energy.CapabilityPhotonEnergy;
import com.gypsyhost.aventuria.custom.gui.catalyzer.CatalyzerScreen;
import com.gypsyhost.aventuria.custom.gui.common.FilterScreen;
import com.gypsyhost.aventuria.custom.gui.press.PressScreen;
import com.gypsyhost.aventuria.custom.gui.upgradestation.UpgradeStationScreen;
import com.gypsyhost.aventuria.custom.renderer.CatalyzerBER;
import com.gypsyhost.aventuria.custom.renderer.UpgradeStationBER;
import com.gypsyhost.aventuria.network.PacketHandler;
import com.gypsyhost.aventuria.registry.*;
import com.gypsyhost.aventuria.world.feature.ModConfiguredFeature;
import com.gypsyhost.aventuria.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Aventuria.MOD_ID)
public class Aventuria
{
    public static final String MOD_ID = "aventuria";
    //private static final Logger LOGGER = LogUtils.getLogger();
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    public Aventuria()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModUpgradeCards.register(eventBus);
        ModBlocks.register(eventBus);
        ModArmorItems.register(eventBus);
        ModToolItems.register(eventBus);
        ModWeaponItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModRecipes.register(eventBus);
        ModMenuTypes.register(eventBus);



        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigs.SPEC, "aventuria-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC, "aventuria-common.toml");

        ModConfiguredFeature.register(eventBus);
        ModPlacedFeatures.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::registerCapabilities);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenuTypes.PRESS_MENU.get(), PressScreen::new);
        MenuScreens.register(ModMenuTypes.CATALYZER_MENU.get(), CatalyzerScreen::new);
        MenuScreens.register(ModMenuTypes.FILTER_MENU.get(), FilterScreen::new);
        MenuScreens.register(ModMenuTypes.UPGRADE_STATION_MENU.get(), UpgradeStationScreen::new);

        BlockEntityRenderers.register(ModBlockEntities.UPGRADE_STATION.get(), UpgradeStationBER::new);
        BlockEntityRenderers.register(ModBlockEntities.CATALYZER.get(), CatalyzerBER::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UPGRADE_STATION.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CATALYZER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ELECTRIC_RAIL.get(), RenderType.translucent());


    }

    public void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        CapabilityPhotonEnergy.register(event);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }


}
