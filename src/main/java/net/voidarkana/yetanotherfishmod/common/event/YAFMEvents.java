package net.voidarkana.yetanotherfishmod.common.event;


import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;
import net.voidarkana.yetanotherfishmod.common.entity.custom.*;

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
    }

}
