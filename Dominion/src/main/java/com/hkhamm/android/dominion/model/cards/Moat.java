package com.hkhamm.android.dominion.model.cards;
import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.states.MoatState;

public class Moat extends ActionReaction {

    public Moat() {
        super();
        this.name = "Moat";
        this.cost = 2;
        this.description = "+2 cards\n" +
                "When another player plays an Attack card, you may reveal this from your hand. " +
                "If you do, you are unaffected by that Attack.";

        this.cardState = new MoatState();
    }

    public void play(Player player) {
        notifyObservers(cardState);
    }
}
