package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.gui.catalyzer.CatalyzerMenu;
import com.gypsyhost.socketcraft.custom.gui.press.PressMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SocketCraft.MOD_ID);

    public static final RegistryObject<MenuType<PressMenu>> PRESS_MENU = registerMenuType(PressMenu::new, "press_menu");
    public static final RegistryObject<MenuType<CatalyzerMenu>> CATALYZER_MENU = registerMenuType(CatalyzerMenu::new, "catalyzer_menu");



    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}