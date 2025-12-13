package net.leinad.echoesofthedepths.Item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RESONITE = registerItem("resonite", new Item(new Item.Settings()));

    public static final Item RESONITE_SWORD = registerItem("resonite_sword",
            new SwordItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RESONITE, 3, -2.4f))));
    public static final Item RESONITE_PICKAXE = registerItem("resonite_pickaxe",
            new PickaxeItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RESONITE, 1, -2.8f))));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EchoesOfTheDepths.MOD_ID, name), item);
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RESONITE);
        });
    }
}
