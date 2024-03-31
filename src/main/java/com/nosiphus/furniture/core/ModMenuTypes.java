package com.nosiphus.furniture.core;

import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.blockentity.BinBlockEntity;
import com.nosiphus.furniture.blockentity.WallCabinetBlockEntity;
import com.nosiphus.furniture.client.menu.BinMenu;
import com.nosiphus.furniture.client.menu.MicrowaveMenu;
import com.nosiphus.furniture.client.menu.OvenMenu;
import com.nosiphus.furniture.client.menu.WallCabinetMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, NosiphusFurnitureMod.MOD_ID);

    public static final RegistryObject<MenuType<BinMenu>> BIN = register("bin", (IContainerFactory<BinMenu>) (ID, inventory, data) -> {
        BinBlockEntity binBlockEntity = (BinBlockEntity) inventory.player.level.getBlockEntity(data.readBlockPos());
        return new BinMenu(ID, inventory, binBlockEntity);
    });
    public static final RegistryObject<MenuType<MicrowaveMenu>> MICROWAVE = registerMenuType((ID, inventory, extraData) -> new MicrowaveMenu(ID, inventory), "microwave");
    public static final RegistryObject<MenuType<OvenMenu>> OVEN = registerMenuType((ID, inventory, extraData) -> new OvenMenu(ID, inventory), "oven");
    public static final RegistryObject<MenuType<WallCabinetMenu>> WALL_CABINET = register("wall_cabinet", (IContainerFactory<WallCabinetMenu>) (ID, inventory, data) -> {
        WallCabinetBlockEntity wallCabinetBlockEntity = (WallCabinetBlockEntity) inventory.player.level.getBlockEntity(data.readBlockPos());
        return new WallCabinetMenu(ID, inventory, wallCabinetBlockEntity);
    });

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String key, MenuType.MenuSupplier<T> supplier) {
        MenuType<T> type = new MenuType<>(supplier);
        return MENU_TYPES.register(key, () -> type);
    }

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

}
