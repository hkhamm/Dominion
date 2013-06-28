package com.hkhamm.android.dominion;

import android.widget.GridView;
import android.widget.TextView;

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

    // TODO add labels to hand and play area, add auto-scrolling to message area, increase message area size

    public Game(MainActivity main) {
        this.main = main;
        textView = (TextView) main.findViewById(R.id.messages);
        cardState = new CardState();
        numberOfPlayers = 2;
        supply = new Supply(numberOfPlayers);
        trash = new ArrayList<Card>();
        turnOrder = new ArrayList<Player>();
        currentPlayerIndex = 0;
        buyFlag = false;

        addAsObserver();
        getStartingDecks();
        currentPlayer = turnOrder.get(currentPlayerIndex);
        setHandIds(currentPlayer);
        startTurn();
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
        }
    }

    private void setHandIds(Player player) {
        Integer[] handIds = new Integer[player.getHand().size()];

        for (int i = 0; i < player.getHand().size(); ++i) {
            handIds[i] = player.getHand().get(i).getDrawable();
        }

        main.setHandIds(handIds);
    }

    private void setPlayAreaIds(Player player) {
        Integer[] playAreaIds = new Integer[player.getPlayArea().size()];

        for (int i = 0; i < player.getPlayArea().size(); ++i) {
            playAreaIds[i] = player.getPlayArea().get(i).getDrawable();
        }

        main.setHandIds(playAreaIds);
    }

    private void startTurn() {

        print(currentPlayer.getName() + "'s Turn");

        currentPlayer.setBuyingPower();

        //refreshPlayerArea();

        print("Buying Power: " + currentPlayer.getBuyingPower());
        print("Play an action or buy a card.");
    }

    public void playAction(int cardIndex) {
        Card card = currentPlayer.getHand().get(cardIndex);

        if (card instanceof Action || card instanceof ActionAttack ||
                card instanceof ActionReaction && currentPlayer.getActions() > 0 && !buyFlag) {
            print("A " + card.getName() + " was played.");

            currentPlayer.playCard(card);
            currentPlayer.setActions(currentPlayer.getActions() - 1);
        }
        else if (currentPlayer.getActions() == 0) {
            print("You have no more actions. Buy a card or end your turn.");
        }
        else {
            print("You must choose an action, try again.");
        }

        if (currentPlayer.getActions() == 0) {
            print("Buy Phase: Choose a card to buy from the supply.");
        }
    }

    public void buyCard(ArrayList<SupplyPile> supplyList, int index) {
        int cardValue = supply.getCost(supplyList, index);
        String cardName = supply.getName(supplyList, index);

        if (cardValue <= currentPlayer.getBuyingPower()) {
            playHand();
            print("A " + cardName + " was purchased.");
            buyFlag = true;
            supply.purchaseCard(supplyList, index, currentPlayer);
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
    }

    /**
     * Moves all cards from the hand to the play area.
     */
    private void playHand() {
        main.setPlayAreaIds(main.getHandIds().clone());
        main.setHandIds(new Integer[0]);

        refreshPlayerArea();
    }

    private void refreshPlayerArea() {
        refreshGridView(main.getPlayArea(), main.getPlayAreaIds());
        refreshGridView(main.getHand(), main.getHandIds());
    }

    private void refreshGridView(GridView gridView, Integer[] drawableIds) {
        CardAdapter cardAdaptor = new CardAdapter(main, drawableIds);
        gridView.invalidateViews();
        gridView.setAdapter(cardAdaptor);
    }

    // TODO bring up a floating window with the card description

    public void examineCard(ArrayList<SupplyPile> supplyList, int index) {
        Card card = supply.getCard(supplyList, index);
        print("Name: " + card.getName() + " Cost: " + card.getCost() + " Description: " + card.getDescription());
    }

    public void examinePlayerCard(ArrayList<Card> cardList, int index) {
        Card card = cardList.get(index);
        print("Name: " + card.getName() + " Cost: " + card.getCost() + " Description: " + card.getDescription());
    }

    public void endTurn() {
        buyFlag = false;
        print("End turn.");

        if (supply.getProvinces() == 0 && currentPlayerIndex == 0) {
            endGame();
        }

        for (ArrayList<SupplyPile> supplyPileList : supply.getCardList()) {
            for (SupplyPile pile : supplyPileList) {
                if (pile.size() == 0) {
                    emptySupply += 1;
                }
            }
        }

        if (emptySupply >= 3 && currentPlayerIndex == 0) {
            endGame();
        }
        else {
            emptySupply = 0;
        }

        currentPlayer.cleanUp();
        if (currentPlayerIndex != numberOfPlayers - 1) {
            currentPlayerIndex++;
        }
        else {
            currentPlayerIndex = 0;
        }

        currentPlayer = turnOrder.get(currentPlayerIndex);
        setHandIds(currentPlayer);
        main.setPlayAreaIds(new Integer[0]); // TODO clear play area before starting a new turn
        refreshPlayerArea();
        startTurn();
    }

    public void endGame() {
        Collections.sort(turnOrder, new PlayerComparator());
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
        for (ArrayList<SupplyPile> supplyPileList : supply.getCardList()) {
            for (SupplyPile pile : supplyPileList) {
                pile.getCard().addObserver(this);
            }
        }
    }

    public Supply getSupply() {
        return supply;
    }

    public ArrayList<Card> getPlayArea() {
        return currentPlayer.getPlayArea();
    }

    public ArrayList<Card> getHand() {
        return currentPlayer.getHand();
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
        textView.append(string + "\n");
    }
}