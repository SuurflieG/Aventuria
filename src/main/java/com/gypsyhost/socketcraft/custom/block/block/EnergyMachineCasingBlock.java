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

public class EnergyMachineCasingBlock extends HorizontalDirectionalBlock {
    public EnergyMachineCasingBlock(Properties properties) {
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
            Block.box(13, 0, 0, 16, 1, 3),
            Block.box(12, 3, 15, 13, 4, 16),
            Block.box(11, 4, 15, 12, 5, 16),
            Block.box(10, 5, 15, 11, 6, 16),
            Block.box(6, 6, 15, 10, 10, 16),
            Block.box(10, 10, 15, 11, 11, 16),
            Block.box(11, 11, 15, 12, 12, 16),
            Block.box(12, 12, 15, 13, 13, 16),
            Block.box(5, 10, 15, 6, 11, 16),
            Block.box(4, 11, 15, 5, 12, 16),
            Block.box(3, 12, 15, 4, 13, 16),
            Block.box(5, 5, 15, 6, 6, 16),
            Block.box(4, 4, 15, 5, 5, 16),
            Block.box(3, 3, 15, 4, 4, 16),
            Block.box(3, 3, 0, 4, 4, 1),
            Block.box(4, 4, 0, 5, 5, 1),
            Block.box(5, 5, 0, 6, 6, 1),
            Block.box(6, 6, 0, 10, 10, 1),
            Block.box(5, 10, 0, 6, 11, 1),
            Block.box(4, 11, 0, 5, 12, 1),
            Block.box(3, 12, 0, 4, 13, 1),
            Block.box(10, 10, 0, 11, 11, 1),
            Block.box(11, 11, 0, 12, 12, 1),
            Block.box(12, 12, 0, 13, 13, 1),
            Block.box(10, 5, 0, 11, 6, 1),
            Block.box(11, 4, 0, 12, 5, 1),
            Block.box(12, 3, 0, 13, 4, 1),
            Block.box(15, 3, 12, 16, 4, 13),
            Block.box(15, 4, 11, 16, 5, 12),
            Block.box(15, 5, 10, 16, 6, 11),
            Block.box(15, 6, 6, 16, 10, 10),
            Block.box(15, 10, 10, 16, 11, 11),
            Block.box(15, 11, 11, 16, 12, 12),
            Block.box(15, 12, 12, 16, 13, 13),
            Block.box(15, 10, 5, 16, 11, 6),
            Block.box(15, 11, 4, 16, 12, 5),
            Block.box(15, 12, 3, 16, 13, 4),
            Block.box(15, 5, 5, 16, 6, 6),
            Block.box(15, 4, 4, 16, 5, 5),
            Block.box(15, 3, 3, 16, 4, 4),
            Block.box(0, 3, 12, 1, 4, 13),
            Block.box(0, 4, 11, 1, 5, 12),
            Block.box(0, 5, 10, 1, 6, 11),
            Block.box(0, 6, 6, 1, 10, 10),
            Block.box(0, 10, 10, 1, 11, 11),
            Block.box(0, 11, 11, 1, 12, 12),
            Block.box(0, 12, 12, 1, 13, 13),
            Block.box(0, 10, 5, 1, 11, 6),
            Block.box(0, 11, 4, 1, 12, 5),
            Block.box(0, 12, 3, 1, 13, 4),
            Block.box(0, 5, 5, 1, 6, 6),
            Block.box(0, 4, 4, 1, 5, 5),
            Block.box(0, 3, 3, 1, 4, 4),
            Block.box(12, 15, 12, 13, 16, 13),
            Block.box(11, 15, 11, 12, 16, 12),
            Block.box(10, 15, 10, 11, 16, 11),
            Block.box(6, 15, 6, 10, 16, 10),
            Block.box(5, 15, 10, 6, 16, 11),
            Block.box(4, 15, 11, 5, 16, 12),
            Block.box(3, 15, 12, 4, 16, 13),
            Block.box(5, 15, 5, 6, 16, 6),
            Block.box(4, 15, 4, 5, 16, 5),
            Block.box(3, 15, 3, 4, 16, 4),
            Block.box(10, 15, 5, 11, 16, 6),
            Block.box(11, 15, 4, 12, 16, 5),
            Block.box(12, 15, 3, 13, 16, 4),
            Block.box(12, 0, 12, 13, 1, 13),
            Block.box(11, 0, 11, 12, 1, 12),
            Block.box(10, 0, 10, 11, 1, 11),
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(5, 0, 10, 6, 1, 11),
            Block.box(4, 0, 11, 5, 1, 12),
            Block.box(3, 0, 12, 4, 1, 13),
            Block.box(5, 0, 5, 6, 1, 6),
            Block.box(4, 0, 4, 5, 1, 5),
            Block.box(3, 0, 3, 4, 1, 4),
            Block.box(10, 0, 5, 11, 1, 6),
            Block.box(11, 0, 4, 12, 1, 5),
            Block.box(12, 0, 3, 13, 1, 4)
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

