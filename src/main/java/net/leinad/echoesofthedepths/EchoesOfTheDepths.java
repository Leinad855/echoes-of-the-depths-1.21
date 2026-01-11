package net.leinad.echoesofthedepths;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.leinad.echoesofthedepths.Item.ModItemGroups;
import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.leinad.echoesofthedepths.entity.ModEntities;
import net.leinad.echoesofthedepths.entity.custom.MantisEntity;
import net.leinad.echoesofthedepths.util.ResoniteAxeAbilityEvent;
import net.leinad.echoesofthedepths.util.ResonitePickaxeAbilityEvent;
import net.leinad.echoesofthedepths.util.ResoniteShovelAbilityEvent;
import net.leinad.echoesofthedepths.world.ModFeatures;
import net.leinad.echoesofthedepths.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoesOfTheDepths implements ModInitializer {
	public static final String MOD_ID = "echoes-of-the-depths";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFeatures.register();
		ModEntities.registerModEntities();

		ModDataComponentsType.registerDataComponentTypes();

		PlayerBlockBreakEvents.BEFORE.register(new ResonitePickaxeAbilityEvent());
		PlayerBlockBreakEvents.BEFORE.register(new ResoniteAxeAbilityEvent());
		PlayerBlockBreakEvents.BEFORE.register(new ResoniteShovelAbilityEvent());

		ModWorldGeneration.generateModWorldGen();

		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
	}


}