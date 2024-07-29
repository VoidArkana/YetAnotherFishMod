package net.voidarkana.yetanotherfishmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.models.BarbfishModel;
import net.voidarkana.yetanotherfishmod.common.entity.custom.BarbfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BarbfishRenderer extends GeoEntityRenderer<BarbfishEntity> {

    public BarbfishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BarbfishModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BarbfishEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barbslim"+barbfishEntity.getVariantSkin()+".png");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barbsmall"+barbfishEntity.getVariantSkin()+".png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barb"+barbfishEntity.getVariantSkin()+".png");
        };
    }

    @Override
    public void render(BarbfishEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
