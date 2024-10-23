package net.voidarkana.fintastic.mixin.client;

import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.voidarkana.fintastic.util.YAFMTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlockRenderer.class)
public class LiquidBlockRendererMixin {

    @Inject(
            method = {"shouldRenderFace"},
            cancellable = true,
            at = @At(value = "TAIL")
    )
    private static void shouldRenderFace(BlockAndTintGetter pLevel, BlockPos pPos, FluidState pFluidState, BlockState pBlockState, Direction pSide, FluidState pNeighborFluid, CallbackInfoReturnable<Boolean> cir) {

        if (pFluidState.is(Fluids.WATER)){
            if(pLevel.getBlockState(pPos.offset(pSide.getNormal())).is(YAFMTags.Blocks.AQUARIUM_GLASS)
            || pLevel.getBlockState(pPos).is(YAFMTags.Blocks.AQUARIUM_GLASS)) {
                cir.setReturnValue(false);
            }
        }

        if (pFluidState.is(Fluids.LAVA)){
            if(pLevel.getBlockState(pPos.offset(pSide.getNormal())).is(YAFMTags.Blocks.INFERNAL_AQUARIUM_GLASS)){
                cir.setReturnValue(false);
            }
        }

        if (pFluidState.is(YAFMTags.Fluid.AC_ACID)){
            if(pLevel.getBlockState(pPos.offset(pSide.getNormal())).is(YAFMTags.Blocks.RADON_AQUARIUM_GLASS)
                    || pLevel.getBlockState(pPos).is(YAFMTags.Blocks.RADON_AQUARIUM_GLASS)){
                cir.setReturnValue(false);
            }
        }

        if (pFluidState.is(YAFMTags.Fluid.AC_SODA)){
            if(pLevel.getBlockState(pPos.offset(pSide.getNormal())).is(YAFMTags.Blocks.SUGAR_AQUARIUM_GLASS)
                    || pLevel.getBlockState(pPos).is(YAFMTags.Blocks.SUGAR_AQUARIUM_GLASS)){
                cir.setReturnValue(false);
            }
        }

    }

//    @Inject(
//            method = {"isFaceOccludedByNeighbor"},
//            cancellable = true,
//            at = @At(value = "TAIL")
//    )
//    private static void isFaceOccludedByNeighbor(BlockGetter blockGetter, BlockPos pos, Direction direction, float f, BlockState state, CallbackInfoReturnable<Boolean> cir) {
//        if (state.is(YAFMBlocks.CLEAR_AQUARIUM_GLASS_PANE.get()) || state.is(YAFMBlocks.INFERNAL_AQUARIUM_GLASS_PANE.get())
//                || state.is(YAFMBlocks.AQUARIUM_GLASS_PANE.get()) || state.is(YAFMBlocks.AQUARIUM_GLASS.get())
//                || state.is(YAFMBlocks.CLEAR_AQUARIUM_GLASS.get()) || state.is(YAFMBlocks.INFERNAL_AQUARIUM_GLASS.get())
//                || state.is(YAFMBlocks.TINTED_AQUARIUM_GLASS.get())) {
//            cir.setReturnValue(true);
//        }
//    }

//    @Inject(
//            method = {"isFaceOccludedBySelf"},
//            cancellable = true,
//            at = @At(value = "TAIL")
//    )
//    private static void isFaceOccludedBySelf(BlockGetter pLevel, BlockPos pPos, BlockState state, Direction pFace, CallbackInfoReturnable<Boolean> cir) {
//        if (state.is(YAFMBlocks.AQUARIUM_GLASS_PANE.get())
//                || state.is(YAFMBlocks.CLEAR_AQUARIUM_GLASS_PANE.get())
//                || state.is(YAFMBlocks.INFERNAL_AQUARIUM_GLASS_PANE.get())
//                && !pFace.getAxis().isVertical()) {
//            cir.setReturnValue(true);
//        }
//    }
}