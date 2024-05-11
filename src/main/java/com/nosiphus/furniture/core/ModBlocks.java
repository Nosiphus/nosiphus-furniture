package com.nosiphus.furniture.core;

import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.item.BlockSupplierItem;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NosiphusFurnitureMod.MOD_ID);

    //Tables
    public static final RegistryObject<Block> TABLE_STONE = register("stone_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> TABLE_ANDESITE = register("andesite_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> TABLE_DIORITE = register("diorite_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> TABLE_GRANITE = register("granite_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> TABLE_BLACKSTONE = register("blackstone_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> TABLE_DEEPSLATE = register("deepslate_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> TABLE_WHITE_MODERN = register("white_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> TABLE_ORANGE_MODERN = register("orange_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> TABLE_MAGENTA_MODERN = register("magenta_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> TABLE_LIGHT_BLUE_MODERN = register("light_blue_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> TABLE_YELLOW_MODERN = register("yellow_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> TABLE_LIME_MODERN = register("lime_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> TABLE_PINK_MODERN = register("pink_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> TABLE_GRAY_MODERN = register("gray_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> TABLE_LIGHT_GRAY_MODERN = register("light_gray_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> TABLE_CYAN_MODERN = register("cyan_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> TABLE_PURPLE_MODERN = register("purple_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> TABLE_BLUE_MODERN = register("blue_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> TABLE_BROWN_MODERN = register("brown_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> TABLE_GREEN_MODERN = register("green_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> TABLE_RED_MODERN = register("red_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> TABLE_BLACK_MODERN = register("black_modern_table",
            () -> new ModernTableBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Chairs
    public static final RegistryObject<Block> CHAIR_STONE = register("stone_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> CHAIR_ANDESITE = register("andesite_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> CHAIR_DIORITE = register("diorite_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> CHAIR_GRANITE = register("granite_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> CHAIR_BLACKSTONE = register("blackstone_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> CHAIR_DEEPSLATE = register("deepslate_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> CHAIR_WHITE_MODERN = register("white_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_ORANGE_MODERN = register("orange_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_MAGENTA_MODERN = register("magenta_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIGHT_BLUE_MODERN = register("light_blue_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_YELLOW_MODERN = register("yellow_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIME_MODERN = register("lime_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_PINK_MODERN = register("pink_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_GRAY_MODERN = register("gray_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIGHT_GRAY_MODERN = register("light_gray_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_CYAN_MODERN = register("cyan_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_PURPLE_MODERN = register("purple_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BLUE_MODERN = register("blue_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BROWN_MODERN = register("brown_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_GREEN_MODERN = register("green_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_RED_MODERN = register("red_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BLACK_MODERN = register("black_modern_chair",
            () -> new ModernChairBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Office Chairs
    public static final RegistryObject<Block> CHAIR_WHITE_OFFICE = register("white_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_ORANGE_OFFICE = register("orange_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_MAGENTA_OFFICE = register("magenta_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIGHT_BLUE_OFFICE = register("light_blue_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_YELLOW_OFFICE = register("yellow_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIME_OFFICE = register("lime_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_PINK_OFFICE = register("pink_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_GRAY_OFFICE = register("gray_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_LIGHT_GRAY_OFFICE = register("light_gray_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_CYAN_OFFICE = register("cyan_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_PURPLE_OFFICE = register("purple_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BLUE_OFFICE = register("blue_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BROWN_OFFICE = register("brown_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_GREEN_OFFICE = register("green_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_RED_OFFICE = register("red_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> CHAIR_BLACK_OFFICE = register("black_office_chair",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Coffee Tables
    public static final RegistryObject<Block> COFFEE_TABLE_STONE = register("stone_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> COFFEE_TABLE_ANDESITE = register("andesite_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> COFFEE_TABLE_DIORITE = register("diorite_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> COFFEE_TABLE_GRANITE = register("granite_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> COFFEE_TABLE_BLACKSTONE = register("blackstone_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> COFFEE_TABLE_DEEPSLATE = register("deepslate_coffee_table",
            () -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> COFFEE_TABLE_WHITE_MODERN = register("white_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_ORANGE_MODERN = register("orange_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_MAGENTA_MODERN = register("magenta_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_LIGHT_BLUE_MODERN = register("light_blue_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_YELLOW_MODERN = register("yellow_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_LIME_MODERN = register("lime_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_PINK_MODERN = register("pink_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_GRAY_MODERN = register("gray_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_LIGHT_GRAY_MODERN = register("light_gray_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_CYAN_MODERN = register("cyan_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_PURPLE_MODERN = register("purple_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_BLUE_MODERN = register("blue_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_BROWN_MODERN = register("brown_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_GREEN_MODERN = register("green_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_RED_MODERN = register("red_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> COFFEE_TABLE_BLACK_MODERN = register("black_modern_coffee_table",
            () -> new ModernCoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Cabinets
    public static final RegistryObject<Block> CABINET_STONE = register("stone_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> CABINET_ANDESITE = register("andesite_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> CABINET_DIORITE = register("diorite_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> CABINET_GRANITE = register("granite_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> CABINET_BLACKSTONE = register("blackstone_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> CABINET_DEEPSLATE = register("deepslate_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> CABINET_WHITE_MODERN = register("white_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CABINET_ORANGE_MODERN = register("orange_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> CABINET_MAGENTA_MODERN = register("magenta_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> CABINET_LIGHT_BLUE_MODERN = register("light_blue_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> CABINET_YELLOW_MODERN = register("yellow_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> CABINET_LIME_MODERN = register("lime_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> CABINET_PINK_MODERN = register("pink_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> CABINET_GRAY_MODERN = register("gray_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> CABINET_LIGHT_GRAY_MODERN = register("light_gray_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> CABINET_CYAN_MODERN = register("cyan_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> CABINET_PURPLE_MODERN = register("purple_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> CABINET_BLUE_MODERN = register("blue_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> CABINET_BROWN_MODERN = register("brown_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> CABINET_GREEN_MODERN = register("green_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> CABINET_RED_MODERN = register("red_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> CABINET_BLACK_MODERN = register("black_modern_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Bedside Cabinets
    public static final RegistryObject<Block> BEDSIDE_CABINET_STONE = register("stone_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_ANDESITE = register("andesite_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_DIORITE = register("diorite_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_GRANITE = register("granite_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_BLACKSTONE = register("blackstone_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_DEEPSLATE = register("deepslate_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_WHITE_MODERN = register("white_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_ORANGE_MODERN = register("orange_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_MAGENTA_MODERN = register("magenta_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_LIGHT_BLUE_MODERN = register("light_blue_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_YELLOW_MODERN = register("yellow_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_LIME_MODERN = register("lime_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_PINK_MODERN = register("pink_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_GRAY_MODERN = register("gray_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_LIGHT_GRAY_MODERN = register("light_gray_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_CYAN_MODERN = register("cyan_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_PURPLE_MODERN = register("purple_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_BLUE_MODERN = register("blue_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_BROWN_MODERN = register("brown_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_GREEN_MODERN = register("green_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_RED_MODERN = register("red_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> BEDSIDE_CABINET_BLACK_MODERN = register("black_modern_bedside_cabinet",
            () -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Desk
    public static final RegistryObject<Block> DESK_STONE = register("stone_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.STONE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_ANDESITE = register("andesite_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE), DeskBlock.MaterialType.ANDESITE));
    public static final RegistryObject<Block> DESK_DIORITE = register("diorite_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE), DeskBlock.MaterialType.DIORITE));
    public static final RegistryObject<Block> DESK_GRANITE = register("granite_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE), DeskBlock.MaterialType.GRANITE));
    public static final RegistryObject<Block> DESK_BLACKSTONE = register("blackstone_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_DEEPSLATE = register("deepslate_desk",
            () -> new DeskBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_WHITE_MODERN = register("white_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE), ModernDeskBlock.MaterialType.WHITE_CONCRETE));
    public static final RegistryObject<Block> DESK_ORANGE_MODERN = register("orange_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE), ModernDeskBlock.MaterialType.ORANGE_CONCRETE));
    public static final RegistryObject<Block> DESK_MAGENTA_MODERN = register("magenta_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE), ModernDeskBlock.MaterialType.MAGENTA_CONCRETE));
    public static final RegistryObject<Block> DESK_LIGHT_BLUE_MODERN = register("light_blue_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE), ModernDeskBlock.MaterialType.LIGHT_BLUE_CONCRETE));
    public static final RegistryObject<Block> DESK_YELLOW_MODERN = register("yellow_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE), ModernDeskBlock.MaterialType.YELLOW_CONCRETE));
    public static final RegistryObject<Block> DESK_LIME_MODERN = register("lime_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE), ModernDeskBlock.MaterialType.LIME_CONCRETE));
    public static final RegistryObject<Block> DESK_PINK_MODERN = register("pink_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE), ModernDeskBlock.MaterialType.PINK_CONCRETE));
    public static final RegistryObject<Block> DESK_GRAY_MODERN = register("gray_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE), ModernDeskBlock.MaterialType.GRAY_CONCRETE));
    public static final RegistryObject<Block> DESK_LIGHT_GRAY_MODERN = register("light_gray_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE), ModernDeskBlock.MaterialType.LIGHT_GRAY_CONCRETE));
    public static final RegistryObject<Block> DESK_CYAN_MODERN = register("cyan_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE), ModernDeskBlock.MaterialType.CYAN_CONCRETE));
    public static final RegistryObject<Block> DESK_PURPLE_MODERN = register("purple_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE), ModernDeskBlock.MaterialType.PURPLE_CONCRETE));
    public static final RegistryObject<Block> DESK_BLUE_MODERN = register("blue_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE), ModernDeskBlock.MaterialType.BLUE_CONCRETE));
    public static final RegistryObject<Block> DESK_BROWN_MODERN = register("brown_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE), ModernDeskBlock.MaterialType.BROWN_CONCRETE));
    public static final RegistryObject<Block> DESK_GREEN_MODERN = register("green_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE), ModernDeskBlock.MaterialType.GREEN_CONCRETE));
    public static final RegistryObject<Block> DESK_RED_MODERN = register("red_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE), ModernDeskBlock.MaterialType.RED_CONCRETE));
    public static final RegistryObject<Block> DESK_BLACK_MODERN = register("black_modern_desk",
            () -> new ModernDeskBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE), ModernDeskBlock.MaterialType.BLACK_CONCRETE));

    //Desk Cabinets
    public static final RegistryObject<Block> DESK_CABINET_STONE = register("stone_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.STONE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_CABINET_ANDESITE = register("andesite_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE), DeskBlock.MaterialType.ANDESITE));
    public static final RegistryObject<Block> DESK_CABINET_DIORITE = register("diorite_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE), DeskBlock.MaterialType.DIORITE));
    public static final RegistryObject<Block> DESK_CABINET_GRANITE = register("granite_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE), DeskBlock.MaterialType.GRANITE));
    public static final RegistryObject<Block> DESK_CABINET_BLACKSTONE = register("blackstone_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_CABINET_DEEPSLATE = register("deepslate_desk_cabinet",
            () -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), DeskBlock.MaterialType.STONE));
    public static final RegistryObject<Block> DESK_CABINET_WHITE_MODERN = register("white_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE), ModernDeskBlock.MaterialType.WHITE_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_ORANGE_MODERN = register("orange_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE), ModernDeskBlock.MaterialType.ORANGE_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_MAGENTA_MODERN = register("magenta_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE), ModernDeskBlock.MaterialType.MAGENTA_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_LIGHT_BLUE_MODERN = register("light_blue_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE), ModernDeskBlock.MaterialType.LIGHT_BLUE_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_YELLOW_MODERN = register("yellow_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE), ModernDeskBlock.MaterialType.YELLOW_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_LIME_MODERN = register("lime_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE), ModernDeskBlock.MaterialType.LIME_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_PINK_MODERN = register("pink_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE), ModernDeskBlock.MaterialType.PINK_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_GRAY_MODERN = register("gray_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE), ModernDeskBlock.MaterialType.GRAY_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_LIGHT_GRAY_MODERN = register("light_gray_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE), ModernDeskBlock.MaterialType.LIGHT_GRAY_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_CYAN_MODERN = register("cyan_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE), ModernDeskBlock.MaterialType.CYAN_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_PURPLE_MODERN = register("purple_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE), ModernDeskBlock.MaterialType.PURPLE_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_BLUE_MODERN = register("blue_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE), ModernDeskBlock.MaterialType.BLUE_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_BROWN_MODERN = register("brown_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE), ModernDeskBlock.MaterialType.BROWN_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_GREEN_MODERN = register("green_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE), ModernDeskBlock.MaterialType.GREEN_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_RED_MODERN = register("red_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE), ModernDeskBlock.MaterialType.RED_CONCRETE));
    public static final RegistryObject<Block> DESK_CABINET_BLACK_MODERN = register("black_modern_desk_cabinet",
            () -> new ModernDeskCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE), ModernDeskBlock.MaterialType.BLACK_CONCRETE));

    //Wall Cabinets
    public static final RegistryObject<Block> WALL_CABINET_WHITE = register("white_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_ORANGE = register("orange_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_MAGENTA = register("magenta_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_LIGHT_BLUE = register("light_blue_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_YELLOW = register("yellow_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_LIME = register("lime_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_PINK = register("pink_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_GRAY = register("gray_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_LIGHT_GRAY = register("light_gray_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_CYAN = register("cyan_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_PURPLE = register("purple_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_BLUE = register("blue_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_BROWN = register("brown_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_GREEN = register("green_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_RED = register("red_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> WALL_CABINET_BLACK = register("black_wall_cabinet",
            () -> new WallCabinetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Sofas
    public static final RegistryObject<Block> SOFA_WHITE_MODERN = register("white_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> SOFA_ORANGE_MODERN = register("orange_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> SOFA_MAGENTA_MODERN = register("magenta_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> SOFA_LIGHT_BLUE_MODERN = register("light_blue_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> SOFA_YELLOW_MODERN = register("yellow_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> SOFA_LIME_MODERN = register("lime_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> SOFA_PINK_MODERN = register("pink_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> SOFA_GRAY_MODERN = register("gray_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> SOFA_LIGHT_GRAY_MODERN = register("light_gray_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> SOFA_CYAN_MODERN = register("cyan_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> SOFA_PURPLE_MODERN = register("purple_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> SOFA_BLUE_MODERN = register("blue_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> SOFA_BROWN_MODERN = register("brown_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> SOFA_GREEN_MODERN = register("green_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> SOFA_RED_MODERN = register("red_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> SOFA_BLACK_MODERN = register("black_modern_sofa",
            () -> new ModernSofaBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Television Stands
    public static final RegistryObject<Block> TELEVISION_STAND_WHITE = register("white_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_ORANGE = register("orange_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_MAGENTA = register("magenta_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_LIGHT_BLUE = register("light_blue_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_YELLOW = register("yellow_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_LIME = register("lime_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_PINK = register("pink_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_GRAY = register("gray_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_LIGHT_GRAY = register("light_gray_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_CYAN = register("cyan_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_PURPLE = register("purple_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_BLUE = register("blue_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_BROWN = register("brown_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_GREEN = register("green_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_RED = register("red_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> TELEVISION_STAND_BLACK = register("black_television_stand",
            () -> new TelevisionStandBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Lamps
    public static final RegistryObject<Block> LAMP_WHITE = register("white_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_ORANGE = register("orange_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_MAGENTA = register("magenta_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_LIGHT_BLUE = register("light_blue_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_YELLOW = register("yellow_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_LIME = register("lime_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.LIME_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_PINK = register("pink_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.PINK_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_GRAY = register("gray_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_LIGHT_GRAY = register("light_gray_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_CYAN = register("cyan_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_PURPLE = register("purple_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_BLUE = register("blue_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_BROWN = register("brown_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_GREEN = register("green_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_RED = register("red_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL).lightLevel((getLightValueLit(15)))));
    public static final RegistryObject<Block> LAMP_BLACK = register("black_lamp",
            () -> new LampBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).lightLevel((getLightValueLit(15)))));

    //Kitchen Counters
    public static final RegistryObject<Block> KITCHEN_COUNTER_WHITE_MODERN = register("white_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_ORANGE_MODERN = register("orange_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_MAGENTA_MODERN = register("magenta_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_LIGHT_BLUE_MODERN = register("light_blue_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_YELLOW_MODERN = register("yellow_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_LIME_MODERN = register("lime_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_PINK_MODERN = register("pink_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_GRAY_MODERN = register("gray_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_LIGHT_GRAY_MODERN = register("light_gray_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_CYAN_MODERN = register("cyan_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_PURPLE_MODERN = register("purple_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_BLUE_MODERN = register("blue_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_BROWN_MODERN = register("brown_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_GREEN_MODERN = register("green_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_RED_MODERN = register("red_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_COUNTER_BLACK_MODERN = register("black_modern_kitchen_counter",
            () -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Kitchen Drawers
    public static final RegistryObject<Block> KITCHEN_DRAWER_WHITE_MODERN = register("white_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_ORANGE_MODERN = register("orange_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_MAGENTA_MODERN = register("magenta_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_LIGHT_BLUE_MODERN = register("light_blue_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_YELLOW_MODERN = register("yellow_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_LIME_MODERN = register("lime_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_PINK_MODERN = register("pink_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_GRAY_MODERN = register("gray_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_LIGHT_GRAY_MODERN = register("light_gray_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_CYAN_MODERN = register("cyan_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_PURPLE_MODERN = register("purple_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_BLUE_MODERN = register("blue_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_BROWN_MODERN = register("brown_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_GREEN_MODERN = register("green_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_RED_MODERN = register("red_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> KITCHEN_DRAWER_BLACK_MODERN = register("black_modern_kitchen_drawer",
            () -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Kitchen Sinks
    public static final RegistryObject<Block> KITCHEN_SINK_WHITE_MODERN = register("white_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_ORANGE_MODERN = register("orange_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_MAGENTA_MODERN = register("magenta_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_LIGHT_BLUE_MODERN = register("light_blue_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_YELLOW_MODERN = register("yellow_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_LIME_MODERN = register("lime_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_PINK_MODERN = register("pink_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_GRAY_MODERN = register("gray_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_LIGHT_GRAY_MODERN = register("light_gray_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_CYAN_MODERN = register("cyan_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_PURPLE_MODERN = register("purple_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_BLUE_MODERN = register("blue_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_BROWN_MODERN = register("brown_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_GREEN_MODERN = register("green_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_RED_MODERN = register("red_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE), false));
    public static final RegistryObject<Block> KITCHEN_SINK_BLACK_MODERN = register("black_modern_kitchen_sink",
            () -> new ModernKitchenSinkBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE), false));

    //Bar Stools
    public static final RegistryObject<Block> BAR_STOOL_WHITE = register("white_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_ORANGE = register("orange_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_MAGENTA = register("magenta_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_LIGHT_BLUE = register("light_blue_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_YELLOW = register("yellow_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_LIME = register("lime_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_PINK = register("pink_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_GRAY = register("gray_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_LIGHT_GRAY = register("light_gray_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_CYAN = register("cyan_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_PURPLE = register("purple_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_BLUE = register("blue_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_BROWN = register("brown_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_GREEN = register("green_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_RED = register("red_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> BAR_STOOL_BLACK = register("black_bar_stool",
            () -> new BarStoolBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Chopping Boards
    public static final RegistryObject<Block> CHOPPING_BOARD_OAK = register("oak_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_SPRUCE = register("spruce_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_BIRCH = register("birch_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_JUNGLE = register("jungle_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_ACACIA = register("acacia_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_DARK_OAK = register("dark_oak_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_CRIMSON = register("crimson_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_WARPED = register("warped_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_MANGROVE = register("mangrove_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)));
    public static final RegistryObject<Block> CHOPPING_BOARD_CHERRY = register("cherry_chopping_board",
            () -> new ChoppingBoardBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)));

    //Electronics
    public static final RegistryObject<Block> LAPTOP_WHITE = register("white_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_ORANGE = register("orange_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_MAGENTA = register("magenta_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_LIGHT_BLUE = register("light_blue_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_YELLOW = register("yellow_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_LIME = register("lime_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_PINK = register("pink_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_GRAY = register("gray_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_LIGHT_GRAY = register("light_gray_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_CYAN = register("cyan_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_PURPLE = register("purple_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_BLUE = register("blue_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_BROWN = register("brown_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_GREEN = register("green_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_RED = register("red_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> LAPTOP_BLACK = register("black_laptop",
            () -> new PlaceholderBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));

    //Door Bells
    public static final RegistryObject<Block> DOOR_BELL_OAK = register("oak_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_SPRUCE = register("spruce_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_BIRCH = register("birch_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_JUNGLE = register("jungle_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_ACACIA = register("acacia_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_DARK_OAK = register("dark_oak_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_CRIMSON = register("crimson_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_WARPED = register("warped_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_MANGROVE = register("mangrove_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_CHERRY = register("cherry_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_OAK = register("stripped_oak_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_SPRUCE = register("stripped_spruce_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_BIRCH = register("stripped_birch_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_JUNGLE = register("stripped_jungle_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_ACACIA = register("stripped_acacia_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_DARK_OAK = register("stripped_dark_oak_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_CRIMSON = register("stripped_crimson_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_WARPED = register("stripped_warped_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_MANGROVE = register("stripped_mangrove_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_WOOD).noOcclusion()));
    public static final RegistryObject<Block> DOOR_BELL_STRIPPED_CHERRY = register("stripped_cherry_door_bell",
            () -> new DoorBellBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_WOOD).noOcclusion()));

    //Inflatable Castles
    public static final RegistryObject<Block> INFLATABLE_CASTLE_WHITE = register("white_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_ORANGE = register("orange_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_MAGENTA = register("magenta_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_LIGHT_BLUE = register("light_blue_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_YELLOW = register("yellow_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_LIME = register("lime_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.LIME_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_PINK = register("pink_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.PINK_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_GRAY = register("gray_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_LIGHT_GRAY = register("light_gray_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_CYAN = register("cyan_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_PURPLE = register("purple_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_BLUE = register("blue_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_BROWN = register("brown_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_GREEN = register("green_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_RED = register("red_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistryObject<Block> INFLATABLE_CASTLE_BLACK = register("black_inflatable_castle",
            () -> new InflatableCastleBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistryObject<Block> CASTLE_NETTING = register("castle_netting",
            () -> new IronBarsBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    //Cups
    public static final RegistryObject<Block> CUP_WHITE = register("white_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_ORANGE = register("orange_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_MAGENTA = register("magenta_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_LIGHT_BLUE = register("light_blue_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_YELLOW = register("yellow_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_LIME = register("lime_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.LIME_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_PINK = register("pink_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.PINK_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_GRAY = register("gray_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_LIGHT_GRAY = register("light_gray_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_CYAN = register("cyan_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_PURPLE = register("purple_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_BLUE = register("blue_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_BROWN = register("brown_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_GREEN = register("green_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_RED = register("red_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS)));
    public static final RegistryObject<Block> CUP_BLACK = register("black_cup",
            () -> new CupBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_STAINED_GLASS)));

    //Bird Baths
    public static final RegistryObject<Block> BIRD_BATH_STONE = register("stone_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> BIRD_BATH_ANDESITE = register("andesite_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> BIRD_BATH_DIORITE = register("diorite_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)));
    public static final RegistryObject<Block> BIRD_BATH_GRANITE = register("granite_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)));
    public static final RegistryObject<Block> BIRD_BATH_BLACKSTONE = register("blackstone_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<Block> BIRD_BATH_DEEPSLATE = register("deepslate_bird_bath",
            () -> new BirdBathBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)));

    //Water Tanks
    public static final RegistryObject<Block> OAK_WATER_TANK = register("oak_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> SPRUCE_WATER_TANK = register("spruce_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> BIRCH_WATER_TANK = register("birch_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> JUNGLE_WATER_TANK = register("jungle_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> ACACIA_WATER_TANK = register("acacia_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> DARK_OAK_WATER_TANK = register("dark_oak_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> CRIMSON_WATER_TANK = register("crimson_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> WARPED_WATER_TANK = register("warped_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> MANGROVE_WATER_TANK = register("mangrove_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> CHERRY_WATER_TANK = register("cherry_water_tank",
            () -> new WaterTankBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));

    //Appliances
    public static final RegistryObject<Block> BIN_LIGHT = register("bin_light",
            () -> new BinBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> BIN_DARK = register("bin_dark",
            () -> new BinBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> BLENDER_LIGHT = register("blender_light",
            () -> new BlenderBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> BLENDER_DARK = register("blender_dark",
            () -> new BlenderBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> DISHWASHER_LIGHT = register("dishwasher_light",
            () -> new DishwasherBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> DISHWASHER_DARK = register("dishwasher_dark",
            () -> new DishwasherBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> MICROWAVE_LIGHT = register("microwave_light",
            () -> new MicrowaveBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> MICROWAVE_DARK = register("microwave_dark",
            () -> new MicrowaveBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> OVEN_LIGHT = register("oven_light",
            () -> new OvenBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> OVEN_DARK = register("oven_dark",
            () -> new OvenBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> OVEN_RANGE_HOOD_LIGHT = register("oven_range_hood_light",
            () -> new OvenRangeHoodBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).lightLevel((getLightValueLit(14))).noOcclusion()));
    public static final RegistryObject<Block> OVEN_RANGE_HOOD_DARK = register("oven_range_hood_dark",
            () -> new OvenRangeHoodBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).lightLevel((getLightValueLit(14))).noOcclusion()));
    public static final RegistryObject<Block> SINK_LIGHT = register("sink_light",
            () -> new SinkBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> SINK_DARK = register("sink_dark",
            () -> new SinkBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> SHOWER_LIGHT_BOTTOM = registerNoItem("shower_light_bottom",
            () -> new ShowerBottomBlock(Block.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion(), () -> ModBlocks.SHOWER_LIGHT));
    public static final RegistryObject<Block> SHOWER_LIGHT = register("shower_light",
            () -> new ShowerTopBlock(Block.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion(), () -> ModBlocks.SHOWER_LIGHT_BOTTOM), block -> new BlockSupplierItem(new Item.Properties(), block.get(), ModBlocks.SHOWER_LIGHT_BOTTOM));
    public static final RegistryObject<Block> SHOWER_DARK_BOTTOM = registerNoItem("shower_dark_bottom",
            () -> new ShowerBottomBlock(Block.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion(), () -> ModBlocks.SHOWER_DARK));
    public static final RegistryObject<Block> SHOWER_DARK = register("shower_dark",
            () -> new ShowerTopBlock(Block.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion(), () -> ModBlocks.SHOWER_DARK_BOTTOM), block -> new BlockSupplierItem(new Item.Properties(), block.get(), ModBlocks.SHOWER_DARK_BOTTOM));
    public static final RegistryObject<Block> SHOWER_HEAD_LIGHT = register("shower_head_light",
            () -> new ShowerHeadBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> SHOWER_HEAD_DARK = register("shower_head_dark",
            () -> new ShowerHeadBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> TOASTER_LIGHT = register("toaster_light",
            () -> new ToasterBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> TOASTER_DARK = register("toaster_dark",
            () -> new ToasterBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> TOILET_LIGHT = register("toilet_light",
            () -> new ToiletBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> TOILET_DARK = register("toilet_dark",
            () -> new ToiletBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> WASHING_MACHINE_LIGHT = register("washing_machine_light",
            () -> new WashingMachineBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).noOcclusion()));
    public static final RegistryObject<Block> WASHING_MACHINE_DARK = register("washing_machine_dark",
            () -> new WashingMachineBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE).noOcclusion()));

    //Festive
    public static final RegistryObject<Block> CANDLE = register("candle",
            () -> new CandleBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE).lightLevel((getLightValueLit(13))).noOcclusion()));
    public static final RegistryObject<Block> CHRISTMAS_TREE_BOTTOM = registerNoItem("christmas_tree_bottom",
            () -> new ChristmasTreeBottomBlock(Block.Properties.copy(Blocks.DARK_OAK_LEAVES).noOcclusion(), () -> ModBlocks.CHRISTMAS_TREE));
    public static final RegistryObject<Block> CHRISTMAS_TREE = register("christmas_tree",
            () -> new ChristmasTreeTopBlock(Block.Properties.copy(Blocks.DARK_OAK_LEAVES).noOcclusion(), () -> ModBlocks.CHRISTMAS_TREE_BOTTOM), block -> new BlockSupplierItem(new Item.Properties(), block.get(), ModBlocks.CHRISTMAS_TREE_BOTTOM));
    public static final RegistryObject<Block> WREATH = register("wreath",
            () -> new WreathBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).noOcclusion()));

    //Fluid Blocks
    public static final RegistryObject<LiquidBlock> SOAPY_WATER = registerLiquid("soapy_water",
            () -> new LiquidBlock(ModFluids.SOAPY_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> SUPER_SOAPY_WATER = registerLiquid("super_soapy_water",
            () -> new LiquidBlock(ModFluids.SUPER_SOAPY_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));

    //Methods
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block)
    {
        return register(name, block, new Item.Properties());
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block, Item.Properties properties)
    {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), properties));
        return registryObject;
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block, @Nullable Function<RegistryObject<Block>, BlockItem> function)
    {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        if(function != null)
        {
            ModItems.ITEMS.register(name, () -> function.apply(registryObject));
        }
        return registryObject;
    }

    private static RegistryObject<Block> registerNoItem(String name, Supplier<Block> block)
    {
        return BLOCKS.register(name, block);
    }

    private static RegistryObject<LiquidBlock> registerLiquid(String name, Supplier<LiquidBlock> block)
    {
        return BLOCKS.register(name, block);
    }

}
