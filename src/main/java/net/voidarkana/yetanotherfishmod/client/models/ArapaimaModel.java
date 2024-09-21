package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.ArapaimaEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.CatfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ArapaimaModel extends GeoModel<ArapaimaEntity> {

    @Override
    public ResourceLocation getModelResource(ArapaimaEntity animatable) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/arapaima.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArapaimaEntity animatable) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/arapaima.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArapaimaEntity animatable) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/arapaima.animation.json");
    }

    @Override
    public void setCustomAnimations(ArapaimaEntity animatable, long instanceId, AnimationState<ArapaimaEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        CoreGeoBone head = this.getAnimationProcessor().getBone("head_rot");
        CoreGeoBone tailRot = this.getAnimationProcessor().getBone("tail_rot");
        CoreGeoBone tailTipRot = this.getAnimationProcessor().getBone("tail_tip_rot");
        CoreGeoBone bodyRot = this.getAnimationProcessor().getBone("body_rot");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))/2));
        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))/8));
        bodyRot.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))/8));
        tailRot.setRotX(-((entityData.headPitch() * ((float) Math.PI / 180F))/4));
        tailTipRot.setRotX(-((entityData.headPitch() * ((float) Math.PI / 180F))/4));

        head.setRotY(-(animatable.tilt * ((float) Math.PI / 180F)));
        bodyRot.setRotY(-(animatable.tilt * ((float) Math.PI / 180F))/2);
        tailRot.setRotY(animatable.tilt * ((float) Math.PI / 180F));
        tailTipRot.setRotY(animatable.tilt * ((float) Math.PI / 180F));

    }
}
