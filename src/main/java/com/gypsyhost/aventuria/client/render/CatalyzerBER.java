package com.gypsyhost.aventuria.client.render;

import com.gypsyhost.aventuria.custom.block.entity.CatalyzerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.items.CapabilityItemHandler;

public class CatalyzerBER implements BlockEntityRenderer<CatalyzerBlockEntity> {

    private final int FUEL_SLOT = 0;
    private final int INPUT_SLOT_LEFT = 1;
    private final int INPUT_SLOT_TOP = 2;
    private final int INPUT_SLOT_RIGHT = 3;
    private final int INPUT_SLOT_BOTTOM = 4;
    private final int CATALYZER = 5;
    private final int RESULT_SLOT = 6;
    private final float gemsSize = .1f;

    public CatalyzerBER(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CatalyzerBlockEntity tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        slotFuel(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotInput_1(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotInput_2(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotInput_3(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotInput_4(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotCatalyst(tile, matrix, buffer, combinedLights, combinedOverlay);
        slotResult(tile, matrix, buffer, combinedLights, combinedOverlay);
    }

    private void yLevelOfItem(PoseStack matrix) {
        matrix.translate(0, .63f, 0);
    }

    private void scaleOfItem(PoseStack matrix, float pX) {
        matrix.scale(pX, pX, pX);
    }

    private void slotFuel(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack fuel = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(FUEL_SLOT)).orElse(ItemStack.EMPTY);

        if (fuel.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.2f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.8f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.8f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.2f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, .12f);

        BakedModel model_fuel = Minecraft.getInstance().getItemRenderer().getModel(fuel, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(fuel, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_fuel);

        matrix.popPose();
    }



    private void slotInput_1(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack input_1 = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(INPUT_SLOT_LEFT)).orElse(ItemStack.EMPTY);

        if (input_1.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.2f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.5f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.8f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.5f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, gemsSize);

        BakedModel model_input_1 = Minecraft.getInstance().getItemRenderer().getModel(input_1, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(input_1, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_input_1);

        matrix.popPose();
    }



    private void slotInput_2(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack input_2 = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(INPUT_SLOT_TOP)).orElse(ItemStack.EMPTY);

        if (input_2.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.5f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.2f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.5f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.8f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, gemsSize);

        BakedModel model_input_2 = Minecraft.getInstance().getItemRenderer().getModel(input_2, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(input_2, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_input_2);

        matrix.popPose();
    }

    private void slotInput_3(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack input_3 = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(INPUT_SLOT_RIGHT)).orElse(ItemStack.EMPTY);

        if (input_3.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.8f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.5f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.2f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.5f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, gemsSize);

        BakedModel model_input_3 = Minecraft.getInstance().getItemRenderer().getModel(input_3, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(input_3, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_input_3);

        matrix.popPose();
    }

    private void slotInput_4(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack input_4 = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(INPUT_SLOT_BOTTOM)).orElse(ItemStack.EMPTY);

        if (input_4.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.5f, 0, .8f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.8f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.5f, 0, .2f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.2f, 0, .5f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, gemsSize);

        BakedModel model_input_4 = Minecraft.getInstance().getItemRenderer().getModel(input_4, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(input_4, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_input_4);

        matrix.popPose();
    }

    private void slotCatalyst(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack catalyst = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(CATALYZER)).orElse(ItemStack.EMPTY);

        if (catalyst.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
                matrix.translate(.5f, 0, .5f);
                matrix.mulPose(Vector3f.XP.rotationDegrees(90));
                matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
                matrix.translate(.5f, 0, .5f);
                matrix.mulPose(Vector3f.XP.rotationDegrees(90));
                matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
                matrix.translate(.5f, 0, .5f);
                matrix.mulPose(Vector3f.XP.rotationDegrees(90));
                matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
                matrix.translate(.5f, 0, .5f);
                matrix.mulPose(Vector3f.XP.rotationDegrees(90));
                matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, .2f);

        BakedModel model_catalyst = Minecraft.getInstance().getItemRenderer().getModel(catalyst, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(catalyst, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_catalyst);

        matrix.popPose();
    }

    private void slotResult(CatalyzerBlockEntity tile, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack result = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(RESULT_SLOT)).orElse(ItemStack.EMPTY);

        if (result.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        yLevelOfItem(matrix);

        if (facing == Direction.SOUTH) {
            matrix.translate(.85f, 0, .15f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(270));
        } else if (facing == Direction.EAST) {
            matrix.translate(.15f, 0, .15f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(180));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.15f, 0, .85f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZP.rotationDegrees(90));
        } else if (facing == Direction.WEST) {
            matrix.translate(.85f, 0, .85f);
            matrix.mulPose(Vector3f.XP.rotationDegrees(90));
            matrix.mulPose(Vector3f.ZN.rotationDegrees(0));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        scaleOfItem(matrix, .18f);

        BakedModel model_result = Minecraft.getInstance().getItemRenderer().getModel(result, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(result, ItemTransforms.TransformType.FIXED, false, matrix, buffer, combinedLights, combinedOverlay, model_result);

        matrix.popPose();
    }
}
