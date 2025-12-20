package net.leinad.echoesofthedepths.Item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.Item.custom.ResoniteSwordItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class ModItems {

    public static final Item RESONITE = registerItem("resonite", new Item(new Item.Settings()));

    public static final Item BONE_UPGRADE_TEMPLATE = registerItem("bone_upgrade_template",
            new SmithingTemplateItem(
                    Text.translatable(
                                    Util.createTranslationKey("item", Identifier.of(EchoesOfTheDepths.MOD_ID, "smithing_template.bone_upgrade.applies_to"))
                            )
                            .formatted(Formatting.BLUE),
                    Text.translatable(
                                    Util.createTranslationKey("item", Identifier.of(EchoesOfTheDepths.MOD_ID, "smithing_template.bone_upgrade.ingredients"))
                            )
                            .formatted(Formatting.BLUE),
                    Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(EchoesOfTheDepths.MOD_ID, "bone_upgrade")))
                            .formatted(Formatting.GRAY),
                    Text.translatable(
                            Util.createTranslationKey("item", Identifier.of(EchoesOfTheDepths.MOD_ID,"smithing_template.bone_upgrade.base_slot_description"))
                    ),
                    Text.translatable(
                            Util.createTranslationKey("item", Identifier.of(EchoesOfTheDepths.MOD_ID, "smithing_template.bone_upgrade.additions_slot_description"))
                    ),
                    List.of(Identifier.ofVanilla("item/empty_slot_sword"), Identifier.ofVanilla("item/empty_slot_pickaxe")),
                    List.of(Identifier.ofVanilla("item/empty_slot_lapis_lazuli")))
            );
    public static final Item RESONITE_SWORD = registerItem("resonite_sword",
            new ResoniteSwordItem(ModToolMaterials.RESONITE, new Item.Settings()
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
