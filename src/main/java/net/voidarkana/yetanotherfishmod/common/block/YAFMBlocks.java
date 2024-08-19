package net.voidarkana.yetanotherfishmod.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.block.custom.DuckweedBlock;
import net.voidarkana.yetanotherfishmod.common.block.custom.HornwortBlock;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;

import java.util.function.Function;
import java.util.function.Supplier;

public class YAFMBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, YetAnotherFishMod.MOD_ID);

    public static final Supplier<Block> DUCKWEED = registerBlockWithItem("duckweed",
            ()-> new DuckweedBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD).mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .instabreak().noCollission()),
                    (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));


    public static final RegistryObject<Block> HORNWORT = registerBlock("hornwort",
            ()-> new HornwortBlock(BlockBehaviour.Properties.copy(Blocks.SEAGRASS).noOcclusion().instabreak().noCollission()));


    private static <T extends Block> Supplier<T> registerBlockWithItem(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        YAFMItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return YAFMItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
