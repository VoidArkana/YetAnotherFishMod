package net.voidarkana.yetanotherfishmod.common.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.voidarkana.yetanotherfishmod.client.particles.YAFMParticles;
import net.voidarkana.yetanotherfishmod.common.entity.custom.base.SchoolingFish;

public class DaphneaSwarmEntity extends WaterAnimal {

    private static final EntityDataAccessor<Integer> SWARM_SIZE = SynchedEntityData.defineId(DaphneaSwarmEntity.class, EntityDataSerializers.INT);
    int attackableCooldown;

    public DaphneaSwarmEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.65D)
                .add(Attributes.MAX_HEALTH, 1.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 10));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SWARM_SIZE, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("SwarmSize", this.getSwarmSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setSwarmSize(pCompound.getInt("SwarmSize"));
    }

    public int getSwarmSize() {
        return this.entityData.get(SWARM_SIZE);
    }

    public void setSwarmSize(int variant) {
        this.entityData.set(SWARM_SIZE, variant);
    }

    public void aiStep() {
        super.aiStep();
        float size = 0.4f;
        if (random.nextInt(10) <= 0 && this.isInWaterOrBubble()) this.level().addParticle(YAFMParticles.DAPHNEA_PARTICLES.get(),
                this.getRandomX(size), this.getRandomY(), this.getRandomZ(size), 0.0, 0.0, 0.0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getSwarmSize()<1 || (this.onGround() && !this.isInWater())){
            this.discard();
        }

        if (this.attackableCooldown>0){
            attackableCooldown--;
        }
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof DaphneaSwarmEntity){
            super.doPush(entity);
        }
    }

    @Override
    protected void blockedByShield(LivingEntity pDefender) {
        pDefender.knockback(0, this.getX(), this.getZ());
    }

    @Override
    public boolean startRiding(Entity pVehicle) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {

        if (this.attackableCooldown==0){

            this.attackableCooldown = 150;

            int prevSwarmSize = this.getSwarmSize();

            this.setSwarmSize(prevSwarmSize - 1);
        }

        return false;
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return this.attackableCooldown == 0 && super.canBeSeenAsEnemy();
    }

}
