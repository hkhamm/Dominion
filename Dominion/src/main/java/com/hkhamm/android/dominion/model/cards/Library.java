package com.hkhamm.android.dominion.model.cards;

public class Library extends Action {

    public Library() {
        super();
        this.name = "Library";
        this.cost = 5;
        this.description = "Draw until you have 7 cards in hand. " +
                "You may set aside any Action cards drawn this way, " +
                "as you draw them; discard the set aside cards after you finish drawing.";
    }
}
