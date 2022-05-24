package com.gypsyhost.socketcraft.custom.item.toolhandle;

import net.minecraft.resources.ResourceLocation;

public enum Handles {

    WOOD("wood_handle", "Wood" , 32, true, new ResourceLocation("textures/item/wood_handle.png"));
//    STONE("stone_handle", 64, true, new ResourceLocation("textures/item/wood_handle.png")),
//    COPPER("copper_handle", 96, true, new ResourceLocation("textures/item/wood_handle.png")),
//    IRON("iron_handle", 128, true, new ResourceLocation("textures/item/wood_handle.png")),
//    GOLD("gold_handle", 196, true, new ResourceLocation("textures/item/wood_handle.png")),
//    DIAMOND("diamond_handle", 512, true, new ResourceLocation("textures/item/wood_handle.png")),
//    NETHERITE("netherite_handle", 1536, true, new ResourceLocation("textures/item/wood_handle.png")),
//    STEEL("steel_handle", 256, true, new ResourceLocation("textures/item/wood_handle.png")),
//    TITANIUM("titanium_handle", 1024, true, new ResourceLocation("textures/item/wood_handle.png")),
//    TUNGSTEN("tungsten_handle", 2048, true, new ResourceLocation("textures/item/wood_handle.png"));

    private final ResourceLocation toolHandleTexture;
    private final String name;
    private final String displayName;
    private final int durability;
    private final boolean canSwap;
    private final HandleItem handleItem;
    private final String toolTip;

    Handles(String name, String displayName, int durability, boolean canSwap, ResourceLocation texture){
        this.toolHandleTexture = texture;
        this.name = name;
        this.displayName = displayName;
        this.durability = durability;
        this.canSwap = canSwap;
        this.handleItem = new HandleItem(this);
        this.toolTip = "tooltip.socketcraft." + this.name;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public HandleItem getHandle(){
        return handleItem;
    }

    public int getDurability(){
        return durability;
    }

    public String getToolTip() {
        return toolTip;
    }

}
