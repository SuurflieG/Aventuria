package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.gui.catalyzer.CatalyzerMenu;
import com.gypsyhost.aventuria.custom.gui.common.FilterMenu;
import com.gypsyhost.aventuria.custom.gui.press.PressMenu;
import com.gypsyhost.aventuria.custom.gui.tools.CustomToolScreen;
import com.gypsyhost.aventuria.custom.gui.upgradestation.UpgradeStationMenu;
import com.gypsyhost.aventuria.custom.gui.tools.CustomToolMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Aventuria.MOD_ID);

    public static final RegistryObject<MenuType<PressMenu>> PRESS_MENU = registerMenuType(PressMenu::new, "press_menu");
    public static final RegistryObject<MenuType<CatalyzerMenu>> CATALYZER_MENU = registerMenuType(CatalyzerMenu::new, "catalyzer_menu");
    public static final RegistryObject<MenuType<CustomToolMenu>> CUSTOM_TOOL_MENU = registerMenuType(CustomToolMenu::new, "custom_tool_menu");
    public static final RegistryObject<MenuType<FilterMenu>> FILTER_MENU = registerMenuType(FilterMenu::new, "filter_menu");
    public static final RegistryObject<MenuType<UpgradeStationMenu>> UPGRADE_STATION_MENU = registerMenuType(UpgradeStationMenu::new, "upgrade_station_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}