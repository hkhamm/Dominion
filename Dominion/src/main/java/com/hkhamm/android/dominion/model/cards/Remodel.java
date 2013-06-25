package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.states.RemodelState;

public class Remodel extends Action {

    public Remodel() {
        super();
        this.name = "Remodel";
        this.cost = 4;
        this.description = "Trash a card from your hand. Gain a card costing up to $2 more than the trashed card.";

        this.cardState = new RemodelState();
    }

    public void play(Player player) {
        notifyObservers(cardState);
    }
}
