package net.leinad.echoesofthedepths.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

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


    }
}
