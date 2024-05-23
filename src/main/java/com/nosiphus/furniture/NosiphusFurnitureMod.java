package com.nosiphus.furniture;

import com.nosiphus.furniture.client.event.CreativeScreenEvents;
import com.nosiphus.furniture.client.menu.DishwasherMenu;
import com.nosiphus.furniture.client.menu.screen.*;
import com.nosiphus.furniture.client.renderer.blockentity.*;
import com.nosiphus.furniture.core.*;
import com.nosiphus.furniture.network.PacketHandler;
import com.nosiphus.furniture.particle.ShowerParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NosiphusFurnitureMod.MOD_ID)
public class NosiphusFurnitureMod {

    public static final Logger LOGGER = LogManager.getLogger(NosiphusFurnitureMod.MOD_ID);
    public static final CreativeModeTab GROUP = new NosiphusFurnitureModTab(NosiphusFurnitureMod.MOD_ID);
    public static final String MOD_ID = "nfm";

    public NosiphusFurnitureMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(eventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModFluids.FLUIDS.register(eventBus);
        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModMenuTypes.MENU_TYPES.register(eventBus);
        ModParticleTypes.PARTICLE_TYPES.register(eventBus);
        ModRecipeSerializer.RECIPE_SERIALIZER.register(eventBus);
        ModRecipeTypes.RECIPE_TYPES.register(eventBus);
        ModSounds.SOUNDS.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }

    @Mod.EventBusSubscriber(modid = NosiphusFurnitureMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientHandler {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOAPY_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAPY_WATER.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.BIN.get(), BinMenuScreen::new);
            MenuScreens.register(ModMenuTypes.DISHWASHER.get(), DishwasherMenuScreen::new);
            MenuScreens.register(ModMenuTypes.MICROWAVE.get(), MicrowaveMenuScreen::new);
            MenuScreens.register(ModMenuTypes.OVEN.get(), OvenMenuScreen::new);
            MenuScreens.register(ModMenuTypes.WALL_CABINET.get(), WallCabinetMenuScreen::new);
            MinecraftForge.EVENT_BUS.register(new CreativeScreenEvents());
        }

        @SubscribeEvent
        public static void onStitch(TextureStitchEvent.Pre event) {
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_AXE);
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_BUCKET);
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_HOE);
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_PICKAXE);
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_SHOVEL);
            event.addSprite(DishwasherMenu.EMPTY_TOOL_SLOT_SWORD);
        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((state, reader, pos, i) -> FoliageColor.getEvergreenColor(),
                    ModBlocks.CHRISTMAS_TREE_BOTTOM.get());
            event.register((state, reader, pos, i) -> FoliageColor.getEvergreenColor(),
                    ModBlocks.CHRISTMAS_TREE.get());
            event.register((state, reader, pos, i) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColor.getDefaultColor(),
                    ModBlocks.WREATH.get());
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            event.register((stack, i) -> {
                BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                return Minecraft.getInstance().getBlockColors().getColor(state, null, null, i);
            }, ModBlocks.CHRISTMAS_TREE.get(), ModBlocks.WREATH.get());
        }

        @SubscribeEvent
        public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ModParticleTypes.SHOWER_PARTICLE.get(),
                    ShowerParticle.Provider::new);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.BIRD_BATH.get(), BirdBathBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHOPPING_BOARD.get(), ChoppingBoardBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.MODERN_KITCHEN_SINK.get(), ModernKitchenSinkBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.SINK.get(), SinkBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.TOILET.get(), ToiletBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.WATER_TANK.get(), WaterTankBlockEntityRenderer::new);
        }

    }

    @Mod.EventBusSubscriber(modid = NosiphusFurnitureMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CommonHandler {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            PacketHandler.init();
        }

    }

}