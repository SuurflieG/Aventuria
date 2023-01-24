package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.block.block.*;
import com.gypsyhost.aventuria.custom.block.block.geodes.*;
import com.gypsyhost.aventuria.custom.block.block.geodes.budding.*;
import com.gypsyhost.aventuria.custom.block.block.geodes.cluster.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Aventuria.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> CATALYZER = registerBlock("catalyzer", () -> new CatalyzerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> UPGRADE_STATION = registerBlock("upgrade_station", () -> new UpgradeStationBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> AVENTURINE_BLOCK = registerBlock("aventurine_block", () -> new AventurineBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_AVENTURINE = registerBlock("budding_aventurine", () -> new BuddingAventurineBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> AVENTURINE_CLUSTER = registerBlock("aventurine_cluster", () -> new AventurineClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_AVENTURINE_BUD = registerBlock("large_aventurine_bud", () -> new AventurineClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_AVENTURINE_BUD = registerBlock("medium_aventurine_bud", () -> new AventurineClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_AVENTURINE_BUD = registerBlock("small_aventurine_bud", () -> new AventurineClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> PYRITE_BLOCK = registerBlock("pyrite_block", () -> new PyriteBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_PYRITE = registerBlock("budding_pyrite", () -> new BuddingPyriteBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PYRITE_CLUSTER = registerBlock("pyrite_cluster", () -> new PyriteClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_PYRITE_BUD = registerBlock("large_pyrite_bud", () -> new PyriteClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_PYRITE_BUD = registerBlock("medium_pyrite_bud", () -> new PyriteClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_PYRITE_BUD = registerBlock("small_pyrite_bud", () -> new PyriteClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new RubyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_RUBY = registerBlock("budding_ruby", () -> new BuddingRubyBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> RUBY_CLUSTER = registerBlock("ruby_cluster", () -> new RubyClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_RUBY_BUD = registerBlock("large_ruby_bud", () -> new RubyClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_RUBY_BUD = registerBlock("medium_ruby_bud", () -> new RubyClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_RUBY_BUD = registerBlock("small_ruby_bud", () -> new RubyClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block", () -> new TopazBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_TOPAZ = registerBlock("budding_topaz", () -> new BuddingTopazBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> TOPAZ_CLUSTER = registerBlock("topaz_cluster", () -> new TopazClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_TOPAZ_BUD = registerBlock("large_topaz_bud", () -> new TopazClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_TOPAZ_BUD = registerBlock("medium_topaz_bud", () -> new TopazClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_TOPAZ_BUD = registerBlock("small_topaz_bud", () -> new TopazClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> SPECTROLITE_BLOCK = registerBlock("spectrolite_block", () -> new SpectroliteBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_SPECTROLITE = registerBlock("budding_spectrolite", () -> new BuddingSpectroliteBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SPECTROLITE_CLUSTER = registerBlock("spectrolite_cluster", () -> new SpectroliteClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_SPECTROLITE_BUD = registerBlock("large_spectrolite_bud", () -> new SpectroliteClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_SPECTROLITE_BUD = registerBlock("medium_spectrolite_bud", () -> new SpectroliteClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_SPECTROLITE_BUD = registerBlock("small_spectrolite_bud", () -> new SpectroliteClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> UNAKITE_BLOCK = registerBlock("unakite_block", () -> new UnakiteBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5f).sound(SoundType.BASALT)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> BUDDING_UNAKITE = registerBlock("budding_unakite", () -> new BuddingUnakiteBlock(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> UNAKITE_CLUSTER = registerBlock("unakite_cluster", () -> new UnakiteClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel( (pLightEmission) -> 8)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> LARGE_UNAKITE_BUD = registerBlock("large_unakite_bud", () -> new UnakiteClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((pLightEmission) -> 6)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MEDIUM_UNAKITE_BUD = registerBlock("medium_unakite_bud", () -> new UnakiteClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((pLightEmission) -> 3)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> SMALL_UNAKITE_BUD = registerBlock("small_unakite_bud", () -> new UnakiteClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((pLightEmission) -> 2)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> AVENTURIA_PORTAL = registerBlockWithoutBlockItem("aventurine_portal", AventuriaPortalBlock::new);


    //Register Block with tooltip field
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    ////Register BlockItem with tooltip field
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent(tooltipKey));
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoTab(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, null);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItemNoTab(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
