package net.leinad.echoesofthedepths.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block RESONITE_ORE = registerBlock("resonite_ore",
            new Block(AbstractBlock.Settings.create().strength(4.5F).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).luminance(state -> 6)));
    public static final Block BONE_STONE = registerBlock("bone_stone",
            new Block(AbstractBlock.Settings.create().strength(4.5f).requiresTool().sounds(BlockSoundGroup.BONE)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(EchoesOfTheDepths.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(EchoesOfTheDepths.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(RESONITE_ORE);
            entries.add(BONE_STONE);
        });
    }
}
