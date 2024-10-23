package net.voidarkana.fintastic.common.item.custom;

import net.minecraft.world.food.FoodProperties;

public class YAFMFoods {

    public static final FoodProperties RAW_FISH = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();

    public static final FoodProperties COOKED_FISH = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationMod(0.6F).build();
}
