package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.Game;
import com.hkhamm.android.dominion.model.Player;

import java.util.ArrayList;

public class WorkshopState extends CardState {

    public WorkshopState() {
        super();
    }

    public void execute(Player player, ArrayList<Player> players, Game game) {
        game.print("Gain a card:");
        //game.printControlledSupply(4);
        //player.getHand().add(game.getSupply().getCardList().get(game.getInput()).drawCard());
    }
}
