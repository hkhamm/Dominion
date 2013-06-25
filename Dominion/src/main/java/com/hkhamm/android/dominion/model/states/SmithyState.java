package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.controller.Game;
import com.hkhamm.android.dominion.model.Player;

import java.util.ArrayList;

public class SmithyState extends CardState {

    public SmithyState() {
        super();
    }

    public void execute(Player player, ArrayList<Player> players, Game game) {
        player.drawCards(3);
    }
}
