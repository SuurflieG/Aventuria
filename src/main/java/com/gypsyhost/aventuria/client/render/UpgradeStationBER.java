package com.gypsyhost.aventuria.client.render;

import com.gypsyhost.aventuria.custom.block.entity.UpgradeStationBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import com.mojang.math.Vector3f;
import net.minecraftforge.items.CapabilityItemHandler;

public class UpgradeStationBER implements BlockEntityRenderer<UpgradeStationBlockEntity> {

    public UpgradeStationBER(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(UpgradeStationBlockEntity tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLights, int combinedOverlay) {
        ItemStack stack = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map(e -> e.getStackInSlot(0)).orElse(ItemStack.EMPTY);
        if (stack.isEmpty()) {
            return;
        }

        Direction facing = tile.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrix.pushPose();
        matrix.translate(0, .78f, 0);

        if (facing == Direction.SOUTH) {
            matrix.translate(.50f, 0, .68f);
            matrix.mulPose(Vector3f.YP.rotationDegrees(110));
        } else if (facing == Direction.EAST) {
            matrix.translate(.68f, 0, .50f);
            matrix.mulPose(Vector3f.YP.rotationDegrees(200));
        } else if (facing == Direction.NORTH) {
            matrix.translate(.50f, 0, .32f);
            matrix.mulPose(Vector3f.YP.rotationDegrees(290));
        } else if (facing == Direction.WEST) {
            matrix.translate(.32f, 0, .50f);
            matrix.mulPose(Vector3f.YP.rotationDegrees(20));
        }
        matrix.mulPose(Vector3f.ZN.rotationDegrees(90));

        matrix.scale(1f, 1f, 1f);

        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(stack, Minecraft.getInstance().level, null, 0);
        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND, false, matrix,buffer, combinedLights, combinedOverlay, model);
        matrix.popPose();
    }
}
