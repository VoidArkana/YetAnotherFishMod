package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.yetanotherfishmod.client.renderers.FreshwaterSharkRenderer;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.entity.custom.ai.FishJumpGoal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.BreedableWaterAnimal;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.VariantSchoolingFish;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;
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

    private static final Ingredient FOOD_ITEMS = Ingredient.of(YAFMTags.Items.FISH_FEED);

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
            case 1, 4 ->super.getDimensions(pPose).scale(1.5F, 1.5F);
            case 2 ->super.getDimensions(pPose).scale(1.7F, 1.7F);
            case 3, 5 ->super.getDimensions(pPose).scale(1F, 1F);
            default ->super.getDimensions(pPose);
        };
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 1F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FishJumpGoal(this, 15));
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("VariantModel", this.getVariantModel());
        compoundnbt.putInt("VariantSkin", this.getVariantSkin());
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

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("VariantModel", 3)) {
            this.setVariantModel(pDataTag.getInt("VariantModel"));
            this.setVariantSkin(pDataTag.getInt("VariantSkin"));
            if (pDataTag.contains("Age")) {
                this.setAge(pDataTag.getInt("Age"));
            }
            this.setCanGrowUp(pDataTag.getBoolean("CanGrow"));
        }else{

            int model = this.random.nextInt(4); //

            this.setVariantModel(model);

            int skin;

            if (model==1){
                skin = this.random.nextInt(3);
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
        FreshwaterSharkEntity baby = YAFMEntities.FRESHWATER_SHARK.get().create(pLevel);
        if (baby != null){
            baby.setVariantModel(this.getVariantModel());
            if (this.getVariantModel()==1){
                FreshwaterSharkEntity otherParent = (FreshwaterSharkEntity) pOtherParent;
                int lowerQuality = Math.min(this.getFeedQuality(), otherParent.getFeedQuality());

                switch (lowerQuality){
                    case 1:
                        baby.setVariantSkin(this.random.nextBoolean() ? this.random.nextInt(3)
                                : this.random.nextBoolean() ? this.getVariantSkin() : otherParent.getVariantSkin());
                        break;
                    case 2, 3:
                        baby.setVariantSkin(this.random.nextBoolean() ? this.getVariantSkin() : otherParent.getVariantSkin());
                        break;
                    default:
                        baby.setVariantSkin(this.random.nextInt(3));
                        break;
                }
            }
            baby.setFromBucket(true);
        }
        return baby;
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

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (itemstack.is(YAFMItems.REGULAR_FEED.get())){
            this.setFeedQuality(0);
        }
        if (itemstack.is(YAFMItems.QUALITY_FEED.get())){
            this.setFeedQuality(1);
        }
        if (itemstack.is(YAFMItems.GREAT_FEED.get())){
            this.setFeedQuality(2);
        }
        if (itemstack.is(YAFMItems.PREMIUM_FEED.get())){
            this.setFeedQuality(3);
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        FreshwaterSharkEntity mate = (FreshwaterSharkEntity) pOtherAnimal;
        return super.canMate(pOtherAnimal) && this.getVariantModel() == mate.getVariantModel();
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel pLevel, Animal pMate) {
        AgeableMob ageablemob = this.getBreedOffspring(pLevel, pMate);
        AgeableMob ageableMob2 = null;
        AgeableMob ageableMob3 = null;
        AgeableMob ageableMob4 = null;
        AgeableMob ageableMob5 = null;

        /** DON'T FORGET!!!!*/
        FreshwaterSharkEntity otherParent = (FreshwaterSharkEntity) pMate;
        int lowerQuality = Math.min(this.getFeedQuality(), otherParent.getFeedQuality());

        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, pMate, ageablemob);
        ageablemob = event.getChild();

        if ((lowerQuality > 0 && this.random.nextBoolean()) || lowerQuality > 2){
            ageableMob2 = this.getBreedOffspring(pLevel, pMate);
            final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event2 = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, pMate, ageableMob2);
            ageableMob2 = event2.getChild();

            if ((lowerQuality > 1 && this.random.nextInt(4)==0) || (lowerQuality > 2 && this.random.nextBoolean())){
                ageableMob3 = this.getBreedOffspring(pLevel, pMate);
                final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event3 = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, pMate, ageableMob3);
                ageableMob3 = event3.getChild();

                if (lowerQuality > 2 && this.random.nextBoolean()){

                    ageableMob4 = this.getBreedOffspring(pLevel, pMate);
                    final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event4 = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, pMate, ageableMob4);
                    ageableMob4 = event4.getChild();

                    if (this.random.nextBoolean()){
                        ageableMob5 = this.getBreedOffspring(pLevel, pMate);
                        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event5 = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, pMate, ageableMob5);
                        ageableMob5 = event5.getChild();
                    }
                }
            }
        }

        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (cancelled) {
            //Reset the "inLove" state for the animals
            this.setAge(6000);
            pMate.setAge(6000);
            this.resetLove();
            pMate.resetLove();
            return;
        }
        if (ageablemob != null) {

            ageablemob.setBaby(true);
            ageablemob.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageablemob);
            pLevel.addFreshEntityWithPassengers(ageablemob);

            if (ageableMob2 != null){

                ageableMob2.setBaby(true);
                ageableMob2.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob2);
                pLevel.addFreshEntityWithPassengers(ageableMob2);

                if (ageableMob3 != null){

                    ageableMob3.setBaby(true);
                    ageableMob3.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                    this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob3);
                    pLevel.addFreshEntityWithPassengers(ageableMob3);

                    if (ageableMob4 != null){

                        ageableMob4.setBaby(true);
                        ageableMob4.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                        this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob4);
                        pLevel.addFreshEntityWithPassengers(ageableMob4);

                        if (ageableMob5 != null){

                            ageableMob5.setBaby(true);
                            ageableMob5.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                            this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob5);
                            pLevel.addFreshEntityWithPassengers(ageableMob5);
                        }
                    }
                }
            }
        }
    }
}
