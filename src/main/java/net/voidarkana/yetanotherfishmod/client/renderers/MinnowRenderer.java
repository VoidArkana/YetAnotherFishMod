package net.voidarkana.yetanotherfishmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.models.MinnowModel;
import net.voidarkana.yetanotherfishmod.common.entity.custom.CatfishEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.MinnowEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MinnowRenderer extends GeoEntityRenderer<MinnowEntity> {

    public MinnowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MinnowModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MinnowEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barbslim"+barbfishEntity.getVariantSkin()+".png");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barbsmall"+barbfishEntity.getVariantSkin()+".png");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/rasbora"+barbfishEntity.getVariantSkin()+".png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barb"+barbfishEntity.getVariantSkin()+".png");
        };
    }

    @Override
    public void render(MinnowEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }

    @Override
    protected void applyRotations(MinnowEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        if (animatable.isInWater()){
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
        }    }
}
