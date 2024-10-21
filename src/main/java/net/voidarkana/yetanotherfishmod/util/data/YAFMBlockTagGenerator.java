package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.block.YAFMBlocks;
import net.voidarkana.yetanotherfishmod.util.YAFMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class YAFMBlockTagGenerator extends BlockTagsProvider {

    public YAFMBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, YetAnotherFishMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(YAFMTags.Blocks.AQUARIUM_GLASS)
                .add(YAFMBlocks.AQUARIUM_GLASS.get())
                .add(YAFMBlocks.AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.CLEAR_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.CLEAR_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.INFERNAL_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_INFERNAL_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.RADON_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.RADON_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_RADON_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.SUGAR_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.SUGAR_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_SUGAR_AQUARIUM_GLASS.get());

        this.tag(YAFMTags.Blocks.INFERNAL_AQUARIUM_GLASS)
                .add(YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.INFERNAL_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_INFERNAL_AQUARIUM_GLASS.get());

        this.tag(YAFMTags.Blocks.RADON_AQUARIUM_GLASS)
                .add(YAFMBlocks.RADON_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.RADON_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_RADON_AQUARIUM_GLASS.get());

        this.tag(YAFMTags.Blocks.SUGAR_AQUARIUM_GLASS)
                .add(YAFMBlocks.SUGAR_AQUARIUM_GLASS.get())
                .add(YAFMBlocks.SUGAR_AQUARIUM_GLASS_PANE.get())
                .add(YAFMBlocks.TINTED_SUGAR_AQUARIUM_GLASS.get());

        this.tag(Tags.Blocks.GLASS).addTags(YAFMTags.Blocks.AQUARIUM_GLASS);
    }
}
