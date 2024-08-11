package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.SchoolingFish;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class GuppyEntity extends SchoolingFish implements GeoEntity {

    protected static final RawAnimation SWIM = RawAnimation.begin().thenLoop("animation.guppy.swim");
    protected static final RawAnimation FLOP = RawAnimation.begin().thenLoop("animation.guppy.flop");

    //base (skin variant)
    //fin model
    //fin color
    //tail model
    //tail color
    //pattern 1
    //pattern 2

    private static final EntityDataAccessor<Integer> FIN_MODEL = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FIN_COLOR = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TAIL_MODEL = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TAIL_COLOR = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_1 = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_2 = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_1_COLOR = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_2_COLOR = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_PATTERN_1 = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_PATTERN_2 = SynchedEntityData.defineId(GuppyEntity.class, EntityDataSerializers.BOOLEAN);

    public GuppyEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FIN_MODEL, 0);
        this.entityData.define(FIN_COLOR, 0);
        this.entityData.define(TAIL_MODEL, 0);
        this.entityData.define(TAIL_COLOR, 0);
        this.entityData.define(PATTERN_1, 0);
        this.entityData.define(PATTERN_2, 0);
        this.entityData.define(PATTERN_1_COLOR, 0);
        this.entityData.define(PATTERN_2_COLOR, 0);
        this.entityData.define(HAS_PATTERN_1, false);
        this.entityData.define(HAS_PATTERN_2, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("FinModel", this.getFinModel());
        compound.putInt("FinColor", this.getFinColor());
        compound.putInt("TailModel", this.getTailModel());
        compound.putInt("TailColor", this.getTailColor());

        compound.putBoolean("HasMainPattern", this.getHasMainPattern());
        compound.putInt("MainPattern", this.getMainPattern());
        compound.putInt("MainPatternColor", this.getMainPatternColor());

        compound.putBoolean("HasSecondaryPattern", this.getHasSecondPattern());
        compound.putInt("SecondaryPattern", this.getSecondPattern());
        compound.putInt("SecondaryPatternColor", this.getSecondPatternColor());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFinModel(compound.getInt("FinModel"));
        this.setFinColor(compound.getInt("FinColor"));
        this.setTailModel(compound.getInt("TailModel"));
        this.setTailColor(compound.getInt("TailColor"));


        this.setHasMainPattern(compound.getBoolean("HasMainPattern"));
        this.setMainPattern(compound.getInt("MainPattern"));
        this.setMainPatternColor(compound.getInt("MainPatternColor"));

        this.setHasSecondPattern(compound.getBoolean("HasSecondaryPattern"));
        this.setSecondPattern(compound.getInt("SecondaryPattern"));
        this.setSecondPatternColor(compound.getInt("SecondaryPatternColor"));
    }

    public int getFinModel() {
        return this.entityData.get(FIN_MODEL);
    }

    public void setFinModel(int variant) {
        this.entityData.set(FIN_MODEL, variant);
    }


    public int getFinColor() {
        return this.entityData.get(FIN_COLOR);
    }

    public void setFinColor(int variant) {
        this.entityData.set(FIN_COLOR, variant);
    }


    public int getTailModel() {
        return this.entityData.get(TAIL_MODEL);
    }

    public void setTailModel(int variant) {
        this.entityData.set(TAIL_MODEL, variant);
    }


    public int getTailColor() {
        return this.entityData.get(TAIL_COLOR);
    }

    public void setTailColor(int variant) {
        this.entityData.set(TAIL_COLOR, variant);
    }


    public Boolean getHasMainPattern() {
        return this.entityData.get(HAS_PATTERN_1);
    }

    public void setHasMainPattern(Boolean variant) {
        this.entityData.set(HAS_PATTERN_1, variant);
    }

    public int getMainPattern() {
        return this.entityData.get(PATTERN_1);
    }

    public void setMainPattern(int variant) {
        this.entityData.set(PATTERN_1, variant);
    }

    public int getMainPatternColor() {
        return this.entityData.get(PATTERN_1_COLOR);
    }

    public void setMainPatternColor(int variant) {
        this.entityData.set(PATTERN_1_COLOR, variant);
    }


    public Boolean getHasSecondPattern() {
        return this.entityData.get(HAS_PATTERN_2);
    }

    public void setHasSecondPattern(Boolean variant) {
        this.entityData.set(HAS_PATTERN_2, variant);
    }

    public int getSecondPattern() {
        return this.entityData.get(PATTERN_2);
    }

    public void setSecondPattern(int variant) {
        this.entityData.set(PATTERN_2, variant);
    }

    public int getSecondPatternColor() {
        return this.entityData.get(PATTERN_2_COLOR);
    }

    public void setSecondPatternColor(int variant) {
        this.entityData.set(PATTERN_2_COLOR, variant);
    }


    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("VariantSkin", this.getVariantSkin());
        compoundnbt.putInt("FinModel", this.getFinModel());
        compoundnbt.putInt("FinColor", this.getFinColor());
        compoundnbt.putInt("TailModel", this.getTailModel());
        compoundnbt.putInt("TailColor", this.getTailColor());


        compoundnbt.putBoolean("HasSecondaryPattern", this.getHasSecondPattern());
        compoundnbt.putInt("MainPattern", this.getMainPattern());
        compoundnbt.putInt("MainPatternColor", this.getMainPatternColor());

        compoundnbt.putBoolean("HasSecondaryPattern", this.getHasSecondPattern());
        compoundnbt.putInt("SecondaryPattern", this.getSecondPattern());
        compoundnbt.putInt("SecondaryPatternColor", this.getSecondPatternColor());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
//        this.setVariantSkin(pTag.getInt("VariantSkin"));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("VariantSkin", 3)) {

            this.setVariantSkin(pDataTag.getInt("VariantSkin"));
            this.setFinModel(pDataTag.getInt("FinModel"));
            this.setFinColor(pDataTag.getInt("FinColor"));
            this.setTailModel(pDataTag.getInt("TailModel"));
            this.setTailColor(pDataTag.getInt("TailColor"));

            this.setHasMainPattern(pDataTag.getBoolean("HasMainPattern"));
            this.setMainPattern(pDataTag.getInt("MainPattern"));
            this.setMainPatternColor(pDataTag.getInt("MainPatternColor"));

            this.setHasSecondPattern(pDataTag.getBoolean("HasSecondaryPattern"));
            this.setSecondPattern(pDataTag.getInt("SecondaryPattern"));
            this.setSecondPatternColor(pDataTag.getInt("SecondaryPatternColor"));

        }else{
            this.setVariantSkin(this.random.nextInt(12));

            this.setFinModel(this.random.nextInt(2));
            this.setFinColor(this.random.nextInt(22));

            //0 checkered_front (checkered1)
            //1 checkered_back (checkered2)
            //2 line_up (line1)
            //3 line_down (line2)
            //4 striped_forward (striped1)
            //5 striped_backward (striped2)
            //6 mask
            //7 koi
            //8 dipped
            //9 belly

            this.setTailModel(this.random.nextInt(10));
            this.setTailColor(this.random.nextInt(22));

            //10 fullbody

            this.setHasMainPattern(this.random.nextInt(3)==0);
            this.setMainPattern(this.random.nextInt(11));
            this.setMainPatternColor(this.random.nextInt(22));


            this.setHasSecondPattern(this.random.nextInt(3)==0);
            this.setSecondPattern(this.random.nextInt(18));
            this.setSecondPatternColor(this.random.nextInt(22));

            //10 dot_tail (dot1)
            //11 dot_mid_up (dot2)
            //12 dot_mid_down (dot3)
            //13 dot_back (dot4)
            //14 dot_front (dot5)
            //15 double_dot
            //16 coat
            //17 bar
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public String getFinsName(int fin){
        if (fin == 0){
            return "jumbo";
        }else{
            return "regular";
        }
    }

    public String getTailName(int pattern){
        return switch (pattern){
            case 1 -> "sword";
            case 2 -> "toppin";
            case 3 -> "topsword";
            case 4 -> "btmsword";
            case 5 -> "btmpin";
            case 6 -> "flag";
            case 7 -> "square";
            case 8 -> "scissor";
            case 9 -> "lyre";
            default -> "round";
        };
    }

    public String getMainPatternName(int pattern){
        return switch (pattern){
            case 1 -> "checkered2";
            case 2 -> "line1";
            case 3 -> "line2";
            case 4 -> "striped1";
            case 5 -> "striped2";
            case 6 -> "mask";
            case 7 -> "koi";
            case 8 -> "dipped";
            case 9 -> "belly";
            case 10 -> "fullbody";
            default -> "checkered1";
        };
    }

    public String getSecondPatternName(int pattern){
        return switch (pattern){
            case 1 -> "checkered2";
            case 2 -> "line1";
            case 3 -> "line2";
            case 4 -> "striped1";
            case 5 -> "striped2";
            case 6 -> "mask";
            case 7 -> "koi";
            case 8 -> "dipped";
            case 9 -> "belly";
            case 10 -> "dot1";
            case 11 -> "dot2";
            case 12 -> "dot3";
            case 13 -> "dot4";
            case 14 -> "dot5";
            case 15 -> "doubledot";
            case 16 -> "coat";
            case 17 -> "bar";
            default -> "checkered1";
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
    }

    protected <E extends GuppyEntity> PlayState Controller(AnimationState<E> event) {
        if (this.isInWater()){
            event.setAndContinue(SWIM);
        }else{
            event.setAndContinue(FLOP);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(YAFMItems.GUPPY_BUCKET.get());
    }


}
