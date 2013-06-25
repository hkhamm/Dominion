package com.hkhamm.android.dominion.model.cards;
import com.hkhamm.android.dominion.model.states.MineState;

public class Mine extends Action {

    public Mine() {
        super();
        this.name = "Mine";
        this.cost = 5;
        this.description = "Trash a Treasure card from your hand. " +
                "Gain a Treasure card costing up to $3 more; put it into your hand.";

        this.cardState = new MineState();
    }
}
