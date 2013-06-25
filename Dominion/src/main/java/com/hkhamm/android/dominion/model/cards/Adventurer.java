package com.hkhamm.android.dominion.model.cards;
import com.hkhamm.android.dominion.model.Player;

public class Adventurer extends Action {

    public Adventurer() {
        super();
        this.name = "Adventurer";
        this.cost = 6;
        this.description = "Reveal cards from your deck until you reveal 2 Treasure cards. " +
                "Put those Treasure cards in your hand and discard the other revealed cards.";
    }

    public void play(Player player) {
        int count = 0;
        for (Card card : player.getDeck()) {
            if (count == 2) {break;}
            if (card instanceof Treasure) {
                player.getHand().add(card);
                player.getDeck().remove(player.getDeck().indexOf(card));
                count++;
            }
            else {
                player.getDiscardPile().add(card);
                player.getDeck().remove(player.getDeck().indexOf(card));
            }
        }
    }
}