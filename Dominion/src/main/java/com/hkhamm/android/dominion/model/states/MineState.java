package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.controller.Game;
import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.cards.*;

import java.util.ArrayList;

public class MineState extends CardState {

    public MineState() {
        super();
    }

    public void execute(Player player, ArrayList<Player> players, Game game) {
        //game.printHand();
        //Card card = game.chooseTreasure();
        Card card = player.getHand().get(0);
        if (card instanceof Copper) {
            game.getSupply().getCardList().get(4).drawCard();
        }
        if (card instanceof Silver) {
            game.getSupply().getCardList().get(5).drawCard();
        }

        player.getHand().remove(card);
        player.setBuyingPower();
    }
}
