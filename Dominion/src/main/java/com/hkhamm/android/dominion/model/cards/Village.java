package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.states.VillageState;

public class Village extends Action {

    public Village() {
        super();
        this.name = "Village";
        this.cost = 3;
        this.description = "+1 Card; +2 Actions.";

        this.cardState = new VillageState();
    }

    public void play(Player player) {
        notifyObservers(cardState);
    }
}
