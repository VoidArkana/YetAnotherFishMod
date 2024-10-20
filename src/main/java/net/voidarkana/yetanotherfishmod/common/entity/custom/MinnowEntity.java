package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.Tags;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.BreedableWaterAnimal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.VariantSchoolingFish;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.BucketableFishEntity;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;


public class MinnowEntity extends VariantSchoolingFish implements GeoEntity {

    protected static final RawAnimation SWIM = RawAnimation.begin().thenLoop("animation.genericfish.swim");
    protected static final RawAnimation FLOP = RawAnimation.begin().thenLoop("animation.genericfish.flop");

    public MinnowEntity(EntityType<? extends BucketableFishEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariantModel(compound.getInt("VariantModel"));
        this.setVariantSkin(compound.getInt("VariantSkin"));
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("VariantModel", this.getVariantModel());
        compoundnbt.putInt("VariantSkin", this.getVariantSkin());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("VariantModel", 3)) {
            this.setVariantModel(pDataTag.getInt("VariantModel"));
            this.setVariantSkin(pDataTag.getInt("VariantSkin"));
        }else{

            int skin = this.random.nextInt(4);

            if(pReason == MobSpawnType.SPAWN_EGG || (pReason == MobSpawnType.BUCKET && pDataTag == null)){
                this.setVariantModel(this.random.nextInt(4));
            }else {
                int model;

                if (pSpawnData instanceof MinnowEntity.FishGroupData){

                    MinnowEntity.FishGroupData fish$fishgroupdata = (MinnowEntity.FishGroupData)pSpawnData;
                    model = fish$fishgroupdata.variantModel;
                    skin = fish$fishgroupdata.variantSkin;

                }else {
                    if (pLevel.getBiome(this.blockPosition()).is(Tags.Biomes.IS_SWAMP)){
                        model = 3;
                    }else if (pLevel.getBiome(this.blockPosition()).is(BiomeTags.IS_JUNGLE)){
                        model = this.random.nextBoolean() ? 1 : 0;
                    }else if (pLevel.getBiome(this.blockPosition()).is(BiomeTags.IS_RIVER)){
                        model = 2;
                    }else {
                        model = this.random.nextInt(4);
                    }

                    pSpawnData = new MinnowEntity.FishGroupData(this, model, skin);
                }

                this.setVariantModel(model);
            }

            this.setVariantSkin(skin);
        }

        return pSpawnData;
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
    }

    protected <E extends MinnowEntity> PlayState Controller(AnimationState<E> event) {
        if (this.isInWater()){
            event.setAndContinue(SWIM);
        }else{
            event.setAndContinue(FLOP);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(YAFMItems.BARB_BUCKET.get());
    }


    static class FishGroupData extends VariantSchoolingFish.SchoolSpawnGroupData {
        final int variantModel;
        final int variantSkin;

        FishGroupData(MinnowEntity pLeader, int pVariantModel, int pVariantSkin) {
            super(pLeader);
            this.variantModel = pVariantModel;
            this.variantSkin = pVariantSkin;
        }
    }

}
