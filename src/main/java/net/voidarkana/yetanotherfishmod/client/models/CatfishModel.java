package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.CatfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CatfishModel extends GeoModel<CatfishEntity> {
    @Override
    public ResourceLocation getModelResource(CatfishEntity CatfishEntity) {
        return switch (CatfishEntity.getVariant()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/catfish_generic.geo.json");
            case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/catfish_big.geo.json");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/catfish_pangasius.geo.json");
            default ->new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/catfish_piraiba.geo.json");
        };
    }

    @Override
    public ResourceLocation getTextureResource(CatfishEntity catfish) {
        return switch (catfish.getVariant()){
            case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/catfish/catfish_blue.png");
            case 2-> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/catfish/catfish_big.png");
            case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/catfish/catfish_pangasius.png");
            default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "textures/entity/catfish/catfish_piraiba.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(CatfishEntity CatfishEntity) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/catfish.animation.json");
    }

    @Override
    public void setCustomAnimations(CatfishEntity animatable, long instanceId, AnimationState<CatfishEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))/2));
    }
}
