package net.leinad.echoesofthedepths.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RESONITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SCULK_FUNGUS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RESONITE_CRYSTAL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RESONITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BONE_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RESONITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MANTIS_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
