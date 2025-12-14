package net.leinad.echoesofthedepths.component;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModComponents implements EntityComponentInitializer {

    public static final ComponentKey<VibrationComponent> VIBRATION =
            ComponentRegistry.getOrCreate(
                    Identifier.of(EchoesOfTheDepths.MOD_ID, "vibration"),
                    VibrationComponent.class
            );


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(
                VIBRATION,
                player -> new VibrationComponentImpl(),
                RespawnCopyStrategy.ALWAYS_COPY
        );
    }
}
