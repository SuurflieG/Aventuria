package com.gypsyhost.aventuria.custom.block.block.geodes.budding;

import com.gypsyhost.aventuria.custom.block.block.geodes.AventurineBlock;
import com.gypsyhost.aventuria.custom.block.block.geodes.cluster.AventurineClusterBlock;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import java.util.Random;

public class BuddingAventurineBlock extends AventurineBlock {

    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingAventurineBlock(BlockBehaviour.Properties pProperties) {
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
                block = ModBlocks.SMALL_AVENTURINE_BUD.get();
            } else if (blockstate.is(ModBlocks.SMALL_AVENTURINE_BUD.get()) && blockstate.getValue(AventurineClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_AVENTURINE_BUD.get();
            } else if (blockstate.is(ModBlocks.MEDIUM_AVENTURINE_BUD.get()) && blockstate.getValue(AventurineClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_AVENTURINE_BUD.get();
            } else if (blockstate.is(ModBlocks.LARGE_AVENTURINE_BUD.get()) && blockstate.getValue(AventurineClusterBlock.FACING) == direction) {
                block = ModBlocks.AVENTURINE_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AventurineClusterBlock.FACING, direction).setValue(AventurineClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }


    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }

}
