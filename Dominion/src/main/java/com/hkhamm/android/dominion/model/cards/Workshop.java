package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.states.WorkshopState;

public class Workshop extends Action {

    public Workshop() {
        super();
        this.name = "Workshop";
        this.cost = 3;
        this.description = "Gain a card costing up to $4.";

        this.cardState = new WorkshopState();
    }

    public void play(Player player) {
        notifyObservers(cardState);
    }
}