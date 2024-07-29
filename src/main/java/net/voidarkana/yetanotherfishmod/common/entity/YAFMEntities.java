package net.voidarkana.yetanotherfishmod.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.custom.BarbfishEntity;
import net.voidarkana.yetanotherfishmod.common.entity.custom.FeatherbackEntity;

public class YAFMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, YetAnotherFishMod.MOD_ID);

    public static final RegistryObject<EntityType<FeatherbackEntity>> FEATHERBACK =
            ENTITY_TYPES.register("featherback",
                    () -> EntityType.Builder.of(FeatherbackEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "featherback").toString()));

    public static final RegistryObject<EntityType<BarbfishEntity>> BARB =
            ENTITY_TYPES.register("barb",
                    () -> EntityType.Builder.of(BarbfishEntity::new, MobCategory.CREATURE)
                            .sized(0.3f, 0.2f)
                            .build(new ResourceLocation(YetAnotherFishMod.MOD_ID, "barbfish").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
