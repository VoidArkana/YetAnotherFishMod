package net.voidarkana.yetanotherfishmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.models.FeatherbackModel;
import net.voidarkana.yetanotherfishmod.common.entity.custom.FeatherbackEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FeatherbackRenderer extends GeoEntityRenderer<FeatherbackEntity> {
    public FeatherbackRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FeatherbackModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FeatherbackEntity featherbackEntity) {
        return switch (featherbackEntity.getVariant()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/featherback/featherback_1.png");
            case 2-> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/featherback/featherback_2.png");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/featherback/featherback_3.png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/featherback/featherback_0.png");
        };
    }

    @Override
    public void render(FeatherbackEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        if(entity.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        }
        else {
            poseStack.scale(1.0F, 1.0F, 1.0F);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
