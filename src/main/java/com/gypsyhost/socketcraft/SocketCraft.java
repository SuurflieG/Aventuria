package com.gypsyhost.socketcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;


@Mod(SocketCraft.MOD_ID)
public class SocketCraft
{
    public static final String MOD_ID = "socketcraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SocketCraft()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

//        ModItems.register(eventBus);
//        ModBlocks.register(eventBus);
//        ModEnchantments.register(eventBus);
//        ModTags.register();
//        ModSounds.register(eventBus);
//        ModPaintings.register(eventBus);
//        ModFluids.register(eventBus);
//        ModBlockEntities.register(eventBus);
//        ModMenuTypes.register(eventBus);
//        ModRecipes.register(eventBus);
//        ModEffects.register(eventBus);
//        ModPotions.register(eventBus);
//        ModEntityTypes.register(eventBus);

//        GeckoLib.initialize();

//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MCCourseClientConfigs.SPEC, "mccourse-client.toml");
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MCCourseCommonConfigs.SPEC, "mccourse-common.toml");

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

//        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TURNIP_CROP.get(), RenderType.cutout());
//
//        ModItemProperties.addCustomItemProperties();
//
//        MenuScreens.register(ModMenuTypes.COBALT_BLASTER_MENU.get(), CobaltBlasterScreen::new);
//
//        WoodType.register(ModWoodTypes.CHERRY_BLOSSOM);


    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
