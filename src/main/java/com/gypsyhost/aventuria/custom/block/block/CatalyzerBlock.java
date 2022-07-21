package com.gypsyhost.aventuria.custom.block.block;

import com.gypsyhost.aventuria.custom.block.entity.CatalyzerBlockEntity;
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


public class CatalyzerBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CatalyzerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
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

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return CatalyzerBlock.Shape.getFromFacing(state.getValue(FACING));
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
            if (blockEntity instanceof CatalyzerBlockEntity) {
                ((CatalyzerBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof CatalyzerBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (CatalyzerBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CatalyzerBlockEntity(pPos, pState);
    }



    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.CATALYZER.get(), CatalyzerBlockEntity::tick);
    }

    private enum Shape {
        NORTH(Stream.of(
                Block.box(0, 0, 0, 16, 9, 16),
                Block.box(6, 9, 6, 10, 10, 10),
                Block.box(1, 9, 12, 4, 10, 15),
                Block.box(12, 9, 7, 14, 10, 9),
                Block.box(2, 9, 7, 4, 10, 9),
                Block.box(7, 9, 12, 9, 10, 14),
                Block.box(7, 9, 2, 9, 10, 4),
                Block.box(0, 9, 0, 0, 16, 16),
                Block.box(16, 9, 0, 16, 16, 16),
                Block.box(0, 9, 16, 16, 16, 16),
                Block.box(0, 16, 0, 16, 16, 16),
                Block.box(0, 9, 0, 16, 16, 0)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        EAST(Stream.of(
                Block.box(0, 0, 0, 16, 9, 16),
                Block.box(6, 9, 6, 10, 10, 10),
                Block.box(12, 9, 12, 15, 10, 15),
                Block.box(7, 9, 2, 9, 10, 4),
                Block.box(7, 9, 12, 9, 10, 14),
                Block.box(12, 9, 7, 14, 10, 9),
                Block.box(2, 9, 7, 4, 10, 9),
                Block.box(0, 9, 16, 16, 16, 16),
                Block.box(0, 9, 0, 16, 16, 0),
                Block.box(16, 9, 0, 16, 16, 16),
                Block.box(0, 16, 0, 16, 16, 16),
                Block.box(0, 9, 0, 0, 16, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        SOUTH(Stream.of(
                Block.box(0, 0, 0, 16, 9, 16),
                Block.box(6, 9, 6, 10, 10, 10),
                Block.box(12, 9, 1, 15, 10, 4),
                Block.box(2, 9, 7, 4, 10, 9),
                Block.box(12, 9, 7, 14, 10, 9),
                Block.box(7, 9, 2, 9, 10, 4),
                Block.box(7, 9, 12, 9, 10, 14),
                Block.box(16, 9, 0, 16, 16, 16),
                Block.box(0, 9, 0, 0, 16, 16),
                Block.box(0, 9, 0, 16, 16, 0),
                Block.box(0, 16, 0, 16, 16, 16),
                Block.box(0, 9, 16, 16, 16, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        WEST(Stream.of(
                Block.box(0, 0, 0, 16, 9, 16),
                Block.box(6, 9, 6, 10, 10, 10),
                Block.box(1, 9, 1, 4, 10, 4),
                Block.box(7, 9, 12, 9, 10, 14),
                Block.box(7, 9, 2, 9, 10, 4),
                Block.box(2, 9, 7, 4, 10, 9),
                Block.box(12, 9, 7, 14, 10, 9),
                Block.box(0, 9, 0, 16, 16, 0),
                Block.box(0, 9, 16, 16, 16, 16),
                Block.box(0, 9, 0, 0, 16, 16),
                Block.box(0, 16, 0, 16, 16, 16),
                Block.box(16, 9, 0, 16, 16, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());
        private final VoxelShape shape;

        Shape(VoxelShape shape) {
            this.shape = shape;
        }

        public static VoxelShape getFromFacing(Direction facing) {
            return switch (facing) {
                case NORTH -> NORTH.shape;
                case EAST -> EAST.shape;
                case SOUTH -> SOUTH.shape;
                case WEST -> WEST.shape;
                default -> throw new IllegalStateException("Invalid facing");
            };
        }
    }
}
