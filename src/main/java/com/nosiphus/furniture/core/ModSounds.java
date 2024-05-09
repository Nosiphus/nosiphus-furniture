package com.nosiphus.furniture.core;

import com.nosiphus.furniture.NosiphusFurnitureMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NosiphusFurnitureMod.MOD_ID);

    public static final RegistryObject<SoundEvent> BLOCK_BIN_CLOSE = register("block.bin.close");
    public static final RegistryObject<SoundEvent> BLOCK_BIN_OPEN = register("block.bin.open");
    public static final RegistryObject<SoundEvent> BLOCK_CHOPPING_BOARD_KNIFE_CHOP = register("block.chopping_board.knife_chop");
    public static final RegistryObject<SoundEvent> BLOCK_DOOR_BELL_RING = register("block.door_bell.ring");
    public static final RegistryObject<SoundEvent> BLOCK_INFLATABLE_CASTLE_BOUNCE = register("block.inflatable_castle.bounce");
    public static final RegistryObject<SoundEvent> BLOCK_MICROWAVE_FINISH = register("block.microwave.finish");
    public static final RegistryObject<SoundEvent> BLOCK_MICROWAVE_RUNNING = register("block.microwave.running");
    public static final RegistryObject<SoundEvent> BLOCK_SHOWER_RUNNING = register("block.shower.running");
    public static final RegistryObject<SoundEvent> BLOCK_TOILET_FART = register("block.toilet.fart");
    public static final RegistryObject<SoundEvent> BLOCK_TOILET_FLUSH = register("block.toilet.flush");

    private static RegistryObject<SoundEvent> register(String name)
    {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation("nfm", name)));
    }

}
