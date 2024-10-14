package net.voidarkana.yetanotherfishmod.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
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

    public static class Blocks {

        public static final TagKey<Block> AQUARIUM_GLASS = tag("aquarium_glass_blocks");
        public static final TagKey<Block> INFERNAL_AQUARIUM_GLASS = tag("infernal_aquarium_glass_blocks");
        public static final TagKey<Block> RADON_AQUARIUM_GLASS = tag("radon_aquarium_glass_blocks");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(YetAnotherFishMod.MOD_ID, name));
        }
    }

    public static class Fluid {
        public static final TagKey<net.minecraft.world.level.material.Fluid> AC_ACID
                = tag("ac_acid");

        private static TagKey<net.minecraft.world.level.material.Fluid> tag(String name){
            return FluidTags.create(new ResourceLocation(YetAnotherFishMod.MOD_ID, name));
        }
    }

}
