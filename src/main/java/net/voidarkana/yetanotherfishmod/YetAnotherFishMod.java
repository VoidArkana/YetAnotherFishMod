package net.voidarkana.yetanotherfishmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.yetanotherfishmod.client.renderers.*;
import net.voidarkana.yetanotherfishmod.common.block.YAFMBlocks;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.event.YAFMEvents;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
import net.voidarkana.yetanotherfishmod.util.YAFMCreativeTab;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


@Mod(YetAnotherFishMod.MOD_ID)
public class YetAnotherFishMod
{

    public static final String MOD_ID = "yetanotherfishmod";
    public static final List<Runnable> CALLBACKS = new ArrayList<>();
    private static final Logger LOGGER = LogUtils.getLogger();

    public YetAnotherFishMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        YAFMCreativeTab.register(modEventBus);

        YAFMEntities.register(modEventBus);
        YAFMItems.register(modEventBus);
        YAFMBlocks.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new YAFMEvents());

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            ComposterBlock.COMPOSTABLES.put(YAFMBlocks.DUCKWEED.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(YAFMBlocks.HORNWORT.get().asItem(), 0.4F);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();

        EntityRenderers.register(YAFMEntities.FEATHERBACK.get(), FeatherbackRenderer::new);
        EntityRenderers.register(YAFMEntities.MINNOW.get(), MinnowRenderer::new);
        EntityRenderers.register(YAFMEntities.CATFISH.get(), CatfishRenderer::new);
        EntityRenderers.register(YAFMEntities.GUPPY.get(), GuppyRenderer::new);
        EntityRenderers.register(YAFMEntities.FRESHWATER_SHARK.get(), FreshwaterSharkRenderer::new);
    }

}
