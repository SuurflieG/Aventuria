package com.gypsyhost.socketcraft.custom.item.toolhandle;

import com.gypsyhost.socketcraft.registry.ModCreativeModeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HandleItem extends Item{

    private final Handles handleItem;


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if(pStack.getItem() instanceof HandleItem){
            //String name = ((Handles.WOOD.getDisplayName()));
            //int durability = (((Handles.WOOD.getDurability())));
            if(handleItem == Handles.WOOD)
            pTooltipComponents.add(new TranslatableComponent("socketcraft.tooltip.item.wood_handle_type", Handles.WOOD.getDisplayName()).withStyle(ChatFormatting.GREEN));
            pTooltipComponents.add(new TranslatableComponent("socketcraft.tooltip.item.wood_handle_durability", Handles.WOOD.getDurability()).withStyle(ChatFormatting.GREEN));
        }
    }

    HandleItem(Handles handles){

        super(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB));
        this.handleItem = handles;
    }
}
