package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class YAFMBiomeTagGenerator extends BiomeTagsProvider {

    public YAFMBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, YetAnotherFishMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(YAFMTags.Biomes.FEATHERBACK_BIOMES)
                .addTag(Tags.Biomes.IS_SWAMP);

        this.tag(YAFMTags.Biomes.ARAPAIMA_BIOMES)
                .addTag(BiomeTags.IS_JUNGLE);

        this.tag(YAFMTags.Biomes.CATFISH_BIOMES)
                .addTag(BiomeTags.IS_RIVER)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(Tags.Biomes.IS_SWAMP);

        this.tag(YAFMTags.Biomes.GUPPY_BIOMES)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(Tags.Biomes.IS_SWAMP);

        this.tag(YAFMTags.Biomes.FWSHARK_BIOMES)
                .addTag(BiomeTags.IS_RIVER);

        this.tag(YAFMTags.Biomes.MINNOW_BIOMES)
                .addTag(BiomeTags.IS_RIVER)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(Tags.Biomes.IS_SWAMP);

        this.tag(YAFMTags.Biomes.PLECO_BIOMES)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(Tags.Biomes.IS_SWAMP);

        this.tag(YAFMTags.Biomes.ARTEMIA_BIOMES)
                .addTag(Tags.Biomes.IS_DESERT);

    }

}
