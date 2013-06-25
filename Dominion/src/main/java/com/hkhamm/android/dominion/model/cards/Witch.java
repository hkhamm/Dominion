package com.hkhamm.android.dominion.model.cards;

public class Witch extends ActionAttack {

    public Witch() {
        super();
        this.name = "Witch";
        this.cost = 5;
        this.description = "+2 model.cards\n" +
                "Each other player gains a Curse card.";
    }
}
