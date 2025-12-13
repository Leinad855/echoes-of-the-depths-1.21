package net.leinad.echoesofthedepths.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.leinad.echoesofthedepths.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlocksTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlocksTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.RESONITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.RESONITE_ORE);

        getOrCreateTagBuilder(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                .add(ModBlocks.RESONITE_ORE);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_RESONITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
    }
}
