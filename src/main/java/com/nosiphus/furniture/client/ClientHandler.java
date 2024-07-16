package com.nosiphus.furniture.client;

import com.nosiphus.furniture.client.event.CreativeScreenEvents;
import com.nosiphus.furniture.client.gui.screen.inventory.BinMenuScreen;
import com.nosiphus.furniture.client.gui.screen.inventory.MicrowaveMenuScreen;
import com.nosiphus.furniture.client.gui.screen.inventory.OvenMenuScreen;
import com.nosiphus.furniture.client.gui.screen.inventory.WallCabinetMenuScreen;
import com.nosiphus.furniture.client.renderer.blockentity.*;
import com.nosiphus.furniture.core.*;
import com.nosiphus.furniture.particle.ShowerParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientHandler {

    public static void setup() {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOAPY_WATER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAPY_WATER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SUPER_SOAPY_WATER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SUPER_SOAPY_WATER.get(), RenderType.translucent());
        MenuScreens.register(ModMenuTypes.BIN.get(), BinMenuScreen::new);
        MenuScreens.register(ModMenuTypes.MICROWAVE.get(), MicrowaveMenuScreen::new);
        MenuScreens.register(ModMenuTypes.OVEN.get(), OvenMenuScreen::new);
        MenuScreens.register(ModMenuTypes.WALL_CABINET.get(), WallCabinetMenuScreen::new);
        MinecraftForge.EVENT_BUS.register(new CreativeScreenEvents());
    }

    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, reader, pos, i) -> FoliageColor.getEvergreenColor(),
                ModBlocks.CHRISTMAS_TREE_BOTTOM.get());
        event.register((state, reader, pos, i) -> FoliageColor.getEvergreenColor(),
                ModBlocks.CHRISTMAS_TREE.get());
        event.register((state, reader, pos, i) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColor.getDefaultColor(),
                ModBlocks.WREATH.get());
    }

    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, i) -> {
            BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return Minecraft.getInstance().getBlockColors().getColor(state, null, null, i);
        }, ModBlocks.CHRISTMAS_TREE.get(), ModBlocks.WREATH.get());
    }

    public static void onRegisterParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.SHOWER_PARTICLE.get(),
                ShowerParticle.Provider::new);
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.BIRD_BATH.get(), BirdBathBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHOPPING_BOARD.get(), ChoppingBoardBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MODERN_KITCHEN_SINK.get(), ModernKitchenSinkBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SINK.get(), SinkBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TOILET.get(), ToiletBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.WATER_TANK.get(), WaterTankBlockEntityRenderer::new);
    }

}
