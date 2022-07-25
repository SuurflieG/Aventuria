package com.gypsyhost.aventuria.custom.block.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class KilowattTransferCable extends CrossCollisionBlock {

    private final VoxelShape[] occlusionByIndex;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty UP = BlockStateProperties.UP;

    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (p_55164_) -> {
        p_55164_.put(Direction.NORTH, NORTH);
        p_55164_.put(Direction.EAST, EAST);
        p_55164_.put(Direction.SOUTH, SOUTH);
        p_55164_.put(Direction.WEST, WEST);
        p_55164_.put(Direction.UP, UP);
        p_55164_.put(Direction.DOWN, DOWN);
    }));

    public KilowattTransferCable(BlockBehaviour.Properties properties) {
        super(4.0F, 4.0F, 8.0F, 4.0F, 8.0F, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, Boolean.FALSE)
                .setValue(EAST, Boolean.FALSE)
                .setValue(SOUTH, Boolean.FALSE)
                .setValue(WEST, Boolean.FALSE)
                .setValue(DOWN, Boolean.FALSE)
                .setValue(UP, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE));
        this.occlusionByIndex = this.makeShapes(4.0F, 4.0F, 8.0F, 4.0F, 8.0F);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return this.occlusionByIndex[this.getAABBIndex(pState)];
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return this.getShape(pState, pReader, pPos, pContext);
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    public boolean connectsTo(BlockState pState, Direction pDirection) {
        boolean flag = this.isSameCable(pState);
        return !isExceptionForConnection(pState) && flag;
    }

    private boolean isSameCable(BlockState pState) {
        return pState.is(ModBlocks.KILOWATT_TRANSFER_CABLE.get()) || pState.is(ModBlocks.BASIC_SOLAR_PANEL.get());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter blockgetter = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockPos blockpos5 = blockpos.above();
        BlockPos blockpos6 = blockpos.below();
        BlockState blockstate = blockgetter.getBlockState(blockpos1);
        BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
        BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
        BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
        BlockState blockstate4 = blockgetter.getBlockState(blockpos5);
        BlockState blockstate5 = blockgetter.getBlockState(blockpos6);

        return super.getStateForPlacement(pContext)
                .setValue(NORTH, this.connectsTo(blockstate, Direction.SOUTH))
                .setValue(EAST, this.connectsTo(blockstate1, Direction.WEST))
                .setValue(SOUTH, this.connectsTo(blockstate2, Direction.NORTH))
                .setValue(WEST, this.connectsTo(blockstate3, Direction.EAST))
                .setValue(UP, this.connectsTo(blockstate4, Direction.DOWN))
                .setValue(DOWN, this.connectsTo(blockstate5, Direction.UP))
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return pFacing.getAxis().isHorizontal() || pFacing.getAxis().isVertical() ? pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing),
                this.connectsTo(pFacingState, pFacing.getOpposite())) : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, WEST, SOUTH, UP, DOWN, WATERLOGGED);
    }
}
