package net.voidarkana.yetanotherfishmod.common.event;


import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CritParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.client.particles.DaphneaParticle;
import net.voidarkana.yetanotherfishmod.client.particles.YAFMParticles;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.entity.custom.*;

import java.rmi.registry.Registry;

@Mod.EventBusSubscriber(modid = YetAnotherFishMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YAFMEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(YAFMEntities.FEATHERBACK.get(), FeatherbackEntity.createAttributes().build());
        event.put(YAFMEntities.MINNOW.get(), MinnowEntity.createAttributes().build());
        event.put(YAFMEntities.CATFISH.get(), CatfishEntity.createAttributes().build());
        event.put(YAFMEntities.GUPPY.get(), GuppyEntity.createAttributes().build());
        event.put(YAFMEntities.FRESHWATER_SHARK.get(), FreshwaterSharkEntity.createAttributes().build());
        event.put(YAFMEntities.PLECO.get(), PlecoEntity.createAttributes().build());
        event.put(YAFMEntities.ARAPAIMA.get(), ArapaimaEntity.createAttributes().build());
        event.put(YAFMEntities.DAPHNEA.get(), DaphneaSwarmEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event){
        Minecraft.getInstance().particleEngine.register(YAFMParticles.DAPHNEA_PARTICLES.get(),
                DaphneaParticle.Factory :: new);
    }

}
