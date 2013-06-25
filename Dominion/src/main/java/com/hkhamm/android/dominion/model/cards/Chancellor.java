package com.hkhamm.android.dominion.model.cards;

public class Chancellor extends Action {

    public Chancellor() {
        super();
        this.name = "Chancellor";
        this.cost = 3;
        this.description = "+$2\n" +
                "You may immediately put your deck into your discard pile.";
    }
}
