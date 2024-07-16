package com.nosiphus.furniture;

import com.nosiphus.furniture.client.ClientHandler;
import com.nosiphus.furniture.common.CommonHandler;
import com.nosiphus.furniture.core.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class NosiphusFurnitureMod {

    public static final Logger LOGGER = LogManager.getLogger("nfm");

    public NosiphusFurnitureMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(eventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModCreativeTabs.CREATIVE_TABS.register(eventBus);
        ModFluids.FLUIDS.register(eventBus);
        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModMenuTypes.MENU_TYPES.register(eventBus);
        ModParticleTypes.PARTICLE_TYPES.register(eventBus);
        ModRecipeSerializer.RECIPE_SERIALIZER.register(eventBus);
        ModRecipeTypes.RECIPE_TYPES.register(eventBus);
        ModSounds.SOUNDS.register(eventBus);
        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(ClientHandler::onRegisterBlockColors);
            eventBus.addListener(ClientHandler::onRegisterItemColors);
            eventBus.addListener(ClientHandler::onRegisterParticleFactories);
            eventBus.addListener(ClientHandler::onRegisterRenderers);
        });

    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(CommonHandler::setup);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ClientHandler::setup);
    }

}