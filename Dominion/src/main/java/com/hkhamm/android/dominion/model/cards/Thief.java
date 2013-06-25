package com.hkhamm.android.dominion.model.cards;

public class Thief extends ActionAttack {

    public Thief() {
        super();
        this.name = "Thief";
        this.cost = 4;
        this.description = "Each other player reveals the top 2 cards of his deck. " +
                "If they revealed any Treasure cards, they trash one of them that you choose. " +
                "You may gain any or all of these trashed cards. They discard the other revealed cards.";
    }
}
