package net.leinad.echoesofthedepths.component;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class VibrationComponentImpl implements VibrationComponent, AutoSyncedComponent{

    private int charge = 0;

    @Override
    public int getCharge() {
        return charge;
    }

    @Override
    public void setCharge(int value) {
        this.charge = Math.max(0, value);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        charge = tag.getInt("Charge");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Charge", charge);
    }

}
