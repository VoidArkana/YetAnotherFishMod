package net.voidarkana.fintastic.common.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.voidarkana.fintastic.Fintastic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = Fintastic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class YAFMForgeEvents {

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        LootPool pool = event.getTable().getPool("main");
        if (name.equals(BuiltInLootTables.FISHING_FISH)) {
            if (pool!=null){
                addEntry(pool, getInjectEntry(new ResourceLocation(Fintastic.MOD_ID, "gameplay/fishing/junk"),
                        25, 1));

                addEntry(pool, getInjectEntry(new ResourceLocation(Fintastic.MOD_ID, "gameplay/fishing/treasure"),
                        20, 1));
            }
        }
    }

    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        try {
            Field entries = ObfuscationReflectionHelper.findField(LootPool.class, "f_79023_");
            entries.setAccessible(true);

            LootPoolEntryContainer[] lootPoolEntriesArray = (LootPoolEntryContainer[]) entries.get(pool);
            ArrayList<LootPoolEntryContainer> newLootEntries = new ArrayList<>(List.of(lootPoolEntriesArray));

            if (newLootEntries.stream().anyMatch(e -> e == entry)) {
                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
            }

            newLootEntries.add(entry);

            LootPoolEntryContainer[] newLootEntriesArray = new LootPoolEntryContainer[newLootEntries.size()];
            newLootEntries.toArray(newLootEntriesArray);
            entries.set(pool, newLootEntriesArray);
        } catch (IllegalAccessException e) {
            //Aquaculture.LOG.error("Error occurred when attempting to add a new entry, to the fishing loot table");
            e.printStackTrace();
        }
    }
}
