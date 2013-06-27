package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.Game;
import com.hkhamm.android.dominion.model.Player;

import java.util.ArrayList;

public class CellarState extends CardState {

    public CellarState() {
        super();
    }

    public void execute(Player player, ArrayList<Player> players, Game game) {
        player.setActions(player.getActions() + 1);
        int num = 0;
        for (int i = 0; i < num; ++i) {
            //player.discardCard(game.getInput());
        }
        for (int i = 0; i < num; ++i) {
            player.drawCards(num);
        }
        player.setBuyingPower();
    }
}
