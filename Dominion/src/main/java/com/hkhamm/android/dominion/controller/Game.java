package com.hkhamm.android.dominion.controller;

import android.widget.TextView;

import com.hkhamm.android.dominion.MainActivity;
import com.hkhamm.android.dominion.R;
import com.hkhamm.android.dominion.model.*;
import com.hkhamm.android.dominion.model.cards.*;
import com.hkhamm.android.dominion.model.states.*;

import java.util.*;

public class Game implements CardObserver {

    private MainActivity main;
    private TextView textView;

    private Supply supply;
    private ArrayList<Card> trash;
    private ArrayList<Player> turnOrder;
    private int emptySupply;
    private int numberOfPlayers;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private CardState cardState;
    private boolean buyFlag;

    public Game(MainActivity main) {
        this.main = main;
        textView = (TextView) main.findViewById(R.id.play_area);
        cardState = new CardState();
        numberOfPlayers = 2;
        supply = new Supply(numberOfPlayers);
        trash = new ArrayList<Card>();
        turnOrder = new ArrayList<Player>();
        currentPlayerIndex = 0;
        buyFlag = false;

        addAsObserver();
        getStartingDecks();
        playGame();
    }

    private void getStartingDecks() {
        for (int i = 1; i < numberOfPlayers + 1; ++i) {
            ArrayList<Card> deck = new ArrayList<Card>();

            for (int j = 0; j < 3; ++j) {
                deck.add(new Estate());
            }
            for (int k = 0; k < 7; ++k) {
                deck.add(new Copper());
            }

            shuffle(deck);

            turnOrder.add(new Player("Player " + i, deck));
        }

        for (Player player : turnOrder) {
            player.drawCards(5);
            setHandIds(player);
        }
    }

    private void setHandIds(Player player) {
        Integer[] handIds = new Integer[player.getHand().size()];

        for (int i = 0; i < player.getHand().size(); ++i) {
            handIds[i] = player.getHand().get(i).getDrawable();
        }

        main.setHandIds(handIds);
    }

    private void playGame() {
        while (supply.getProvinces() > 0) {
            currentPlayer = turnOrder.get(currentPlayerIndex);

            for (SupplyPile pile : supply.getCardList()) {
                if (pile.size() == 0) {
                    emptySupply += 1;
                }
            }

            if (emptySupply >= 3) {
                break;
            }
            else {
                emptySupply = 0;
            }

            print(currentPlayer.getName() + "'s Turn");

            currentPlayer.setBuyingPower();

            print("Action Phase: Play a card from your hand.");

            //choosePhase();

            // if (currentPlayerIndex == 0)  // one round test
            //     break;
        }
        Collections.sort(turnOrder, new PlayerComparator());
        endGame();
    }

    /*
    private void choosePhase() {
        int actions = currentPlayer.getActions();
        int buys = currentPlayer.getBuys();
        printPlayArea();
        printNiceHand();
        int phase = view.choosePhase(actions, buys);

        if (phase == 1) {
            playAction();
        }
        if (phase == 2) {
            buyCard();
        }
        if (phase == 3) {
            examineCard();
        }
        if (phase == 4) {
            endTurn();
        }

    }
    */

    private void playAction() { // TODO connect onClick with playing actions, buying, etc
        int cardIndex = 0;

        Card card = currentPlayer.getHand().get(cardIndex);
        if (card instanceof Action ||
                card instanceof ActionAttack ||
                card instanceof ActionReaction) {
            currentPlayer.playCard(card);
            currentPlayer.setActions(currentPlayer.getActions() - 1);
            //choosePhase();
        }
        else {
            print("You must choose an action, try again.");
            playAction();
        }
    }

    private void buyCard() {
        int supplyPile = 0;
        int cardValue = 0;
        String cardName = supply.getName(supplyPile);
        cardValue += supply.getCost(supplyPile);

        print("Choose a card from the supply:");

        if (cardValue <= currentPlayer.getBuyingPower()) {
            print("You purchased a " + cardName + ".");
            buyFlag = true;
            supply.purchaseCard(supplyPile, currentPlayer);
            currentPlayer.setBuys(currentPlayer.getBuys() - 1);

            for (int i = 0; i < currentPlayer.getHand().size(); ++i) {
                currentPlayer.getPlayArea().addAll(currentPlayer.getHand());
                currentPlayer.getHand().clear();
            }

            currentPlayer.increaseUsedBuyingPower(cardValue);
            currentPlayer.setBuyingPower();
        }
        else {
            print("You can't afford a " + cardName + ". Try again.");
        }
        //choosePhase();
    }

    private void examineCard() {
        int supplyPile;

        print("\nChoose a card to examine:");
        supplyPile = 0;

        Card card = supply.getCardList().get(supplyPile).getCard();
        print("Name: " + card.getName());
        print("Cost: " + card.getCost());
        print(card.getDescription());
    }

    private void endTurn() {
        buyFlag = false;
        print("End turn.");
        currentPlayer.cleanUp();
        if (currentPlayerIndex != numberOfPlayers - 1) {
            currentPlayerIndex++;
        }
        else {
            currentPlayerIndex = 0;
        }
    }

    public void endGame() {
        print("Game over.");

        int victoryPoints = 0;
        ArrayList<Player> victor = new ArrayList<Player>();

        for (Player player : turnOrder) {
            int playerPoints = player.getVictoryPoints();
            print(player.getName() + " has " + playerPoints + ".");
            if (playerPoints > victoryPoints) {
                victoryPoints = playerPoints;
                victor.add(player);
            }
            else if (playerPoints == victoryPoints) {
                victor.add(player);
            }
        }

        if (victor.size() < 1) {
            print(victor.get(0).getName() + " is the victor with " + victoryPoints + " victory points.");
        }
        else {
            print("It's a tie.");
            for (Player player : turnOrder) {
                print(player.getName() + " is a victor with " + victoryPoints + " victory points.");
            }
        }
    }

    public static void shuffle(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); ++i) {
            Random generator = new Random();
            int randomNum = generator.nextInt(cards.size());

            Card randomCard = cards.get(randomNum);
            Card temp = cards.get(0);

            cards.set(0, randomCard);
            cards.set(randomNum, temp);
        }
    }

    public void executeCard() {
        cardState.execute(currentPlayer, turnOrder, this);
    }

    private void addAsObserver() {
        for (SupplyPile supplyPile : supply.getCardList()) {
            for (Card card : supplyPile.getPile()) {
                card.addObserver(this);
            }
        }
    }

    public Supply getSupply() {
        return supply;
    }

    public ArrayList<Card> getTrash() {
        return trash;
    }

    public void trashCard(Card card) {
        trash.add(card);
    }

    public void update(CardState cardState) {
        this.cardState = cardState;
        executeCard();
    }

    public void print(String string) {
        textView.append(string);
    }

    public int getInput() {
        //return view.chooseInt();
        return 0;
    }

    /*
    public Card chooseTreasure() {
        return view.chooseTreasure(currentPlayer, this);
    }

    public int discardCards() {
        return view.discardCards();
    }

    public void printControlledSupply(int cost) {
        view.printControlledSupply(supply, cost);
    }

    public void printBack() {
        view.printBack();
    }

    public void printHand() {
        view.printHand(currentPlayer);
    }

    public void printNiceHand() {
        view.printNiceHand(currentPlayer);
    }

    public void printPlayArea() {
        view.printPlayArea(currentPlayer);
    }

    public int getInput() {
        return view.chooseInt();
    }

    public static void main(String[] args) {
        new Game();
    }
    */
}