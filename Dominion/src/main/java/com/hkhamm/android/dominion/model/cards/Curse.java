package com.hkhamm.android.dominion.model.cards;

public class Curse extends Victory {

    int victoryPoints;

    public Curse() {
        super();
        this.name = "Curse";
        this.cost = 0;
        this.victoryPoints = -1;
        this.description = "-1 victory point.";
    }
}