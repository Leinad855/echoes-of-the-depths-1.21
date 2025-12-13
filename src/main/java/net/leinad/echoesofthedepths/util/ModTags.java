package net.leinad.echoesofthedepths.util;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_RESONITE_TOOL = createTag("needs_resonite_tool");
        public static final TagKey<Block> INCORRECT_FOR_RESONITE_TOOL = createTag("incorrect_for_resonite_tool");

        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
        }
    }

    public static class Items {

        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
        }
    }
}
