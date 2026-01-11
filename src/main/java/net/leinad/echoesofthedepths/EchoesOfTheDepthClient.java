package net.leinad.echoesofthedepths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.leinad.echoesofthedepths.entity.ModEntities;
import net.leinad.echoesofthedepths.entity.client.MantisModel;
import net.leinad.echoesofthedepths.entity.client.MantisRender;
import net.leinad.echoesofthedepths.util.ModModelPredicates;

public class EchoesOfTheDepthClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRender::new);
    }
}
