package com.hkhamm.android.dominion.model;

import com.hkhamm.android.dominion.model.cards.Card;

import java.util.ArrayList;

public class SupplyPile {

    private ArrayList<Card> pile;

    public SupplyPile(Card card, int number) {
        pile = new ArrayList<Card>();
        for (int i = 0; i < number; ++i) {
            pile.add(card);
        }
    }

    public int getDrawable() {
        return getCard().getDrawable();
    }

    public ArrayList<Card> getPile() {
        return pile;
    }

    public Card getCard() {
        return pile.get(0);
    }

    public int getCost() {
        return pile.get(0).getCost();
    }

    public Card drawCard() {
        Card card = pile.get(0);
        pile.remove(pile.get(0));
        return card;
    }

    public int size() {
        return pile.size();
    }
}
