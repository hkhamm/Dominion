package com.hkhamm.android.dominion.model.cards;

public class Bureaucrat extends ActionAttack {

    public Bureaucrat() {
        super();
        this.name = "Bureaucrat";
        this.cost = 4;
        this.description = "Gain a silver card; put it on top of your deck. " +
                "Each other player reveals a Victory card from his hand and puts it on his deck " +
                "(or reveals a hand with no Victory cards).";
    }
}
