package net.voidarkana.yetanotherfishmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.models.FreshwaterSharkModel;
import net.voidarkana.yetanotherfishmod.client.models.MinnowModel;
import net.voidarkana.yetanotherfishmod.common.entity.custom.FreshwaterSharkEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FreshwaterSharkRenderer extends GeoEntityRenderer<FreshwaterSharkEntity> {

    public FreshwaterSharkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FreshwaterSharkModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FreshwaterSharkEntity fish) {
        return switch (fish.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_bala_adult.png");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_highfin_adult.png");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_highfin_juvenile.png");
            case 4 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_rainbow_adult.png");
            case 5 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_rainbow_juvenile_"+fish.getVariantSkin()+".png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/freshwater_shark/shark_bala_juvenile.png");
        };
    }

    @Override
    public void render(FreshwaterSharkEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}