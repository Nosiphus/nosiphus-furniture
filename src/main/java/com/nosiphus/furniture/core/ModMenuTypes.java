package com.nosiphus.furniture.core;

import com.nosiphus.furniture.blockentity.BinBlockEntity;
import com.nosiphus.furniture.client.menu.*;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "nfm");

    public static final RegistryObject<MenuType<BinMenu>> BIN_MENU = register("bin", (IContainerFactory<BinMenu>) (ID, inventory, data) -> {
        BinBlockEntity binBlockEntity = (BinBlockEntity) inventory.player.level.getBlockEntity(data.readBlockPos());
        return new BinMenu(ID, inventory, binBlockEntity);
    });

    public static final RegistryObject<MenuType<MicrowaveMenu>> MICROWAVE_MENU = registerMenuType((ID, inventory, extraData) -> new MicrowaveMenu(ID, inventory), "microwave_menu");
    public static final RegistryObject<MenuType<OvenMenu>> OVEN_MENU = registerMenuType((ID, inventory, extraData) -> new OvenMenu(ID, inventory), "oven_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String key, MenuType.MenuSupplier<T> supplier) {
        MenuType<T> type = new MenuType<>(supplier);
        return MENU_TYPES.register(key, () -> type);
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

}
