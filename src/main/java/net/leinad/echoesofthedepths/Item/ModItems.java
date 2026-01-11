package net.leinad.echoesofthedepths.Item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.Item.custom.ResoniteAxeItem;
import net.leinad.echoesofthedepths.Item.custom.ResonitePickaxeItem;
import net.leinad.echoesofthedepths.Item.custom.ResoniteShovelItem;
import net.leinad.echoesofthedepths.Item.custom.ResoniteSwordItem;
import net.leinad.echoesofthedepths.entity.ModEntities;
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
                    List.of(Identifier.ofVanilla("item/empty_slot_sword"), Identifier.ofVanilla("item/empty_slot_pickaxe"), Identifier.ofVanilla("item/empty_slot_axe"), Identifier.ofVanilla("item/empty_slot_shovel")),
                    List.of(Identifier.ofVanilla("item/empty_slot_lapis_lazuli")))
            );
    public static final Item RESONITE_SWORD = registerItem("resonite_sword",
            new ResoniteSwordItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.RESONITE, 3, -2.4f))));
    public static final Item RESONITE_PICKAXE = registerItem("resonite_pickaxe",
            new ResonitePickaxeItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.RESONITE, 1, -2.8f))));
    public static final Item RESONITE_AXE = registerItem("resonite_axe",
            new ResoniteAxeItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.RESONITE, 5, -3.0F))));
    public static final Item RESONITE_SHOVEL = registerItem("resonite_shovel",
            new ResoniteShovelItem(ModToolMaterials.RESONITE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.RESONITE, 1.5f, -3.0F))));

    public static final Item MANTIS_SPAWN_EGG = registerItem("mantis_spawn_egg",
            new SpawnEggItem(ModEntities.MANTIS, 0x9dc783, 0xbfaf5f, new Item.Settings()));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(EchoesOfTheDepths.MOD_ID, name), item);
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RESONITE);
        });
    }
}
