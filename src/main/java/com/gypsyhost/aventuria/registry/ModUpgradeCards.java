package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModUpgradeCards {
    public static final DeferredRegister<Item> UPGRADECARD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    public static final RegistryObject<Item> BLANK = UPGRADECARD_ITEMS.register("upgrade_blank", () -> Upgrade.BLANK.getCard());
    public static final RegistryObject<Item> SILK = UPGRADECARD_ITEMS.register("upgrade_silk", () -> Upgrade.SILK.getCard());
    //public static final RegistryObject<Item> VOID_JUNK = UPGRADECARD_ITEMS.register("upgrade__void_junk", () -> Upgrades.VOID_JUNK.getCard());
    public static final RegistryObject<Item> MAGNET = UPGRADECARD_ITEMS.register("upgrade__magnet", () -> Upgrade.MAGNET.getCard());
    public static final RegistryObject<Item> THREE_BY_THREE = UPGRADECARD_ITEMS.register("upgrade_three_by_three", () -> Upgrade.THREE_BY_THREE.getCard());
    public static final RegistryObject<Item> FORTUNE_1 = UPGRADECARD_ITEMS.register("upgrade_fortune_1", () -> Upgrade.FORTUNE_1.getCard());
    public static final RegistryObject<Item> FORTUNE_2 = UPGRADECARD_ITEMS.register("upgrade_fortune_2", () -> Upgrade.FORTUNE_2.getCard());
    public static final RegistryObject<Item> FORTUNE_3 = UPGRADECARD_ITEMS.register("upgrade_fortune_3", () -> Upgrade.FORTUNE_3.getCard());
    public static final RegistryObject<Item> EFFICIENCY_1 = UPGRADECARD_ITEMS.register("upgrade_efficiency_1", () -> Upgrade.EFFICIENCY_1.getCard());
    public static final RegistryObject<Item> EFFICIENCY_2 = UPGRADECARD_ITEMS.register("upgrade_efficiency_2", () -> Upgrade.EFFICIENCY_2.getCard());
    public static final RegistryObject<Item> EFFICIENCY_3 = UPGRADECARD_ITEMS.register("upgrade_efficiency_3", () -> Upgrade.EFFICIENCY_3.getCard());
    public static final RegistryObject<Item> EFFICIENCY_4 = UPGRADECARD_ITEMS.register("upgrade_efficiency_4", () -> Upgrade.EFFICIENCY_4.getCard());
    public static final RegistryObject<Item> EFFICIENCY_5 = UPGRADECARD_ITEMS.register("upgrade_efficiency_5", () -> Upgrade.EFFICIENCY_5.getCard());

    public static void register(IEventBus eventBus) {
        UPGRADECARD_ITEMS.register(eventBus);
    }
}
