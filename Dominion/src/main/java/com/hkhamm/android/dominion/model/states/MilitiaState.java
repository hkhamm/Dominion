package com.hkhamm.android.dominion.model.states;

import com.hkhamm.android.dominion.Game;
import com.hkhamm.android.dominion.model.Player;

import java.util.ArrayList;

public class MilitiaState extends CardState {

    public MilitiaState() {
        super();
    }

    public void execute(Player currentPlayer, ArrayList<Player> players, Game game) {
        currentPlayer.increaseTempBuyingPower(2);
        for (Player player : players) {
            if (player != currentPlayer) {
                while (player.getHand().size() > 3) {
                    game.print("A Militia has been played, you must discard down to 3.");
                    // TODO fix militiastate
                    //game.printHand();
                    //player.discardCard(game.getInput());
                }
            }
        }
    }
}