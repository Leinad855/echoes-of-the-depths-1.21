package net.leinad.echoesofthedepths.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RESONITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RESONITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BONE_UPGRADE_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.RESONITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RESONITE_PICKAXE, Models.HANDHELD);
    }
}
