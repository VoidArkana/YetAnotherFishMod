package net.voidarkana.yetanotherfishmod.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.voidarkana.yetanotherfishmod.YetAnotherFishMod;
import net.voidarkana.yetanotherfishmod.common.entity.YAFMEntities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class FishBucketItem extends MobBucketItem {

    public FishBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item, boolean hasTooltip, Item.Properties builder) {
        super(entityType, fluid, () -> {
            return SoundEvents.BUCKET_EMPTY_FISH;
        }, builder);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> {
            return () -> {
                return YetAnotherFishMod.CALLBACKS.add(() -> {
                    ItemProperties.register(this, new ResourceLocation(YetAnotherFishMod.MOD_ID, "variant"), (stack, world, player, i) -> {
                        return stack.hasTag() ? (float)stack.getTag().getInt("Variant") : 0.0F;
                    });
                });
            };
        });
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY};
        ChatFormatting[] bchatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.AQUA};

        if (getFishType() == YAFMEntities.FEATHERBACK.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("Variant", 3)) {
                int i = compoundtag.getInt("Variant");


                String featherback_sci = "yafm.featherback_sci." + i;
                String common = "yafm.featherback_common." + i;

                pTooltipComponents.add(Component.translatable(common).withStyle(achatformatting));

                MutableComponent mutablecomponent = Component.translatable(featherback_sci);
                mutablecomponent.withStyle(bchatformatting);

                if (Screen.hasShiftDown()){
                    pTooltipComponents.add(mutablecomponent);
                }
            }
        }

//        if (getFishType() == YAFMEntities.GUPPY.get()) {
//            CompoundTag compoundtag = pStack.getTag();
//            if (compoundtag != null && compoundtag.contains("VariantModel", 3)) {
//                int i = compoundtag.getInt("VariantModel");
//                int j = compoundtag.getInt("VariantSkin");
//
//
//                String base = "yafm.guppy_base." + i;
//                String second = "yafm.guppy_second." + j;
//                String sci = "yafm.guppy.sci";
//
//                pTooltipComponents.add(Component.translatable(base).withStyle(achatformatting));
//
//                MutableComponent mutablecomponent = Component.translatable(second);
//
//                mutablecomponent.withStyle(achatformatting);
//                pTooltipComponents.add(mutablecomponent);
//
//
//                MutableComponent mutablecomponent2 = Component.translatable(sci);
//
//                mutablecomponent2.withStyle(bchatformatting);
//
//                if (Screen.hasShiftDown()){
//                pTooltipComponents.add(mutablecomponent2);
//                }
//            }
//        }

    }
}
