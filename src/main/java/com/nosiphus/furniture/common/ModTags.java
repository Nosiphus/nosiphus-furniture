package com.nosiphus.furniture.common;

import com.nosiphus.furniture.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags
{
    public static class Items
    {
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
