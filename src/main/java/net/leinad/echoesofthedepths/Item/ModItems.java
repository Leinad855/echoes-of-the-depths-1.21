package net.leinad.echoesofthedepths.Item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RESONITE = registerItem("resonite", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EchoesOfTheDepths.MOD_ID, name), item);
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RESONITE);
        });
    }
}
