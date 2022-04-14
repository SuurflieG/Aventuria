package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SocketCraft.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new SCRecipes(generator));
            generator.addProvider(new SCLootTables(generator));
            SCBlockTags blockTags = new SCBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new SCItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new SCBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new SCItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new SCLanguageProvider(generator, "en_us"));
        }
    }
}
