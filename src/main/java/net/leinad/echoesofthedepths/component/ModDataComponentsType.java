package net.leinad.echoesofthedepths.component;

import com.mojang.serialization.Codec;
import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


import java.util.function.UnaryOperator;

public class ModDataComponentsType {

    public static final ComponentType<Integer> CHARGE = register("charge", builder -> builder.codec(Codec.INT));

    public static final ComponentType<Long> COOLDOWN_USE = register("cooldown_use", builder -> builder.codec(Codec.LONG));

    public static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(EchoesOfTheDepths.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
    }
}
