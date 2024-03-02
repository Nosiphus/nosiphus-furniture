package com.nosiphus.furniture.core;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "nfm");

    public static final RegistryObject<FlowingFluid> SOAPY_WATER = FLUIDS.register("soapy_water",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOAPY_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOAPY_WATER = FLUIDS.register("flowing_soapy_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAPY_WATER_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SOAPY_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SOAPY_WATER_FLUID_TYPE, SOAPY_WATER, FLOWING_SOAPY_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.SOAPY_WATER)
            .bucket(ModItems.SOAPY_WATER);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

}
