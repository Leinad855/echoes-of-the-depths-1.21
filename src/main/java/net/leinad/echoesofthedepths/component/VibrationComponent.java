package net.leinad.echoesofthedepths.component;

import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.entity.RespawnableComponent;

public interface VibrationComponent extends Component {
    int getCharge();
    void setCharge(int value);

    default void addCharge(int amount) {
        setCharge(getCharge() + amount);
    }

}
