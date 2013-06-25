package com.hkhamm.android.dominion.model.cards;

public class CouncilRoom extends Action {

    public CouncilRoom() {
        super();
        this.name = "Council Room";
        this.cost = 5;
        this.description = "+4 model.cards; +1 Buy\n" +
                "Each other player draws a card.";
    }
}
