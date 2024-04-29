package com.nosiphus.furniture.core;

import com.nosiphus.furniture.NosiphusFurnitureMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NosiphusFurnitureMod.MOD_ID);

    //Food
    public static final RegistryObject<Item> BREAD_SLICE = register("bread_slice",
            () -> new Item(new Item.Properties().tab(NosiphusFurnitureMod.GROUP)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .alwaysEat()
                            .build())));
    public static final RegistryObject<Item> TOAST = register("toast",
            () -> new Item(new Item.Properties().tab(NosiphusFurnitureMod.GROUP)
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationMod(0.2f)
                            .alwaysEat()
                            .build())));

    //Tools
    public static final RegistryObject<Item> KNIFE = register("knife",
            () -> new SwordItem(Tiers.STONE, 3, -2.4F, (new Item.Properties().tab(NosiphusFurnitureMod.GROUP))));
    public static final RegistryObject<Item> SOAP = register("soap",
            () -> new Item(new Item.Properties().tab(NosiphusFurnitureMod.GROUP)));
    public static final RegistryObject<Item> SOAPY_WATER_BUCKET = register("soapy_water_bucket",
            () -> new BucketItem(ModFluids.SOAPY_WATER, new Item.Properties().tab(NosiphusFurnitureMod.GROUP).stacksTo(1)));
    public static final RegistryObject<Item> SUPER_SOAPY_WATER_BUCKET = register("super_soapy_water_bucket",
            () -> new BucketItem(ModFluids.SUPER_SOAPY_WATER, new Item.Properties().tab(NosiphusFurnitureMod.GROUP).stacksTo(1)));

    //Methods
    private static RegistryObject<Item> register(String name, Supplier<Item> item)
    {
        return ITEMS.register(name, item);
    }

}
