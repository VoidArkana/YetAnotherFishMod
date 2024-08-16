package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.yetanotherfishmod.common.entity.custom.ai.FishJumpGoal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.BreedableWaterAnimal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.VariantSchoolingFish;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class FreshwaterSharkEntity extends VariantSchoolingFish implements GeoEntity {

    protected static final RawAnimation SWIM = RawAnimation.begin().thenLoop("animation.genericfish.swim");
    protected static final RawAnimation FLOP = RawAnimation.begin().thenLoop("animation.genericfish.flop");

    protected static final RawAnimation HEAD_SWIM = RawAnimation.begin().thenLoop("animation.genericfish.headswim");
    protected static final RawAnimation HEAD_FLOP = RawAnimation.begin().thenLoop("animation.genericfish.headflop");

    public FreshwaterSharkEntity(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.refreshDimensions();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return switch (this.getVariantModel()){
            case 1 ->super.getDimensions(pPose).scale(1.5F, 1.5F);
            case 2 ->super.getDimensions(pPose).scale(1.7F, 1.7F);
            case 3 ->super.getDimensions(pPose).scale(1F, 1F);
            case 4 ->super.getDimensions(pPose).scale(1.5F, 1.5F);
            case 5 ->super.getDimensions(pPose).scale(1F, 1F);
            default ->super.getDimensions(pPose);
        };
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FishJumpGoal(this, 15));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 1F);
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

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("VariantModel", 3)) {
            this.setVariantModel(pDataTag.getInt("VariantModel"));
            this.setVariantSkin(pDataTag.getInt("VariantSkin"));
        }else{

            int model = this.random.nextInt(6);
            this.setVariantModel(model);

            int skin;
            if (model==5){
                skin = this.random.nextInt(2);
            }else{
                skin = 0;
            }

            this.setVariantSkin(skin);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }


    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(YAFMItems.FRESHWATER_SHARK_BUCKET.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
    }

    protected <E extends FreshwaterSharkEntity> PlayState Controller(AnimationState<E> event) {
        if (this.isInWater()){
            event.setAndContinue(this.getVariantModel() == 0 ? SWIM : HEAD_SWIM);
            event.getController().setAnimationSpeed(1.5);
        }else{
            event.setAndContinue(this.getVariantModel() == 0 ? FLOP : HEAD_FLOP);
        }
        return PlayState.CONTINUE;
    }
}
