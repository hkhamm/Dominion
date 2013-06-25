package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;
import com.hkhamm.android.dominion.model.CardObservable;
import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.states.CardState;

public class Card extends CardObservable {

    protected String name;
    protected int cost;
    protected int value;
    protected int victoryPoints;
    protected String description;
    protected CardState cardState;
    protected int drawable;

    public Card() {
        this.name = "Name";
        this.cost = 0;
        this.value = 0;
        this.victoryPoints = 0;
        this.description = "This is a card.";

        this.cardState = new CardState();
        this.drawable = R.drawable.action;
    }

    public void play(Player player) {
        notifyObservers(cardState);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getValue() {
        return value;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getDescription() {
        return description;
    }

    public int getDrawable() {
        return drawable;
    }
}