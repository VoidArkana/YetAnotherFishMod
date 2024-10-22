package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.worldgen.YAFMBiomeModifiers;
import net.voidarkana.yetanotherfishmod.common.worldgen.YAFMConfiguredFeatures;
import net.voidarkana.yetanotherfishmod.common.worldgen.YAFMPlacedFeatures;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class YAFMWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, YAFMConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, YAFMPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, YAFMBiomeModifiers::bootstrap);

    public YAFMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(YetAnotherFishMod.MOD_ID));
    }


}
