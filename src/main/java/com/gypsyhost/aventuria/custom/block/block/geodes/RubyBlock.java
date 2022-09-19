package com.gypsyhost.aventuria.custom.block.block.geodes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RubyBlock extends Block {
    public RubyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult blockHitResult, Projectile pProjectile) {
        if (!pLevel.isClientSide) {
            BlockPos blockpos = blockHitResult.getBlockPos();
            pLevel.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + pLevel.random.nextFloat() * 1.2F);
            pLevel.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + pLevel.random.nextFloat() * 1.2F);
        }
    }
}