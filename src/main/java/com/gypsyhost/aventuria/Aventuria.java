package com.gypsyhost.aventuria;

import com.gypsyhost.aventuria.config.ClientConfigs;
import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.custom.gui.screen.CatalyzerScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomEquipmentScreen;
import com.gypsyhost.aventuria.custom.gui.screen.UpgradeStationScreen;
import com.gypsyhost.aventuria.client.render.CatalyzerBER;
import com.gypsyhost.aventuria.client.render.UpgradeStationBER;
import com.gypsyhost.aventuria.custom.gui.widgets.EquipmentSelectButton;
import com.gypsyhost.aventuria.custom.util.AventuriaKeyBinding;
import com.gypsyhost.aventuria.network.PacketHandler;
import com.gypsyhost.aventuria.registry.*;
import com.gypsyhost.aventuria.world.dimension.ModDimensions;
import com.gypsyhost.aventuria.world.feature.ModConfiguredFeature;
import com.gypsyhost.aventuria.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.common.MinecraftForge;
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
        ModTreeBlocks.register(eventBus);
        ModArmorItems.register(eventBus);
        ModToolItems.register(eventBus);
        ModWeaponItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModRecipes.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModDimensions.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigs.SPEC, "aventuria-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC, "aventuria-common.toml");

        ModPlacedFeatures.register(eventBus);
        ModConfiguredFeature.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        AventuriaKeyBinding.register();

        MenuScreens.register(ModMenuTypes.CATALYZER_MENU.get(), CatalyzerScreen::new);
        MenuScreens.register(ModMenuTypes.UPGRADE_STATION_MENU.get(), UpgradeStationScreen::new);

        BlockEntityRenderers.register(ModBlockEntities.UPGRADE_STATION.get(), UpgradeStationBER::new);
        BlockEntityRenderers.register(ModBlockEntities.CATALYZER.get(), CatalyzerBER::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UPGRADE_STATION.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CATALYZER.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AVENTURINE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_AVENTURINE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_AVENTURINE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_AVENTURINE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PYRITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_PYRITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_PYRITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_PYRITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RUBY_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_RUBY_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_RUBY_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_RUBY_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOPAZ_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_TOPAZ_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_TOPAZ_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_TOPAZ_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPECTROLITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_SPECTROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_SPECTROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_SPECTROLITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNAKITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_UNAKITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_UNAKITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_UNAKITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModTreeBlocks.LARCH_SAPLING.get(), RenderType.cutout());

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(PacketHandler::register);


    }


}
