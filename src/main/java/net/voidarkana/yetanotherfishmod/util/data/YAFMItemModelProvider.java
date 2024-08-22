package net.voidarkana.yetanotherfishmod.util.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;

public class YAFMItemModelProvider extends ItemModelProvider {
    public YAFMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, YetAnotherFishMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(YAFMItems.FEATHERBACK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.FEATHERBACK_BUCKET);

        withExistingParent(YAFMItems.BARB_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.BARB_BUCKET);

        withExistingParent(YAFMItems.CATFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.CATFISH_BUCKET);

        withExistingParent(YAFMItems.GUPPY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.GUPPY_BUCKET);

        withExistingParent(YAFMItems.FRESHWATER_SHARK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.FRESHWATER_SHARK_BUCKET);

        simpleItem(YAFMItems.REGULAR_FEED);
        simpleItem(YAFMItems.QUALITY_FEED);
        simpleItem(YAFMItems.GREAT_FEED);
        simpleItem(YAFMItems.PREMIUM_FEED);
        simpleItem(YAFMItems.BAD_FEED);

        simpleItem(YAFMItems.COOKED_FISH);
        simpleItem(YAFMItems.RAW_FISH);

        withExistingParent(YAFMItems.PLECO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(YAFMItems.PLECO_BUCKET);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(YetAnotherFishMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
