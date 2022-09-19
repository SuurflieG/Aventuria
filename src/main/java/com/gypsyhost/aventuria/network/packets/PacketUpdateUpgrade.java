package com.gypsyhost.aventuria.network.packets;


import com.gypsyhost.aventuria.custom.gui.screen.CustomArmorScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomToolScreen;
import com.gypsyhost.aventuria.custom.gui.screen.CustomWeaponScreen;
import com.gypsyhost.aventuria.custom.gui.widgets.ToggleButton;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateUpgrade {
    private final String upgrade;

    public PacketUpdateUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public static void encode(PacketUpdateUpgrade msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.upgrade);
    }

    public static PacketUpdateUpgrade decode(FriendlyByteBuf buffer) {
        return new PacketUpdateUpgrade(buffer.readUtf(100));
    }

    public static class Handler {
        public static void handle(PacketUpdateUpgrade msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null)
                    return;

                Upgrade upgrade = UpgradeTools.getUpgradesByName(msg.upgrade);
                if( upgrade == null )
                    return;

                ItemStack stack = CustomPickaxeItem.getPickaxe(player);
                UpgradeTools.updateUpgrade(stack, upgrade);
                CustomToolScreen.updateButtons(upgrade, stack);

                ItemStack stack1 = CustomShovelItem.getShovel(player);
                UpgradeTools.updateUpgrade(stack1, upgrade);
                CustomToolScreen.updateButtons(upgrade, stack1);

                ItemStack stack2 = CustomAxeItem.getAxe(player);
                UpgradeTools.updateUpgrade(stack2, upgrade);
                CustomToolScreen.updateButtons(upgrade, stack2);

                ItemStack stack3 = CustomHoeItem.getHoe(player);
                UpgradeTools.updateUpgrade(stack3, upgrade);
                CustomToolScreen.updateButtons(upgrade, stack3);

                ItemStack stack4 = CustomSwordItem.getSword(player);
                UpgradeTools.updateUpgrade(stack4, upgrade);
                CustomWeaponScreen.updateButtons(upgrade, stack4);

                ItemStack stack5 = CustomArmorItem.getArmor(player);
                UpgradeTools.updateUpgrade(stack5, upgrade);
                CustomArmorScreen.updateButtons(upgrade, stack5);
            });

            ctx.get().setPacketHandled(true);
        }
    }
}
