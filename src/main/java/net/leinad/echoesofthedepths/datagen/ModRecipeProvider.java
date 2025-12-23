package net.leinad.echoesofthedepths.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> RESONITE_SMELTABLES = List.of(ModItems.RESONITE, ModBlocks.RESONITE_ORE);

        offerSmelting(exporter, RESONITE_SMELTABLES, RecipeCategory.MISC, ModItems.RESONITE, 0.25f, 200, "resonite");
        offerBlasting(exporter, RESONITE_SMELTABLES, RecipeCategory.MISC, ModItems.RESONITE, 0.25f, 100, "resonite");

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.BONE_UPGRADE_TEMPLATE),
                Ingredient.ofItems(Items.DIAMOND_SWORD),
                Ingredient.ofItems(ModItems.RESONITE),
                RecipeCategory.MISC,
                ModItems.RESONITE_SWORD)
                .criterion("has_bone_stone",
                        conditionsFromItem(ModItems.BONE_UPGRADE_TEMPLATE))
                .offerTo(exporter, Identifier.of(
                        EchoesOfTheDepths.MOD_ID,
                        "resonite_sword_smithing"
                ));

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.BONE_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.DIAMOND_PICKAXE),
                        Ingredient.ofItems(ModItems.RESONITE),
                        RecipeCategory.MISC,
                        ModItems.RESONITE_PICKAXE)
                .criterion("has_bone_stone",
                        conditionsFromItem(ModItems.BONE_UPGRADE_TEMPLATE))
                .offerTo(exporter, Identifier.of(
                        EchoesOfTheDepths.MOD_ID,
                        "resonite_pickaxe_smithing"
                ));

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.BONE_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(Items.DIAMOND_AXE),
                        Ingredient.ofItems(ModItems.RESONITE),
                        RecipeCategory.MISC,
                        ModItems.RESONITE_AXE)
                .criterion("has_bone_stone",
                        conditionsFromItem(ModItems.BONE_UPGRADE_TEMPLATE))
                .offerTo(exporter, Identifier.of(
                        EchoesOfTheDepths.MOD_ID,
                        "resonite_axe_smithing"
                ));
    }


}
