package com.gypsyhost.aventuria.network;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.network.packets.PacketUpdateUpgrade;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/*public class ModMessages {

    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Aventuria.MOD_ID, "messages"))
                .networkProtocolVersion(()-> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketUpdateUpgrade.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketUpdateUpgrade::new)
                .encoder(PacketUpdateUpgrade::toBytes)
                .consumerMainThread(PacketUpdateUpgrade::handle)
                .add();
    }
}*/
