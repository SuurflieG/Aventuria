package com.gypsyhost.socketcraft.custom.item.toolparts.pickaxe;

import com.gypsyhost.socketcraft.registry.ModCreativeModeTab;
import net.minecraft.world.item.*;

public class PickaxeHeadItem extends Item {

    private final PickaxeHead pickaxeHeadItem;


    PickaxeHeadItem(PickaxeHead pickaxeHeadItem){
        super(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB));
        this.pickaxeHeadItem = pickaxeHeadItem;
    }

}
