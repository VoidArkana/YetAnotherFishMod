package net.voidarkana.yetanotherfishmod.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.block.YAFMBlocks;

@Mod.EventBusSubscriber(modid = YetAnotherFishMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class YAFMClientEvents {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) ->
                pLevel != null && pPos != null ? BiomeColors.getAverageWaterColor(pLevel, pPos)
                        : 0x4f9ce3, YAFMBlocks.AQUARIUM_GLASS.get());


        event.getBlockColors().register((pState, pLevel, pPos, pTintIndex) ->
                0xff6f36, YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

        event.getItemColors().register((pStack, pTintIndex) -> {
            BlockState blockstate = ((BlockItem)pStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, pTintIndex);
        }, YAFMBlocks.AQUARIUM_GLASS.get());


        event.getItemColors().register((pStack, pTintIndex) -> 0xff6f36, YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get());

    }
}
