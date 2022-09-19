package com.gypsyhost.aventuria.custom.block.block.geodes.budding;

import com.gypsyhost.aventuria.custom.block.block.geodes.AventurineBlock;
import com.gypsyhost.aventuria.custom.block.block.geodes.RubyBlock;
import com.gypsyhost.aventuria.custom.block.block.geodes.cluster.AventurineClusterBlock;
import com.gypsyhost.aventuria.custom.block.block.geodes.cluster.RubyClusterBlock;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import java.util.Random;

public class BuddingRubyBlock extends RubyBlock {

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingRubyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = ModBlocks.SMALL_RUBY_BUD.get();
            } else if (blockstate.is(ModBlocks.SMALL_RUBY_BUD.get()) && blockstate.getValue(RubyClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_RUBY_BUD.get();
            } else if (blockstate.is(ModBlocks.MEDIUM_RUBY_BUD.get()) && blockstate.getValue(RubyClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_RUBY_BUD.get();
            } else if (blockstate.is(ModBlocks.LARGE_RUBY_BUD.get()) && blockstate.getValue(RubyClusterBlock.FACING) == direction) {
                block = ModBlocks.RUBY_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(RubyClusterBlock.FACING, direction).setValue(RubyClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }

}
