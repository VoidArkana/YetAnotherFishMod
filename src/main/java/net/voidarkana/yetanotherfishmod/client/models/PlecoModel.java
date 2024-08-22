package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.PlecoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class PlecoModel extends GeoModel<PlecoEntity> {
    @Override
    public ResourceLocation getModelResource(PlecoEntity PlecoEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/pleco.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlecoEntity catfish) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/pleco.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PlecoEntity PlecoEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/pleco.animation.json");
    }

    @Override
    public void setCustomAnimations(PlecoEntity animatable, long instanceId, AnimationState<PlecoEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

//        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");
//
//        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
//
//        if (!animatable.onGround() && !animatable.getWantsToSwim()){
//            swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))/2));
//        }
    }

}
