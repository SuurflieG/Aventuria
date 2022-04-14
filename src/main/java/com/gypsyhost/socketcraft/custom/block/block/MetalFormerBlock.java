package com.gypsyhost.socketcraft.custom.block.block;

import com.gypsyhost.socketcraft.custom.block.entity.MetalFormerBlockEntity;
import com.gypsyhost.socketcraft.registry.ModBlockEntities;
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

public class MetalFormerBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public MetalFormerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_N = Stream.of(
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
            Block.box(0.125, 0.0625, 0.875, 0.875, 0.125, 0.9375),
            Block.box(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875),
            Block.box(0.125, 0.125, 0.125, 0.1875, 0.5, 0.3125),
            Block.box(0.3125, 0.125, 0.125, 0.375, 0.5, 0.3125),
            Block.box(0.1875, 0.125, 0.25, 0.3125, 0.5, 0.3125),
            Block.box(0.1875, 0.125, 0.125, 0.3125, 0.5, 0.1875),
            Block.box(0.1875, 0.125, 0.6875, 0.3125, 0.5, 0.75),
            Block.box(0.1875, 0.125, 0.8125, 0.3125, 0.5, 0.875),
            Block.box(0.3125, 0.125, 0.6875, 0.375, 0.5, 0.875),
            Block.box(0.125, 0.125, 0.6875, 0.1875, 0.5, 0.875),
            Block.box(0.5, 0.125, 0.75, 0.8125, 0.5, 0.8125),
            Block.box(0.5, 0.125, 0.1875, 0.8125, 0.5, 0.25),
            Block.box(0.5625, 0.1875, 0.25, 0.75, 0.25, 0.75),
            Block.box(0.5625, 0.375, 0.25, 0.75, 0.4375, 0.75),
            Block.box(0.375, 0.125, 0.1875, 0.5, 0.140625, 0.25),
            Block.box(0.375, 0.125, 0.75, 0.5, 0.140625, 0.8125)

    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(

            Block.box(0.8125, 0, 0, 1, 0.0625, 0.1875),
            Block.box(0.9375, 0.0625, 0, 1, 0.1875, 0.1875),
            Block.box(0.8125, 0.0625, 0, 0.9375, 0.1875, 0.0625),
            Block.box(0.875, 0.0625, 0.0625, 0.9375, 0.9375, 0.125),
            Block.box(0.9375, 0.8125, 0, 1, 0.9375, 0.1875),
            Block.box(0.8125, 0.8125, 0, 0.9375, 0.9375, 0.0625),
            Block.box(0.8125, 0.9375, 0, 1, 1, 0.1875),
            Block.box(0.8125, 0.9375, 0.8125, 1, 1, 1),
            Block.box(0.9375, 0.8125, 0.8125, 1, 0.9375, 0.9375),
            Block.box(0.8125, 0.8125, 0.9375, 1, 0.9375, 1),
            Block.box(0.875, 0.0625, 0.875, 0.9375, 0.9375, 0.9375),
            Block.box(0.9375, 0.0625, 0.8125, 1, 0.1875, 0.9375),
            Block.box(0.8125, 0.0625, 0.9375, 1, 0.1875, 1),
            Block.box(0.8125, 0, 0.8125, 1, 0.0625, 1),
            Block.box(0, 0.9375, 0, 0.1875, 1, 0.1875),
            Block.box(0, 0.8125, 0.0625, 0.0625, 0.9375, 0.1875),
            Block.box(0, 0.8125, 0, 0.1875, 0.9375, 0.0625),
            Block.box(0.0625, 0.0625, 0.0625, 0.125, 0.9375, 0.125),
            Block.box(0, 0.0625, 0.0625, 0.0625, 0.1875, 0.1875),
            Block.box(0, 0.0625, 0, 0.1875, 0.1875, 0.0625),
            Block.box(0, 0, 0, 0.1875, 0.0625, 0.1875),
            Block.box(0, 0.9375, 0.8125, 0.1875, 1, 1),
            Block.box(0.0625, 0.8125, 0.9375, 0.1875, 0.9375, 1),
            Block.box(0, 0.8125, 0.8125, 0.0625, 0.9375, 1),
            Block.box(0.0625, 0.0625, 0.875, 0.125, 0.9375, 0.9375),
            Block.box(0.0625, 0.0625, 0.9375, 0.1875, 0.1875, 1),
            Block.box(0, 0.0625, 0.8125, 0.0625, 0.1875, 1),
            Block.box(0, 0, 0.8125, 0.1875, 0.0625, 1),
            Block.box(0.125, 0.0625, 0.0625, 0.875, 0.125, 0.125),
            Block.box(0.125, 0.0625, 0.875, 0.875, 0.125, 0.9375),
            Block.box(0.125, 0.875, 0.875, 0.875, 0.9375, 0.9375),
            Block.box(0.125, 0.875, 0.0625, 0.875, 0.9375, 0.125),
            Block.box(0.875, 0.0625, 0.125, 0.9375, 0.125, 0.875),
            Block.box(0.875, 0.875, 0.125, 0.9375, 0.9375, 0.875),
            Block.box(0.0625, 0.875, 0.125, 0.125, 0.9375, 0.875),
            Block.box(0.0625, 0.0625, 0.125, 0.125, 0.125, 0.875),
            Block.box(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875),
            Block.box(0.6875, 0.125, 0.125, 0.875, 0.5, 0.1875),
            Block.box(0.6875, 0.125, 0.3125, 0.875, 0.5, 0.375),
            Block.box(0.6875, 0.125, 0.1875, 0.75, 0.5, 0.3125),
            Block.box(0.8125, 0.125, 0.1875, 0.875, 0.5, 0.3125),
            Block.box(0.25, 0.125, 0.1875, 0.3125, 0.5, 0.3125),
            Block.box(0.125, 0.125, 0.1875, 0.1875, 0.5, 0.3125),
            Block.box(0.125, 0.125, 0.3125, 0.3125, 0.5, 0.375),
            Block.box(0.125, 0.125, 0.125, 0.3125, 0.5, 0.1875),
            Block.box(0.1875, 0.125, 0.5, 0.25, 0.5, 0.8125),
            Block.box(0.75, 0.125, 0.5, 0.8125, 0.5, 0.8125),
            Block.box(0.25, 0.1875, 0.5625, 0.75, 0.25, 0.75),
            Block.box(0.25, 0.375, 0.5625, 0.75, 0.4375, 0.75),
            Block.box(0.75, 0.125, 0.375, 0.8125, 0.140625, 0.5),
            Block.box(0.1875, 0.125, 0.375, 0.25, 0.140625, 0.5)

    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(0, 0, 0.8125, 0.1875, 0.0625, 1),
            Block.box(0, 0.0625, 0.8125, 0.0625, 0.1875, 1),
            Block.box(0.0625, 0.0625, 0.9375, 0.1875, 0.1875, 1),
            Block.box(0.0625, 0.0625, 0.875, 0.125, 0.9375, 0.9375),
            Block.box(0, 0.8125, 0.8125, 0.0625, 0.9375, 1),
            Block.box(0.0625, 0.8125, 0.9375, 0.1875, 0.9375, 1),
            Block.box(0, 0.9375, 0.8125, 0.1875, 1, 1),
            Block.box(0, 0.9375, 0, 0.1875, 1, 0.1875),
            Block.box(0, 0.8125, 0.0625, 0.0625, 0.9375, 0.1875),
            Block.box(0, 0.8125, 0, 0.1875, 0.9375, 0.0625),
            Block.box(0.0625, 0.0625, 0.0625, 0.125, 0.9375, 0.125),
            Block.box(0, 0.0625, 0.0625, 0.0625, 0.1875, 0.1875),
            Block.box(0, 0.0625, 0, 0.1875, 0.1875, 0.0625),
            Block.box(0, 0, 0, 0.1875, 0.0625, 0.1875),
            Block.box(0.8125, 0.9375, 0.8125, 1, 1, 1),
            Block.box(0.9375, 0.8125, 0.8125, 1, 0.9375, 0.9375),
            Block.box(0.8125, 0.8125, 0.9375, 1, 0.9375, 1),
            Block.box(0.875, 0.0625, 0.875, 0.9375, 0.9375, 0.9375),
            Block.box(0.9375, 0.0625, 0.8125, 1, 0.1875, 0.9375),
            Block.box(0.8125, 0.0625, 0.9375, 1, 0.1875, 1),
            Block.box(0.8125, 0, 0.8125, 1, 0.0625, 1),
            Block.box(0.8125, 0.9375, 0, 1, 1, 0.1875),
            Block.box(0.8125, 0.8125, 0, 0.9375, 0.9375, 0.0625),
            Block.box(0.9375, 0.8125, 0, 1, 0.9375, 0.1875),
            Block.box(0.875, 0.0625, 0.0625, 0.9375, 0.9375, 0.125),
            Block.box(0.8125, 0.0625, 0, 0.9375, 0.1875, 0.0625),
            Block.box(0.9375, 0.0625, 0, 1, 0.1875, 0.1875),
            Block.box(0.8125, 0, 0, 1, 0.0625, 0.1875),
            Block.box(0.125, 0.0625, 0.875, 0.875, 0.125, 0.9375),
            Block.box(0.125, 0.0625, 0.0625, 0.875, 0.125, 0.125),
            Block.box(0.125, 0.875, 0.0625, 0.875, 0.9375, 0.125),
            Block.box(0.125, 0.875, 0.875, 0.875, 0.9375, 0.9375),
            Block.box(0.0625, 0.0625, 0.125, 0.125, 0.125, 0.875),
            Block.box(0.0625, 0.875, 0.125, 0.125, 0.9375, 0.875),
            Block.box(0.875, 0.875, 0.125, 0.9375, 0.9375, 0.875),
            Block.box(0.875, 0.0625, 0.125, 0.9375, 0.125, 0.875),
            Block.box(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875),
            Block.box(0.125, 0.125, 0.8125, 0.3125, 0.5, 0.875),
            Block.box(0.125, 0.125, 0.625, 0.3125, 0.5, 0.6875),
            Block.box(0.25, 0.125, 0.6875, 0.3125, 0.5, 0.8125),
            Block.box(0.125, 0.125, 0.6875, 0.1875, 0.5, 0.8125),
            Block.box(0.6875, 0.125, 0.6875, 0.75, 0.5, 0.8125),
            Block.box(0.8125, 0.125, 0.6875, 0.875, 0.5, 0.8125),
            Block.box(0.6875, 0.125, 0.625, 0.875, 0.5, 0.6875),
            Block.box(0.6875, 0.125, 0.8125, 0.875, 0.5, 0.875),
            Block.box(0.75, 0.125, 0.1875, 0.8125, 0.5, 0.5),
            Block.box(0.1875, 0.125, 0.1875, 0.25, 0.5, 0.5),
            Block.box(0.25, 0.1875, 0.25, 0.75, 0.25, 0.4375),
            Block.box(0.25, 0.375, 0.25, 0.75, 0.4375, 0.4375),
            Block.box(0.1875, 0.125, 0.5, 0.25, 0.140625, 0.625),
            Block.box(0.75, 0.125, 0.5, 0.8125, 0.140625, 0.625)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(0.8125, 0, 0.8125, 1, 0.0625, 1),
            Block.box(0.8125, 0.0625, 0.9375, 1, 0.1875, 1),
            Block.box(0.9375, 0.0625, 0.8125, 1, 0.1875, 0.9375),
            Block.box(0.875, 0.0625, 0.875, 0.9375, 0.9375, 0.9375),
            Block.box(0.8125, 0.8125, 0.9375, 1, 0.9375, 1),
            Block.box(0.9375, 0.8125, 0.8125, 1, 0.9375, 0.9375),
            Block.box(0.8125, 0.9375, 0.8125, 1, 1, 1),
            Block.box(0, 0.9375, 0.8125, 0.1875, 1, 1),
            Block.box(0.0625, 0.8125, 0.9375, 0.1875, 0.9375, 1),
            Block.box(0, 0.8125, 0.8125, 0.0625, 0.9375, 1),
            Block.box(0.0625, 0.0625, 0.875, 0.125, 0.9375, 0.9375),
            Block.box(0.0625, 0.0625, 0.9375, 0.1875, 0.1875, 1),
            Block.box(0, 0.0625, 0.8125, 0.0625, 0.1875, 1),
            Block.box(0, 0, 0.8125, 0.1875, 0.0625, 1),
            Block.box(0.8125, 0.9375, 0, 1, 1, 0.1875),
            Block.box(0.8125, 0.8125, 0, 0.9375, 0.9375, 0.0625),
            Block.box(0.9375, 0.8125, 0, 1, 0.9375, 0.1875),
            Block.box(0.875, 0.0625, 0.0625, 0.9375, 0.9375, 0.125),
            Block.box(0.8125, 0.0625, 0, 0.9375, 0.1875, 0.0625),
            Block.box(0.9375, 0.0625, 0, 1, 0.1875, 0.1875),
            Block.box(0.8125, 0, 0, 1, 0.0625, 0.1875),
            Block.box(0, 0.9375, 0, 0.1875, 1, 0.1875),
            Block.box(0, 0.8125, 0.0625, 0.0625, 0.9375, 0.1875),
            Block.box(0, 0.8125, 0, 0.1875, 0.9375, 0.0625),
            Block.box(0.0625, 0.0625, 0.0625, 0.125, 0.9375, 0.125),
            Block.box(0, 0.0625, 0.0625, 0.0625, 0.1875, 0.1875),
            Block.box(0, 0.0625, 0, 0.1875, 0.1875, 0.0625),
            Block.box(0, 0, 0, 0.1875, 0.0625, 0.1875),
            Block.box(0.875, 0.0625, 0.125, 0.9375, 0.125, 0.875),
            Block.box(0.0625, 0.0625, 0.125, 0.125, 0.125, 0.875),
            Block.box(0.0625, 0.875, 0.125, 0.125, 0.9375, 0.875),
            Block.box(0.875, 0.875, 0.125, 0.9375, 0.9375, 0.875),
            Block.box(0.125, 0.0625, 0.875, 0.875, 0.125, 0.9375),
            Block.box(0.125, 0.875, 0.875, 0.875, 0.9375, 0.9375),
            Block.box(0.125, 0.875, 0.0625, 0.875, 0.9375, 0.125),
            Block.box(0.125, 0.0625, 0.0625, 0.875, 0.125, 0.125),
            Block.box(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875),
            Block.box(0.8125, 0.125, 0.6875, 0.875, 0.5, 0.875),
            Block.box(0.625, 0.125, 0.6875, 0.6875, 0.5, 0.875),
            Block.box(0.6875, 0.125, 0.6875, 0.8125, 0.5, 0.75),
            Block.box(0.6875, 0.125, 0.8125, 0.8125, 0.5, 0.875),
            Block.box(0.6875, 0.125, 0.25, 0.8125, 0.5, 0.3125),
            Block.box(0.6875, 0.125, 0.125, 0.8125, 0.5, 0.1875),
            Block.box(0.625, 0.125, 0.125, 0.6875, 0.5, 0.3125),
            Block.box(0.8125, 0.125, 0.125, 0.875, 0.5, 0.3125),
            Block.box(0.1875, 0.125, 0.1875, 0.5, 0.5, 0.25),
            Block.box(0.1875, 0.125, 0.75, 0.5, 0.5, 0.8125),
            Block.box(0.25, 0.1875, 0.25, 0.4375, 0.25, 0.75),
            Block.box(0.25, 0.375, 0.25, 0.4375, 0.4375, 0.75),
            Block.box(0.5, 0.125, 0.75, 0.625, 0.140625, 0.8125),
            Block.box(0.5, 0.125, 0.1875, 0.625, 0.140625, 0.25)
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
            if (blockEntity instanceof MetalFormerBlockEntity) {
                ((MetalFormerBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof MetalFormerBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (MetalFormerBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MetalFormerBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.METAL_FORMER.get(), MetalFormerBlockEntity::tick);
    }
}
