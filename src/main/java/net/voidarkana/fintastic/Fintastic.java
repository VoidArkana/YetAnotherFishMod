package net.voidarkana.fintastic;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.voidarkana.fintastic.client.particles.YAFMParticles;
import net.voidarkana.fintastic.client.renderers.*;
import net.voidarkana.fintastic.common.block.YAFMBlocks;
import net.voidarkana.fintastic.common.entity.YAFMEntities;
import net.voidarkana.fintastic.common.entity.YAFMEntityPlacements;
import net.voidarkana.fintastic.common.event.YAFMEvents;
import net.voidarkana.fintastic.common.item.YAFMItems;
import net.voidarkana.fintastic.common.item.custom.FishnetItem;
import net.voidarkana.fintastic.common.loot.YAFMLootModifiers;
import net.voidarkana.fintastic.common.sound.YAFMSounds;
import net.voidarkana.fintastic.common.worldgen.YAFMConfiguredFeatures;
import net.voidarkana.fintastic.server.MessageHurtMultipart;
import net.voidarkana.fintastic.server.MessageInteractMultipart;
import net.voidarkana.fintastic.util.ClientProxy;
import net.voidarkana.fintastic.util.CommonProxy;
import net.voidarkana.fintastic.util.YAFMCreativeTab;
import org.slf4j.Logger;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.ArrayList;
import java.util.List;


@Mod(Fintastic.MOD_ID)
public class Fintastic
{
    public static final SimpleChannel NETWORK_WRAPPER;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static int packetsRegistered;

    public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    static {
        NetworkRegistry.ChannelBuilder channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("fintastic", "main_channel"));
        String version = PROTOCOL_VERSION;
        version.getClass();
        channel = channel.clientAcceptedVersions(version::equals);
        version = PROTOCOL_VERSION;
        version.getClass();
        NETWORK_WRAPPER = channel.serverAcceptedVersions(version::equals).networkProtocolVersion(() -> {
            return PROTOCOL_VERSION;
        }).simpleChannel();
    }

    public static final String MOD_ID = "fintastic";
    public static final List<Runnable> CALLBACKS = new ArrayList<>();
    private static final Logger LOGGER = LogUtils.getLogger();

    public Fintastic()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        YAFMCreativeTab.register(modEventBus);

        YAFMEntities.register(modEventBus);
        YAFMSounds.register(modEventBus);
        YAFMItems.register(modEventBus);
        YAFMBlocks.register(modEventBus);
        YAFMParticles.register(modEventBus);
        YAFMLootModifiers.register(modEventBus);

        YAFMConfiguredFeatures.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new YAFMEvents());

        PROXY.init();

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        YAFMEntityPlacements.entityPlacement();

        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageHurtMultipart.class, MessageHurtMultipart::write, MessageHurtMultipart::read, MessageHurtMultipart.Handler::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageInteractMultipart.class, MessageInteractMultipart::write, MessageInteractMultipart::read, MessageInteractMultipart.Handler::handle);

        event.enqueueWork(()->{
            ComposterBlock.COMPOSTABLES.put(YAFMBlocks.DUCKWEED.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(YAFMBlocks.HORNWORT.get().asItem(), 0.4F);
        });
    }


    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(PROXY::clientInit);

        CALLBACKS.forEach(Runnable::run);
        CALLBACKS.clear();

        EntityRenderers.register(YAFMEntities.FEATHERBACK.get(), FeatherbackRenderer::new);
        EntityRenderers.register(YAFMEntities.MINNOW.get(), MinnowRenderer::new);
        EntityRenderers.register(YAFMEntities.CATFISH.get(), CatfishRenderer::new);
        EntityRenderers.register(YAFMEntities.GUPPY.get(), GuppyRenderer::new);
        EntityRenderers.register(YAFMEntities.FRESHWATER_SHARK.get(), FreshwaterSharkRenderer::new);
        EntityRenderers.register(YAFMEntities.PLECO.get(), PlecoRenderer::new);
        EntityRenderers.register(YAFMEntities.ARAPAIMA.get(), ArapaimaRenderer::new);

        EntityRenderers.register(YAFMEntities.DAPHNIA.get(), DaphniaRenderer::new);

        EntityRenderers.register(YAFMEntities.ARTEMIA.get(), ArtemiaRenderer::new);

        ItemProperties.register(YAFMItems.FISHNET.get(), new ResourceLocation("has_entity"),
                (stack, level, living, i) -> living != null && FishnetItem.containsEntity(stack) ? 1 : 0);
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }

}
