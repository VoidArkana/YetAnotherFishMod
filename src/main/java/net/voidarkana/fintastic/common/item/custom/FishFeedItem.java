package net.voidarkana.fintastic.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FishFeedItem extends Item {

    private int quality;

    public FishFeedItem(Properties pProperties, int pQuality) {
        super(pProperties);
        this.quality = pQuality;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.AQUA};
        ChatFormatting[] bchatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

        MutableComponent translatable = Component.translatable("fintastic.translatable.shift");
        translatable.withStyle(achatformatting);

        MutableComponent fishfeedDesc = Component.translatable("fintastic.translatable.fishfeed." + this.quality);
        fishfeedDesc.withStyle(bchatformatting);

        if (!Screen.hasShiftDown()){
            pTooltipComponents.add(translatable);
        }else {
            pTooltipComponents.add(fishfeedDesc);
        }

    }

}
