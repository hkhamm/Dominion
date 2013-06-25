package com.hkhamm.android.dominion.model.cards;
import com.hkhamm.android.dominion.model.states.MilitiaState;

public class Militia extends ActionAttack {

    public Militia() {
        super();
        this.name = "Militia";
        this.cost = 4;
        this.description = "+$2\n" +
                "Each other player discards down to 3 cards in his hand.";

        this.cardState = new MilitiaState();
    }
}
