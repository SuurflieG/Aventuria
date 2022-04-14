package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.block.block.MachineCasingBlock;
import com.gypsyhost.socketcraft.custom.block.block.MetalFormerBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
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
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SocketCraft.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> TUNGSTEN_ORE = registerBlock("tungsten_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> AVENTURINE_ORE = registerBlock("aventurine_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> CALCITE_ORE = registerBlock("calcite_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> PYRITE_ORE = registerBlock("pyrite_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> UNAKITE_ORE = registerBlock("unakite_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(2,4)), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> TUNGSTEN_BLOCK = registerBlock("tungsten_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Block> METAL_FORMER = registerBlock("metal_former", () -> new MetalFormerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.MOD_TAB);
    public static final RegistryObject<Block> MACHINE_CASING = registerBlock("machine_casing", () -> new MachineCasingBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.MOD_TAB);


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

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
