package net.voidarkana.fintastic.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.voidarkana.fintastic.client.models.ArapaimaModel;
import net.voidarkana.fintastic.common.entity.custom.ArapaimaEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ArapaimaRenderer extends GeoEntityRenderer<ArapaimaEntity> {

    public ArapaimaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ArapaimaModel());
    }

    @Override
    protected void applyRotations(ArapaimaEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        if (animatable.isInWater()){
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
        }    }

//    @Override
//    public boolean shouldRender(ArapaimaEntity livingEntityIn, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
//        for (ArapaimaPart part : livingEntityIn.allParts) {
//            if (pCamera.isVisible(part.getBoundingBox())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
