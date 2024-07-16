package com.nosiphus.furniture.core;

import com.nosiphus.furniture.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_TABS.register("creative_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();
        builder.icon(() -> new ItemStack(ModBlocks.CHAIR_WHITE_MODERN.get()));
        builder.title(Component.translatable("itemGroup.nfm"));
        builder.displayItems((flags, output) -> {
            ModItems.ITEMS.getEntries().forEach(registryObject -> {
                output.accept(registryObject.get());
            });
        });
        return builder.build();
    });

}
