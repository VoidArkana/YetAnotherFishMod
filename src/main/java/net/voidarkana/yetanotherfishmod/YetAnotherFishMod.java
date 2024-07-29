package net.voidarkana.yetanotherfishmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.yetanotherfishmod.client.renderers.BarbfishRenderer;
import net.voidarkana.yetanotherfishmod.client.renderers.FeatherbackRenderer;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.event.YAFMEvents;
import net.voidarkana.yetanotherfishmod.common.item.YAFMItems;
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

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new YAFMEvents());

    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(YAFMEntities.FEATHERBACK.get(), FeatherbackRenderer::new);
        EntityRenderers.register(YAFMEntities.BARB.get(), BarbfishRenderer::new);
    }

}
