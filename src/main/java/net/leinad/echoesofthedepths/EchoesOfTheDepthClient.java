package net.leinad.echoesofthedepths;

import net.fabricmc.api.ClientModInitializer;
import net.leinad.echoesofthedepths.util.ModModelPredicates;

public class EchoesOfTheDepthClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
    }
}
