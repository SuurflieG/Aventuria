/*
package com.gypsyhost.aventuria.custom.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;

public class SpecialBlockActions {
    private static final HashMap<Block, TriConsumer<Level, BlockPos, BlockState>> register = new HashMap<>();

    static {
        register.put(Blocks.ICE, (world, pos, state) -> {
            Material material = world.getBlockState(pos.below()).getMaterial();
            if (material.blocksMotion() || material.isLiquid())
                world.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
        });
    }

    public static HashMap<Block, TriConsumer<Level, BlockPos, BlockState>> getRegister() {
        return register;
    }
}
*/
