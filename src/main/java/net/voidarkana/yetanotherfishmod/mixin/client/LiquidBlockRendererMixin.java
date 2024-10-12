package net.voidarkana.yetanotherfishmod.mixin.client;

import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.yetanotherfishmod.common.block.YAFMBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlockRenderer.class)
public class LiquidBlockRendererMixin {

    @Inject(
            method = {"Lnet/minecraft/client/renderer/block/LiquidBlockRenderer;isFaceOccludedByNeighbor(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;FLnet/minecraft/world/level/block/state/BlockState;)Z"},
            remap = true,
            cancellable = true,
            at = @At(value = "TAIL")
    )

    private static void isFaceOccludedByNeighbor(BlockGetter blockGetter, BlockPos pos, Direction direction, float f, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(YAFMBlocks.AQUARIUM_GLASS.get()) || state.is(YAFMBlocks.CLEAR_AQUARIUM_GLASS.get()) || state.is(YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get())) {
            cir.setReturnValue(true);
        }
    }
}