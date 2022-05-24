package com.gypsyhost.socketcraft.custom.item.socketcards;

import com.gypsyhost.socketcraft.custom.item.toolhandle.HandleItem;
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

public class SocketCard extends Item {

    private Sockets socketCard;

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pStack.getItem() instanceof HandleItem) {
            if (socketCard == Sockets.SILK)
                pTooltipComponents.add(new TranslatableComponent("socketcraft.tooltip.item.wood_handle_type", Sockets.SILK.getBaseName()).withStyle(ChatFormatting.GREEN));
        }
    }

    SocketCard(Sockets sockets){

        super(new Item.Properties().tab(ModCreativeModeTab.MOD_TAB));
        this.socketCard = sockets;
    }

}
