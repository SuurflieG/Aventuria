package com.gypsyhost.aventuria.custom.util;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class CustomBlockStateProperties {

    //public static final EnumProperty<RailShapeHelper> RAIL_SHAPE = EnumProperty.create("shape", RailShapeHelper.class);
    public static final BooleanProperty HAS_ENERGY = BooleanProperty.create("has_energy");

    public static final BooleanProperty EXTRACT_UP = BooleanProperty.create("extract_up");
    public static final BooleanProperty EXTRACT_DOWN = BooleanProperty.create("extract_down");
    public static final BooleanProperty EXTRACT_NORTH = BooleanProperty.create("extract_north");
    public static final BooleanProperty EXTRACT_EAST = BooleanProperty.create("extract_east");
    public static final BooleanProperty EXTRACT_SOUTH = BooleanProperty.create("extract_south");
    public static final BooleanProperty EXTRACT_WEST = BooleanProperty.create("extract_west");

    public static final BooleanProperty INPUT_UP = BooleanProperty.create("input_up");
    public static final BooleanProperty INPUT_DOWN = BooleanProperty.create("input_down");
    public static final BooleanProperty INPUT_NORTH = BooleanProperty.create("input_north");
    public static final BooleanProperty INPUT_EAST = BooleanProperty.create("input_east");
    public static final BooleanProperty INPUT_SOUTH = BooleanProperty.create("input_south");
    public static final BooleanProperty INPUT_WEST = BooleanProperty.create("input_west");


}
