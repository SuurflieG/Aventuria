package com.gypsyhost.aventuria.custom.block.block;

import com.gypsyhost.aventuria.custom.block.entity.PressBlockEntity;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class PressBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PressBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(2, 1, 2, 14, 2, 14),
            Block.box(8, 2, 12, 13, 8, 13),
            Block.box(3, 2, 11, 5, 8, 12),
            Block.box(3, 2, 13, 5, 8, 14),
            Block.box(5, 2, 11, 6, 8, 14),
            Block.box(2, 2, 11, 3, 8, 14),
            Block.box(3, 2, 2, 5, 8, 3),
            Block.box(3, 2, 4, 5, 8, 5),
            Block.box(5, 2, 2, 6, 8, 5),
            Block.box(2, 2, 2, 3, 8, 5),
            Block.box(8, 2, 3, 13, 8, 4),
            Block.box(9, 3, 4, 12, 4, 12),
            Block.box(9, 6, 4, 12, 7, 12),
            Block.box(6, 2, 3, 8, 2.25, 4),
            Block.box(6, 2, 12, 8, 2.25, 13),
            Block.box(2, 1, 14, 14, 2, 15),
            Block.box(1, 1, 1, 2, 15, 2),
            Block.box(1, 1, 14, 2, 15, 15),
            Block.box(14, 1, 14, 15, 15, 15),
            Block.box(14, 1, 1, 15, 15, 2),
            Block.box(1, 1, 2, 2, 2, 14),
            Block.box(14, 1, 2, 15, 2, 14),
            Block.box(14, 14, 2, 15, 15, 14),
            Block.box(1, 14, 2, 2, 15, 14),
            Block.box(2, 1, 1, 14, 2, 2),
            Block.box(2, 14, 1, 14, 15, 2),
            Block.box(2, 14, 14, 14, 15, 15),
            Block.box(15, 13, 13, 16, 15, 15),
            Block.box(15, 1, 13, 16, 3, 15),
            Block.box(1, 13, 15, 3, 15, 16),
            Block.box(13, 13, 15, 16, 15, 16),
            Block.box(13, 15, 13, 16, 16, 16),
            Block.box(0, 15, 13, 3, 16, 16),
            Block.box(0, 13, 13, 1, 15, 16),
            Block.box(13, 0, 13, 16, 1, 16),
            Block.box(13, 1, 15, 16, 3, 16),
            Block.box(1, 1, 15, 3, 3, 16),
            Block.box(0, 1, 13, 1, 3, 16),
            Block.box(0, 0, 13, 3, 1, 16),
            Block.box(13, 0, 0, 16, 1, 3),
            Block.box(13, 1, 0, 15, 3, 1),
            Block.box(15, 1, 0, 16, 3, 3),
            Block.box(15, 13, 0, 16, 15, 3),
            Block.box(13, 13, 0, 15, 15, 1),
            Block.box(13, 15, 0, 16, 16, 3),
            Block.box(0, 15, 0, 3, 16, 3),
            Block.box(0, 13, 0, 3, 15, 1),
            Block.box(0, 13, 1, 1, 15, 3),
            Block.box(0, 1, 0, 3, 3, 1),
            Block.box(0, 1, 1, 1, 3, 3),
            Block.box(0, 0, 0, 3, 1, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(2, 1, 2, 14, 2, 14),
            Block.box(3, 2, 3, 8, 8, 4),
            Block.box(11, 2, 4, 13, 8, 5),
            Block.box(11, 2, 2, 13, 8, 3),
            Block.box(10, 2, 2, 11, 8, 5),
            Block.box(13, 2, 2, 14, 8, 5),
            Block.box(11, 2, 13, 13, 8, 14),
            Block.box(11, 2, 11, 13, 8, 12),
            Block.box(10, 2, 11, 11, 8, 14),
            Block.box(13, 2, 11, 14, 8, 14),
            Block.box(3, 2, 12, 8, 8, 13),
            Block.box(4, 3, 4, 7, 4, 12),
            Block.box(4, 6, 4, 7, 7, 12),
            Block.box(8, 2, 12, 10, 2.25, 13),
            Block.box(8, 2, 3, 10, 2.25, 4),
            Block.box(2, 1, 1, 14, 2, 2),
            Block.box(14, 1, 14, 15, 15, 15),
            Block.box(14, 1, 1, 15, 15, 2),
            Block.box(1, 1, 1, 2, 15, 2),
            Block.box(1, 1, 14, 2, 15, 15),
            Block.box(14, 1, 2, 15, 2, 14),
            Block.box(1, 1, 2, 2, 2, 14),
            Block.box(1, 14, 2, 2, 15, 14),
            Block.box(14, 14, 2, 15, 15, 14),
            Block.box(2, 1, 14, 14, 2, 15),
            Block.box(2, 14, 14, 14, 15, 15),
            Block.box(2, 14, 1, 14, 15, 2),
            Block.box(0, 13, 1, 1, 15, 3),
            Block.box(0, 1, 1, 1, 3, 3),
            Block.box(13, 13, 0, 15, 15, 1),
            Block.box(0, 13, 0, 3, 15, 1),
            Block.box(0, 15, 0, 3, 16, 3),
            Block.box(13, 15, 0, 16, 16, 3),
            Block.box(15, 13, 0, 16, 15, 3),
            Block.box(0, 0, 0, 3, 1, 3),
            Block.box(0, 1, 0, 3, 3, 1),
            Block.box(13, 1, 0, 15, 3, 1),
            Block.box(15, 1, 0, 16, 3, 3),
            Block.box(13, 0, 0, 16, 1, 3),
            Block.box(0, 0, 13, 3, 1, 16),
            Block.box(1, 1, 15, 3, 3, 16),
            Block.box(0, 1, 13, 1, 3, 16),
            Block.box(0, 13, 13, 1, 15, 16),
            Block.box(1, 13, 15, 3, 15, 16),
            Block.box(0, 15, 13, 3, 16, 16),
            Block.box(13, 15, 13, 16, 16, 16),
            Block.box(13, 13, 15, 16, 15, 16),
            Block.box(15, 13, 13, 16, 15, 15),
            Block.box(13, 1, 15, 16, 3, 16),
            Block.box(15, 1, 13, 16, 3, 15),
            Block.box(13, 0, 13, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(2, 1, 2, 14, 2, 14),
            Block.box(12, 2, 3, 13, 8, 8),
            Block.box(11, 2, 11, 12, 8, 13),
            Block.box(13, 2, 11, 14, 8, 13),
            Block.box(11, 2, 10, 14, 8, 11),
            Block.box(11, 2, 13, 14, 8, 14),
            Block.box(2, 2, 11, 3, 8, 13),
            Block.box(4, 2, 11, 5, 8, 13),
            Block.box(2, 2, 10, 5, 8, 11),
            Block.box(2, 2, 13, 5, 8, 14),
            Block.box(3, 2, 3, 4, 8, 8),
            Block.box(4, 3, 4, 12, 4, 7),
            Block.box(4, 6, 4, 12, 7, 7),
            Block.box(3, 2, 8, 4, 2.25, 10),
            Block.box(12, 2, 8, 13, 2.25, 10),
            Block.box(14, 1, 2, 15, 2, 14),
            Block.box(1, 1, 14, 2, 15, 15),
            Block.box(14, 1, 14, 15, 15, 15),
            Block.box(14, 1, 1, 15, 15, 2),
            Block.box(1, 1, 1, 2, 15, 2),
            Block.box(2, 1, 14, 14, 2, 15),
            Block.box(2, 1, 1, 14, 2, 2),
            Block.box(2, 14, 1, 14, 15, 2),
            Block.box(2, 14, 14, 14, 15, 15),
            Block.box(1, 1, 2, 2, 2, 14),
            Block.box(1, 14, 2, 2, 15, 14),
            Block.box(14, 14, 2, 15, 15, 14),
            Block.box(13, 13, 0, 15, 15, 1),
            Block.box(13, 1, 0, 15, 3, 1),
            Block.box(15, 13, 13, 16, 15, 15),
            Block.box(15, 13, 0, 16, 15, 3),
            Block.box(13, 15, 0, 16, 16, 3),
            Block.box(13, 15, 13, 16, 16, 16),
            Block.box(13, 13, 15, 16, 15, 16),
            Block.box(13, 0, 0, 16, 1, 3),
            Block.box(15, 1, 0, 16, 3, 3),
            Block.box(15, 1, 13, 16, 3, 15),
            Block.box(13, 1, 15, 16, 3, 16),
            Block.box(13, 0, 13, 16, 1, 16),
            Block.box(0, 0, 0, 3, 1, 3),
            Block.box(0, 1, 1, 1, 3, 3),
            Block.box(0, 1, 0, 3, 3, 1),
            Block.box(0, 13, 0, 3, 15, 1),
            Block.box(0, 13, 1, 1, 15, 3),
            Block.box(0, 15, 0, 3, 16, 3),
            Block.box(0, 15, 13, 3, 16, 16),
            Block.box(0, 13, 13, 1, 15, 16),
            Block.box(1, 13, 15, 3, 15, 16),
            Block.box(0, 1, 13, 1, 3, 16),
            Block.box(1, 1, 15, 3, 3, 16),
            Block.box(0, 0, 13, 3, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(2, 1, 2, 14, 2, 14),
            Block.box(3, 2, 8, 4, 8, 13),
            Block.box(4, 2, 3, 5, 8, 5),
            Block.box(2, 2, 3, 3, 8, 5),
            Block.box(2, 2, 5, 5, 8, 6),
            Block.box(2, 2, 2, 5, 8, 3),
            Block.box(13, 2, 3, 14, 8, 5),
            Block.box(11, 2, 3, 12, 8, 5),
            Block.box(11, 2, 5, 14, 8, 6),
            Block.box(11, 2, 2, 14, 8, 3),
            Block.box(12, 2, 8, 13, 8, 13),
            Block.box(4, 3, 9, 12, 4, 12),
            Block.box(4, 6, 9, 12, 7, 12),
            Block.box(12, 2, 6, 13, 2.25, 8),
            Block.box(3, 2, 6, 4, 2.25, 8),
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
        switch (pState.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
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
    // this method is needed else the block entity will be invisible
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PressBlockEntity) {
                ((PressBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof PressBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (PressBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PressBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.PRESS.get(), PressBlockEntity::tick);
    }
}
