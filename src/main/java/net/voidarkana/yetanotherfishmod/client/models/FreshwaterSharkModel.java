package net.voidarkana.yetanotherfishmod.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.FreshwaterSharkEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.GuppyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FreshwaterSharkModel extends GeoModel<FreshwaterSharkEntity> {
    @Override
    public ResourceLocation getModelResource(FreshwaterSharkEntity fish) {
            return switch (fish.getVariantModel()){
                case 1 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_bala_adult.geo.json");
                case 2 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_highfin_adult.geo.json");
                case 3 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_highfin_juvenile.geo.json");
                case 4 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_rainbow_adult.geo.json");
                case 5 -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_rainbow_juvenile.geo.json");
                default -> new ResourceLocation(YetAnotherFishMod.MOD_ID, "geo/shark_bala_juvenile.geo.json");
        };
    }

    @Override
    public ResourceLocation getTextureResource(FreshwaterSharkEntity fish) {
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
    public ResourceLocation getAnimationResource(FreshwaterSharkEntity fish) {
        return new ResourceLocation(YetAnotherFishMod.MOD_ID, "animations/genericfish.animation.json");
    }

    @Override
    public void setCustomAnimations(FreshwaterSharkEntity fish, long instanceId, AnimationState<FreshwaterSharkEntity> animationState) {
        super.setCustomAnimations(fish, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }
}
