package com.hkhamm.android.dominion.model.cards;

public class Spy extends ActionAttack {

    public Spy() {
        super();
        this.name = "Spy";
        this.cost = 4;
        this.description = "+1 Card; +1 Action\n" +
                "Each player (including you) reveals the top card of his deck and either discards it or puts it back, your choice.";
    }
}
