package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.entity.custom.ai.FishBreedGoal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.ai.FollowIndiscriminateSchoolLeaderGoal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.BreedableWaterAnimal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.SchoolingFish;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class DaphniaEntity extends SchoolingFish implements GeoEntity {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.DRIED_KELP);

    protected static final RawAnimation SWIM = RawAnimation.begin().thenLoop("animation.daphnia.swim");
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.daphnia.idle");
    protected static final RawAnimation FLOP = RawAnimation.begin().thenLoop("animation.daphnia.flop");
    protected static final RawAnimation JUMP = RawAnimation.begin().thenLoop("animation.daphnia.jump");

    public DaphniaEntity(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FishBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowIndiscriminateSchoolLeaderGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));

        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WaterAnimal.class, 8.0F, 1.6D, 1.4D, (entity) -> {
            return entity.getType().is(YAFMTags.EntityType.PREDATOR_FISH);}));

        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 80));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(YAFMItems.DAPHNIA_BUCKET.get());
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());

        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putBoolean("CanGrow", this.getCanGrowUp());

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
        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("Age", 3)) {
            if (pDataTag.contains("Age")) {
                this.setAge(pDataTag.getInt("Age"));}
            this.setCanGrowUp(pDataTag.getBoolean("CanGrow"));
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
    }

    protected <E extends DaphniaEntity> PlayState Controller(AnimationState<DaphniaEntity> event) {
        DaphniaEntity entity = event.getAnimatable();
        if (entity.isInWater()){
            if (event.isMoving()){
                event.setAndContinue(SWIM);

                if (entity.isBaby()){
                    event.getController().setAnimationSpeed(2d);}

            } else {
                event.setAndContinue(IDLE);
            }
        }else{
            event.setAndContinue(FLOP);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        DaphniaEntity baby = YAFMEntities.DAPHNIA.get().create(pLevel);
        if (baby != null){
            baby.setFromBucket(true);
        }
        return baby;
    }

    @Override
    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D && this.distanceToSqr(this.leader) > 1.5D;
    }
}
