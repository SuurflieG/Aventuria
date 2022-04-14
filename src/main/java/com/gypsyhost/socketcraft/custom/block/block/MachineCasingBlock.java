package com.gypsyhost.socketcraft.custom.block.block;

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
            Block.box(0, 0, 0, 0.1875, 0.0625, 0.1875),
            Block.box(0, 0.0625, 0, 0.1875, 0.1875, 0.0625),
            Block.box(0, 0.0625, 0.0625, 0.0625, 0.1875, 0.1875),
            Block.box(0.0625, 0.0625, 0.0625, 0.125, 0.9375, 0.125),
            Block.box(0, 0.8125, 0, 0.1875, 0.9375, 0.0625),
            Block.box(0, 0.8125, 0.0625, 0.0625, 0.9375, 0.1875),
            Block.box(0, 0.9375, 0, 0.1875, 1, 0.1875),
            Block.box(0.8125, 0.9375, 0, 1, 1, 0.1875),
            Block.box(0.8125, 0.8125, 0, 0.9375, 0.9375, 0.0625),
            Block.box(0.9375, 0.8125, 0, 1, 0.9375, 0.1875),
            Block.box(0.875, 0.0625, 0.0625, 0.9375, 0.9375, 0.125),
            Block.box(0.8125, 0.0625, 0, 0.9375, 0.1875, 0.0625),
            Block.box(0.9375, 0.0625, 0, 1, 0.1875, 0.1875),
            Block.box(0.8125, 0, 0, 1, 0.0625, 0.1875),
            Block.box(0, 0.9375, 0.8125, 0.1875, 1, 1),
            Block.box(0.0625, 0.8125, 0.9375, 0.1875, 0.9375, 1),
            Block.box(0, 0.8125, 0.8125, 0.0625, 0.9375, 1),
            Block.box(0.0625, 0.0625, 0.875, 0.125, 0.9375, 0.9375),
            Block.box(0.0625, 0.0625, 0.9375, 0.1875, 0.1875, 1),
            Block.box(0, 0.0625, 0.8125, 0.0625, 0.1875, 1),
            Block.box(0, 0, 0.8125, 0.1875, 0.0625, 1),
            Block.box(0.8125, 0.9375, 0.8125, 1, 1, 1),
            Block.box(0.9375, 0.8125, 0.8125, 1, 0.9375, 0.9375),
            Block.box(0.8125, 0.8125, 0.9375, 1, 0.9375, 1),
            Block.box(0.875, 0.0625, 0.875, 0.9375, 0.9375, 0.9375),
            Block.box(0.9375, 0.0625, 0.8125, 1, 0.1875, 0.9375),
            Block.box(0.8125, 0.0625, 0.9375, 1, 0.1875, 1),
            Block.box(0.8125, 0, 0.8125, 1, 0.0625, 1),
            Block.box(0.0625, 0.0625, 0.125, 0.125, 0.125, 0.875),
            Block.box(0.875, 0.0625, 0.125, 0.9375, 0.125, 0.875),
            Block.box(0.875, 0.875, 0.125, 0.9375, 0.9375, 0.875),
            Block.box(0.0625, 0.875, 0.125, 0.125, 0.9375, 0.875),
            Block.box(0.125, 0.0625, 0.0625, 0.875, 0.125, 0.125),
            Block.box(0.125, 0.875, 0.0625, 0.875, 0.9375, 0.125),
            Block.box(0.125, 0.875, 0.875, 0.875, 0.9375, 0.9375),
            Block.box(0.125, 0.0625, 0.875, 0.875, 0.125, 0.9375)
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

