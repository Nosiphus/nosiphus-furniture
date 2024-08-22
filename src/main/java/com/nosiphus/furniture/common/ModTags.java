package com.nosiphus.furniture.common;

import com.nosiphus.furniture.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> BAR_STOOLS = tag("bar_stools");
        public static final TagKey<Block> BEDSIDE_CABINETS = tag("bedside_cabinets");
        public static final TagKey<Block> BIRD_BATHS = tag("bird_baths");
        public static final TagKey<Block> CABINETS = tag("cabinets");
        public static final TagKey<Block> CHAIRS = tag("chairs");
        public static final TagKey<Block> CHOPPING_BOARDS = tag("chopping_boards");
        public static final TagKey<Block> COFFEE_TABLES = tag("coffee_tables");
        public static final TagKey<Block> CUPS = tag("cups");
        public static final TagKey<Block> DESK_CABINETS = tag("desk_cabinets");
        public static final TagKey<Block> DESKS = tag("desks");
        public static final TagKey<Block> DOOR_BELLS = tag("door_bells");
        public static final TagKey<Block> INFLATABLE_CASTLES = tag("inflatable_castles");
        public static final TagKey<Block> KITCHEN_COUNTERS = tag("kitchen_counters");
        public static final TagKey<Block> KITCHEN_DRAWERS = tag("kitchen_drawers");
        public static final TagKey<Block> KITCHEN_SINKS = tag("kitchen_sinks");
        public static final TagKey<Block> LAMPS = tag("lamps");
        public static final TagKey<Block> MODERN_BEDSIDE_CABINETS = tag("modern_bedside_cabinets");
        public static final TagKey<Block> MODERN_CABINETS = tag("modern_cabinets");
        public static final TagKey<Block> MODERN_CHAIRS = tag("modern_chairs");
        public static final TagKey<Block> MODERN_DESK_CABINETS = tag("modern_desk_cabinets");
        public static final TagKey<Block> MODERN_DESKS = tag("modern_desks");
        public static final TagKey<Block> MODERN_KITCHEN_COUNTERS = tag("modern_kitchen_counters");
        public static final TagKey<Block> MODERN_KITCHEN_DRAWERS = tag("modern_kitchen_drawers");
        public static final TagKey<Block> MODERN_SOFAS = tag("modern_sofas");
        public static final TagKey<Block> MODERN_TABLES = tag("modern_tables");
        public static final TagKey<Block> SOFAS = tag("sofas");
        public static final TagKey<Block> TABLES = tag("tables");
        public static final TagKey<Block> TELEVISION_STANDS = tag("television_stands");
        public static final TagKey<Block> WALL_CABINETS = tag("wall_cabinets");
        public static final TagKey<Block> WATER_TANKS = tag("water_tanks");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(Reference.MOD_ID, name));
        }
    }


    public static class Items
    {
        public static final TagKey<Item> BAR_STOOLS = tag("bar_stools");
        public static final TagKey<Item> BEDSIDE_CABINETS = tag("bedside_cabinets");
        public static final TagKey<Item> BIRD_BATHS = tag("bird_baths");
        public static final TagKey<Item> CABINETS = tag("cabinets");
        public static final TagKey<Item> CHAIRS = tag("chairs");
        public static final TagKey<Item> CHOPPING_BOARDS = tag("chopping_boards");
        public static final TagKey<Item> COFFEE_TABLES = tag("coffee_tables");
        public static final TagKey<Item> CUPS = tag("cups");
        public static final TagKey<Item> DESK_CABINETS = tag("desk_cabinets");
        public static final TagKey<Item> DESKS = tag("desks");
        public static final TagKey<Item> DOOR_BELLS = tag("door_bells");
        public static final TagKey<Item> INFLATABLE_CASTLES = tag("inflatable_castles");
        public static final TagKey<Item> KITCHEN_COUNTERS = tag("kitchen_counters");
        public static final TagKey<Item> KITCHEN_DRAWERS = tag("kitchen_drawers");
        public static final TagKey<Item> KITCHEN_SINKS = tag("kitchen_sinks");
        public static final TagKey<Item> LAMPS = tag("lamps");
        public static final TagKey<Item> MODERN_BEDSIDE_CABINETS = tag("modern_bedside_cabinets");
        public static final TagKey<Item> MODERN_CABINETS = tag("modern_cabinets");
        public static final TagKey<Item> MODERN_CHAIRS = tag("modern_chairs");
        public static final TagKey<Item> MODERN_DESK_CABINETS = tag("modern_desk_cabinets");
        public static final TagKey<Item> MODERN_DESKS = tag("modern_desks");
        public static final TagKey<Item> MODERN_KITCHEN_COUNTERS = tag("modern_kitchen_counters");
        public static final TagKey<Item> MODERN_KITCHEN_DRAWERS = tag("modern_kitchen_drawers");
        public static final TagKey<Item> MODERN_SOFAS = tag("modern_sofas");
        public static final TagKey<Item> MODERN_TABLES = tag("modern_tables");
        public static final TagKey<Item> SOFAS = tag("sofas");
        public static final TagKey<Item> TABLES = tag("tables");
        public static final TagKey<Item> TELEVISION_STANDS = tag("television_stands");
        public static final TagKey<Item> WALL_CABINETS = tag("wall_cabinets");
        public static final TagKey<Item> WATER_TANKS = tag("water_tanks");

        public static final TagKey<Item> GENERAL = tag("general");
        public static final TagKey<Item> STORAGE = tag("storage");
        public static final TagKey<Item> BEDROOM = tag("bedroom");
        public static final TagKey<Item> OUTDOORS = tag("outdoors");
        public static final TagKey<Item> KITCHEN = tag("kitchen");
        public static final TagKey<Item> BATHROOM = tag("bathroom");
        public static final TagKey<Item> ELECTRONICS = tag("electronics");
        public static final TagKey<Item> FESTIVE = tag("festive");
        public static final TagKey<Item> LIGHTING = tag("lighting");
        public static final TagKey<Item> ITEMS = tag("items");

        private static TagKey<Item> tag(String name)
        {
            return TagKey.create(Registries.ITEM, new ResourceLocation(Reference.MOD_ID, name));
        }
    }
}
