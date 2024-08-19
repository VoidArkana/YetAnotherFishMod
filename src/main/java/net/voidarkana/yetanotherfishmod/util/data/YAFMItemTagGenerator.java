package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class YAFMItemTagGenerator extends ItemTagsProvider {

    public YAFMItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, YetAnotherFishMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(YAFMTags.Items.FISH_FEED)
                .add(YAFMItems.REGULAR_FEED.get())
                .add(YAFMItems.GREAT_FEED.get())
                .add(YAFMItems.QUALITY_FEED.get())
                .add(YAFMItems.PREMIUM_FEED.get());

        this.tag(ItemTags.FISHES).add(YAFMItems.RAW_FISH.get()).add(YAFMItems.COOKED_FISH.get());

        this.tag(ItemTags.AXOLOTL_TEMPT_ITEMS)
                .add(YAFMItems.BARB_BUCKET.get())
                .add(YAFMItems.FRESHWATER_SHARK_BUCKET.get())
                .add(YAFMItems.GUPPY_BUCKET.get())
                .add(YAFMItems.FEATHERBACK_BUCKET.get())
                .add(YAFMItems.CATFISH_BUCKET.get());

        this.tag(ItemTags.PIGLIN_LOVED)
                .add(YAFMItems.PREMIUM_FEED.get());
    }
}
