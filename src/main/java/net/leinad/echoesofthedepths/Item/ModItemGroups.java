package net.leinad.echoesofthedepths.Item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ECHOES_OF_THE_DEPTHS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EchoesOfTheDepths.MOD_ID, "echoes-of-the-depths_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.RESONITE))
                    .displayName(Text.translatable("itemgroup.echoes-of-the-depths.echoes-of-the-depths_items"))
                    .entries((displayContext, entries) -> {
                      entries.add(ModItems.RESONITE);
                      entries.add(ModItems.RESONITE_SWORD);
                      entries.add(ModItems.RESONITE_PICKAXE);
                    })
                    .build());

    public static final ItemGroup ECHOES_OF_THE_DEPTHS_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EchoesOfTheDepths.MOD_ID, "echoes-of-the-depths_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.RESONITE_ORE))
                    .displayName(Text.translatable("itemgroup.echoes-of-the-depths.echoes-of-the-depths_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.RESONITE_ORE);
                    })
                    .build());


    public static void registerItemGroups() {

    }
}
