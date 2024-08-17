package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
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
    }
}
