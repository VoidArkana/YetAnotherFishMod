package net.voidarkana.yetanotherfishmod.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.TropicalFish;
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
    public FishBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item,
                          boolean hasTooltip, Properties builder) {
        super(entityType, fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> YetAnotherFishMod.CALLBACKS.add(() ->
                ItemProperties.register(this, new ResourceLocation(YetAnotherFishMod.MOD_ID, "variant"),
                        (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (getFishType() == YAFMEntities.FEATHERBACK.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("Variant", 3)) {
                int i = compoundtag.getInt("Variant");

                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                String featherback_model = "yafm.featherback." + i;

                pTooltipComponents.add(Component.translatable(featherback_model).withStyle(achatformatting));
            }
        }

        if (getFishType() == YAFMEntities.GUPPY.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("VariantModel", 3)) {
                int i = compoundtag.getInt("VariantModel");
                int j = compoundtag.getInt("VariantSkin");

                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                String base = "yafm.guppy_base." + i;
                String second = "yafm.guppy_second." + j;

                pTooltipComponents.add(Component.translatable(base).withStyle(achatformatting));

                MutableComponent mutablecomponent = Component.translatable(second);

                mutablecomponent.withStyle(achatformatting);
                pTooltipComponents.add(mutablecomponent);
            }
        }

    }
}
