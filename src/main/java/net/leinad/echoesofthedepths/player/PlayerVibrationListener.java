package net.leinad.echoesofthedepths.player;

import net.leinad.echoesofthedepths.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.GameEventTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.EntityPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.listener.GameEventListener;

public class PlayerVibrationListener implements GameEventListener {

    private final World world;

    public PlayerVibrationListener(World world) {
        this.world = world;
    }

    @Override
    public PositionSource getPositionSource() {
        return new EntityPositionSource(null, 0);
    }

    @Override
    public int getRange() {
        return 16;
    }

    @Override
    public boolean listen(ServerWorld world, RegistryEntry<GameEvent> event, GameEvent.Emitter emitter, Vec3d emitterPos) {
        if (!event.isIn(GameEventTags.VIBRATIONS)) return false;

        for (PlayerEntity player : world.getPlayers()) {
            if (player.getPos().distanceTo(emitterPos) <= 16) {
                ModComponents.VIBRATION.get(player).addCharge(2);
                ModComponents.VIBRATION.sync(player);
            }
        }

        return true;
    }
}
