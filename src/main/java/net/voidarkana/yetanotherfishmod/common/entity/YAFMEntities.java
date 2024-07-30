package net.voidarkana.yetanotherfishmod.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.GuppyEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.MinnowEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.CatfishEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.FeatherbackEntity;

public class YAFMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, YetAnotherFishMod.MOD_ID);

    public static final RegistryObject<EntityType<FeatherbackEntity>> FEATHERBACK =
            ENTITY_TYPES.register("featherback",
                    () -> EntityType.Builder.of(FeatherbackEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "featherback").toString()));

    public static final RegistryObject<EntityType<MinnowEntity>> MINNOW =
            ENTITY_TYPES.register("minnow",
                    () -> EntityType.Builder.of(MinnowEntity::new, MobCategory.CREATURE)
                            .sized(0.3f, 0.2f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "minnow").toString()));

    public static final RegistryObject<EntityType<CatfishEntity>> CATFISH =
            ENTITY_TYPES.register("catfish",
                    () -> EntityType.Builder.of(CatfishEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "catfish").toString()));

    public static final RegistryObject<EntityType<GuppyEntity>> GUPPY =
            ENTITY_TYPES.register("guppy",
                    () -> EntityType.Builder.of(GuppyEntity::new, MobCategory.CREATURE)
                            .sized(0.3f, 0.3f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "guppy").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
