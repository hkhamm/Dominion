package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.controller.Game;
import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.cards.Card;

import java.util.ArrayList;

public class RemodelState extends CardState {

    public RemodelState() {
        super();
    }

    public void execute(Player player, ArrayList<Player> players, Game game) {
        game.print("You must trash a card.");
        //game.printHand();
        //Card card = player.getHand().get(game.getInput());
        //int cost = card.getCost() + 2;
        game.print("Choose a card:");
        //game.printControlledSupply(cost);
        //player.getHand().add(game.getSupply().getCardList().get(game.getInput()).drawCard());
        //player.getHand().remove(card);
        player.setBuyingPower();
    }
}