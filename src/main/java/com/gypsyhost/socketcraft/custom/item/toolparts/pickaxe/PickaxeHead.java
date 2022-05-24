package com.gypsyhost.socketcraft.custom.item.toolparts.pickaxe;

import net.minecraft.resources.ResourceLocation;

public enum PickaxeHead {

    WOOD("wood_pickaxe_head", "Wood" , 32, true, new ResourceLocation("textures/item/wood_pickaxe_head.png"));
//    STONE("stone_pickaxe_head", "Stone" , 64, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    COPPER("copper_pickaxe_head", "Copper" , 96, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    IRON("iron_pickaxe_head", "Iron" , 128, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    GOLD("gold_pickaxe_head", "Gold" , 196, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    DIAMOND("diamond_pickaxe_head", "Diamond" , 512, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    NETHERITE("netherite_pickaxe_head", "Netherite" , 1536, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    STEEL("steel_pickaxe_head", "Steel" , 256, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    TITANIUM("titanium_pickaxe_head", "Titanium" , 1024, true, new ResourceLocation("textures/item/wood_pickaxe_head.png")),
//    TUNGSTEN("tungsten_pickaxe_head", "Tungsten" , 2048, true, new ResourceLocation("textures/item/wood_pickaxe_head.png"));

    private final ResourceLocation pickaxeHeadTexture;
    private final String name;
    private final String displayName;
    private final int durability;
    private final boolean canSwap;
    private final PickaxeHeadItem pickaxeHeadItem;
    private final String toolTip;

    PickaxeHead(String name, String displayName, int durability, boolean canSwap, ResourceLocation texture){
        this.pickaxeHeadTexture = texture;
        this.name = name;
        this.displayName = displayName;
        this.durability = durability;
        this.canSwap = canSwap;
        this.pickaxeHeadItem = new PickaxeHeadItem(this);
        this.toolTip = "tooltip.socketcraft." + this.name;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public PickaxeHeadItem getPickaxeHead(){
        return pickaxeHeadItem;
    }

    public int getDurability(){
        return durability;
    }

    public String getToolTip() {
        return toolTip;
    }

}