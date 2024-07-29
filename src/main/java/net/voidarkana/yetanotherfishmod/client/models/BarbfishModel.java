package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.BarbfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BarbfishModel extends GeoModel<BarbfishEntity> {
    @Override
    public ResourceLocation getModelResource(BarbfishEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barbslim.geo.json");
            case 2 ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barbsmall.geo.json");
            default ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barb.geo.json");
        };
    }

    @Override
    public ResourceLocation getTextureResource(BarbfishEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barbslim"+barbfishEntity.getVariantSkin()+".png");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barbsmall"+barbfishEntity.getVariantSkin()+".png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/barb/barb"+barbfishEntity.getVariantSkin()+".png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(BarbfishEntity barbfishEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/genericfish.animation.json");
    }

    @Override
    public void setCustomAnimations(BarbfishEntity animatable, long instanceId, AnimationState<BarbfishEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }
}
