package net.voidarkana.yetanotherfishmod.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.item.custom.FishBucketItem;

@Mod.EventBusSubscriber(modid = YetAnotherFishMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YAFMItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, YetAnotherFishMod.MOD_ID);

    public static final RegistryObject<Item> FEATHERBACK_SPAWN_EGG = ITEMS.register("featherback_spawn_egg",
            () -> new ForgeSpawnEggItem(YAFMEntities.FEATHERBACK, 0x82827a, 0xd7d7a7, new Item.Properties()));

    public static final RegistryObject<Item> FEATHERBACK_BUCKET = ITEMS.register("featherback_bucket", () -> {
        return new FishBucketItem(YAFMEntities.FEATHERBACK, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).stacksTo(1));
    });


    public static final RegistryObject<Item> BARB_SPAWN_EGG = ITEMS.register("minnow_spawn_egg",
            () -> new ForgeSpawnEggItem(YAFMEntities.MINNOW, 0x40cb97, 0xd04a20, new Item.Properties()));

    public static final RegistryObject<Item> BARB_BUCKET = ITEMS.register("minnow_bucket", () -> {
        return new FishBucketItem(YAFMEntities.MINNOW, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).stacksTo(1));
    });


    public static final RegistryObject<Item> CATFISH_SPAWN_EGG = ITEMS.register("catfish_spawn_egg",
            () -> new ForgeSpawnEggItem(YAFMEntities.CATFISH, 0x33485b, 0x7f8c96, new Item.Properties()));

    public static final RegistryObject<Item> CATFISH_BUCKET = ITEMS.register("catfish_bucket", () -> {
        return new FishBucketItem(YAFMEntities.CATFISH, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).stacksTo(1));
    });


    public static final RegistryObject<Item> GUPPY_SPAWN_EGG = ITEMS.register("guppy_spawn_egg",
            () -> new ForgeSpawnEggItem(YAFMEntities.GUPPY, 0x343a5b, 0x71788b, new Item.Properties()));

    public static final RegistryObject<Item> GUPPY_BUCKET = ITEMS.register("guppy_bucket", () -> {
        return new FishBucketItem(YAFMEntities.GUPPY, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).stacksTo(1));
    });

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
