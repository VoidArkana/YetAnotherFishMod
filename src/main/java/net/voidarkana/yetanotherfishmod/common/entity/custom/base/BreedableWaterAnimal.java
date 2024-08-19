package net.voidarkana.yetanotherfishmod.common.entity.custom.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.yetanotherfishmod.common.entity.custom.GuppyEntity;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;

public abstract class BreedableWaterAnimal extends Animal {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(YAFMTags.Items.FISH_FEED);

    public float prevTilt;
    public float tilt;

    private static final EntityDataAccessor<Integer> FEED_TYPE = SynchedEntityData.defineId(BreedableWaterAnimal.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CAN_GROW_UP = SynchedEntityData.defineId(BreedableWaterAnimal.class, EntityDataSerializers.BOOLEAN);

    protected BreedableWaterAnimal(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FEED_TYPE, 0);
        this.entityData.define(CAN_GROW_UP, true);
    }

    public int getFeedQuality() {
        return this.entityData.get(FEED_TYPE);
    }

    public void setFeedQuality(int variant) {
        this.entityData.set(FEED_TYPE, variant);
    }


    public Boolean getCanGrowUp() {
        return this.entityData.get(CAN_GROW_UP);
    }

    public void setCanGrowUp(boolean variant) {
        this.entityData.set(CAN_GROW_UP, variant);
    }


    public boolean canBreatheUnderwater() {
        return true;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getAmbientSoundInterval() {
        return 120;
    }

    public int getExperienceReward() {
        return 1 + this.level().random.nextInt(3);
    }

    protected void handleAirSupply(int pAirSupply) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(pAirSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }

    }

    /**
     * Gets called every tick from main Entity class
     */
    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public static boolean checkSurfaceWaterAnimalSpawnRules(EntityType<? extends WaterAnimal> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }

//    @Override
//    public void tick() {
//        super.tick();
//
//        if (this.isInWater()) {
//            float added = (float) position().y() * (float) getDeltaMovement().y();
//            float xTilt = Mth.clamp(added, -25.0F, 20.0F);
//
//            setXRot(-Mth.lerp(getXRot(), xTilt, xTilt));
//        }
//
//    }

    @Override
    public void aiStep() {
        super.aiStep();
        prevTilt = tilt;
        if (this.isInWater()) {
            final float v = Mth.degreesDifference(this.getYRot(), yRotO);
            if (Math.abs(v) > 1) {
                if (Math.abs(tilt) < 25) {
                    tilt -= Math.signum(v);
                }
            } else {
                if (Math.abs(tilt) > 0) {
                    final float tiltSign = Math.signum(tilt);
                    tilt -= tiltSign * 0.85F;
                    if (tilt * tiltSign < 0) {
                        tilt = 0;
                    }
                }
            }
        } else {
            tilt = 0;
        }

        if (this.isAlive() && !this.getCanGrowUp()) {
            if (this.getAge() >- 500){
                int i = this.getAge();
                this.setAge(i-6000);
            }
        }
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        int i = this.getAge();

        if (this.isBaby() && isFood(itemstack)){


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

            if (this.getCanGrowUp()){
                this.ageUp(getSpeedUpSecondsWhenFeedingFish(-i, this.getFeedQuality()), true);
                return InteractionResult.SUCCESS;
            }else{
                if (itemstack.is(YAFMItems.PREMIUM_FEED.get())){
                    this.setCanGrowUp(true);

                    for(int j = 0; j < 7; ++j) {
                        double d0 = this.random.nextGaussian() * 0.02D;
                        double d1 = this.random.nextGaussian() * 0.02D;
                        double d2 = this.random.nextGaussian() * 0.02D;
                        this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
                    }

                }else {
                    return InteractionResult.PASS;
                }
            }
        }

        if (this.isBaby() && itemstack.is(YAFMItems.BAD_FEED.get()) && this.getCanGrowUp()){
            this.setCanGrowUp(false);

            this.setAge(-12000);

            for(int j = 0; j < 7; ++j) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }

            return InteractionResult.SUCCESS;
        }

        return super.interactAt(pPlayer, pVec, pHand);
    }

    public static int getSpeedUpSecondsWhenFeedingFish(int pTicksUntilAdult, int multiplier) {
        return (int)((float)(pTicksUntilAdult / 20) * 0.1F * (multiplier+1));
    }


    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

}
