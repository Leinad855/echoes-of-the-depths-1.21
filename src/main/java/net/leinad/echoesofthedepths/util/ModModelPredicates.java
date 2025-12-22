package net.leinad.echoesofthedepths.util;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.Item.custom.ResonitePickaxeItem;
import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.RESONITE_SWORD, Identifier.of(EchoesOfTheDepths.MOD_ID, "charged"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentsType.CHARGE) != null
                        && stack.get(ModDataComponentsType.CHARGE) == 10 ? 1f : 0f);

        ModelPredicateProviderRegistry.register(ModItems.RESONITE_PICKAXE, Identifier.of(EchoesOfTheDepths.MOD_ID, "charged"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentsType.CHARGE) != null
                        && stack.get(ModDataComponentsType.CHARGE) == ResonitePickaxeItem.MAX_CHARGE ? 1f : 0f);
    }
}
