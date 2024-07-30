package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.MinnowEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MinnowModel extends GeoModel<MinnowEntity> {
    @Override
    public ResourceLocation getModelResource(MinnowEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barbslim.geo.json");
            case 2 ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barbsmall.geo.json");
            case 3 ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/rasbora.geo.json");
            default ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/barb.geo.json");
        };
    }

    @Override
    public ResourceLocation getTextureResource(MinnowEntity barbfishEntity) {
        return switch (barbfishEntity.getVariantModel()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barbslim"+barbfishEntity.getVariantSkin()+".png");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barbsmall"+barbfishEntity.getVariantSkin()+".png");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/rasbora"+barbfishEntity.getVariantSkin()+".png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/minnow/barb"+barbfishEntity.getVariantSkin()+".png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(MinnowEntity barbfishEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/genericfish.animation.json");
    }

    @Override
    public void setCustomAnimations(MinnowEntity animatable, long instanceId, AnimationState<MinnowEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }
}
