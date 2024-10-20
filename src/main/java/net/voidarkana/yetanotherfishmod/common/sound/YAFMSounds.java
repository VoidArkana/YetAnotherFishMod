package net.voidarkana.yetanotherfishmod.common.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;

public class YAFMSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, YetAnotherFishMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SALTY = registerSoundEvents("salty");
    public static final RegistryObject<SoundEvent> FRESH = registerSoundEvents("fresh");

    public static final RegistryObject<SoundEvent> AXOLOTL = registerSoundEvents("axolotl");
    public static final RegistryObject<SoundEvent> DRAGONFISH = registerSoundEvents("dragonfish");
    public static final RegistryObject<SoundEvent> SHUNJI = registerSoundEvents("shunji");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(YetAnotherFishMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
