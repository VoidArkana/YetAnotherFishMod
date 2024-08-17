package net.voidarkana.yetanotherfishmod.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;

public class YAFMTags {

    public static class Biomes {

        //public static final TagKey<Biome> VANILLA_CHICKEN_REMOVED_BIOMES = tag("vanilla_chicken_removed_biomes");

        private static TagKey<Biome> tag(String pName) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(YetAnotherFishMod.MOD_ID, pName));
        }

    }

    public static class Items {

        public static final TagKey<Item> FISH_FEED = tag("fish_feed");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(YetAnotherFishMod.MOD_ID, name));
        }
    }

}
