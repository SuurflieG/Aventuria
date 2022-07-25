package com.gypsyhost.aventuria.custom.block.block;

import com.gypsyhost.aventuria.custom.block.entity.BasicSolarPanelBlockEntity;
import com.gypsyhost.aventuria.custom.util.CustomBlockStateProperties;
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

public class BasicSolarPanelBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BasicSolarPanelBlock(Properties pProperties) {
        super(pProperties.lightLevel(state -> state.getValue(CustomBlockStateProperties.HAS_ENERGY) ? 15 : 0));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return BasicSolarPanelBlock.Shape.getFromFacing(state.getValue(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(CustomBlockStateProperties.HAS_ENERGY, false);
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
        pBuilder.add(CustomBlockStateProperties.HAS_ENERGY);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {

        if (pState.getBlock() != pNewState.getBlock()) {
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof BasicSolarPanelBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer) pPlayer), (BasicSolarPanelBlockEntity) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BasicSolarPanelBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.BASIC_SOLAR_PANEL.get(), BasicSolarPanelBlockEntity::tick);
    }

    // this method is needed else the block entity will be invisible
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    private enum Shape {
        NORTH(Stream.of(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(1, 1, 1, 15, 17, 15),
                Block.box(6, 6, 15, 10, 10, 16),
                Block.box(6, 6, 0, 10, 10, 1),
                Block.box(0, 6, 6, 1, 10, 10),
                Block.box(15, 6, 6, 16, 10, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        EAST(Stream.of(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(1, 1, 1, 15, 17, 15),
                Block.box(6, 6, 15, 10, 10, 16),
                Block.box(6, 6, 0, 10, 10, 1),
                Block.box(0, 6, 6, 1, 10, 10),
                Block.box(15, 6, 6, 16, 10, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        SOUTH(Stream.of(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(1, 1, 1, 15, 17, 15),
                Block.box(6, 6, 15, 10, 10, 16),
                Block.box(6, 6, 0, 10, 10, 1),
                Block.box(0, 6, 6, 1, 10, 10),
                Block.box(15, 6, 6, 16, 10, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()),
        WEST(Stream.of(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(1, 1, 1, 15, 17, 15),
                Block.box(6, 6, 15, 10, 10, 16),
                Block.box(6, 6, 0, 10, 10, 1),
                Block.box(0, 6, 6, 1, 10, 10),
                Block.box(15, 6, 6, 16, 10, 10)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());
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