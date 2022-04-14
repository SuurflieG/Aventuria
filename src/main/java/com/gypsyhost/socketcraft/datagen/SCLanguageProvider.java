package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.gypsyhost.socketcraft.registry.ModCreativeModeTab.MOD_TAB;


public class SCLanguageProvider extends LanguageProvider {


    public SCLanguageProvider(DataGenerator gen, String locale) {
        super(gen, SocketCraft.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + MOD_TAB, "socketcrafttab");
    }
}
