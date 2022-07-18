package com.gypsyhost.aventuria.custom.block.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class MachineCasingBlock extends HorizontalDirectionalBlock {
    public MachineCasingBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(1, 1, 2, 2, 2, 14),
            Block.box(14, 1, 1, 15, 15, 2),
            Block.box(1, 1, 1, 2, 15, 2),
            Block.box(1, 1, 14, 2, 15, 15),
            Block.box(14, 1, 14, 15, 15, 15),
            Block.box(2, 1, 1, 14, 2, 2),
            Block.box(2, 1, 14, 14, 2, 15),
            Block.box(2, 14, 14, 14, 15, 15),
            Block.box(2, 14, 1, 14, 15, 2),
            Block.box(14, 1, 2, 15, 2, 14),
            Block.box(14, 14, 2, 15, 15, 14),
            Block.box(1, 14, 2, 2, 15, 14),
            Block.box(1, 13, 15, 3, 15, 16),
            Block.box(1, 1, 15, 3, 3, 16),
            Block.box(0, 13, 1, 1, 15, 3),
            Block.box(0, 13, 13, 1, 15, 16),
            Block.box(0, 15, 13, 3, 16, 16),
            Block.box(0, 15, 0, 3, 16, 3),
            Block.box(0, 13, 0, 3, 15, 1),
            Block.box(0, 0, 13, 3, 1, 16),
            Block.box(0, 1, 13, 1, 3, 16),
            Block.box(0, 1, 1, 1, 3, 3),
            Block.box(0, 1, 0, 3, 3, 1),
            Block.box(0, 0, 0, 3, 1, 3),
            Block.box(13, 0, 13, 16, 1, 16),
            Block.box(15, 1, 13, 16, 3, 15),
            Block.box(13, 1, 15, 16, 3, 16),
            Block.box(13, 13, 15, 16, 15, 16),
            Block.box(15, 13, 13, 16, 15, 15),
            Block.box(13, 15, 13, 16, 16, 16),
            Block.box(13, 15, 0, 16, 16, 3),
            Block.box(15, 13, 0, 16, 15, 3),
            Block.box(13, 13, 0, 15, 15, 1),
            Block.box(15, 1, 0, 16, 3, 3),
            Block.box(13, 1, 0, 15, 3, 1),
            Block.box(13, 0, 0, 16, 1, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

}

