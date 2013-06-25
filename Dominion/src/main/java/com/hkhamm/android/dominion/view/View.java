package com.hkhamm.android.dominion.view;

import com.hkhamm.android.dominion.controller.Game;
import com.hkhamm.android.dominion.model.cards.*;
import com.hkhamm.android.dominion.model.Player;
import com.hkhamm.android.dominion.model.Supply;
import com.hkhamm.android.dominion.model.SupplyPile;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    private Scanner scanner;
    private int numberOfPlayers;
    private int phase;
    private boolean buyFlag;

    public View() {
        scanner = new Scanner(System.in);
        numberOfPlayers = 0;
        phase = 0;
        buyFlag = false;

        System.out.println("Welcome to Dominion!\n");
    }

    public int getPlayers() {
        System.out.print("How many players? (2 - 4): ");

        int input = scanner.nextInt();
        if (input > 1 && input < 5) {
            numberOfPlayers = input;
        }
        else {
            System.out.println("Input is out of range, try again.");
            getPlayers();
        }

        return numberOfPlayers;
    }

    public void printNewTurn(Player player) {
        System.out.println("");
        System.out.println(player.getName() + "'s Turn");
    }

    public void printEndTurn() {
        System.out.println("End Turn.");
    }

    public void printCard(Card card) {
        System.out.printf("%n%s ($%s)%n", card.getName(), card.getCost());
        System.out.printf("%s%n", card.getDescription());
    }

    public void printSupply(Supply supply) {
        System.out.println("");
        System.out.println("Supply:");
        int num = 1;
        for (SupplyPile pile : supply.getCardList()) {
            System.out.printf("%2s) %-10s $%s%n",
                              num, pile.getCard().getName(), pile.getCard().getCost());
            num++;
        }
    }

    public void printFormattedSupply(Supply supply) {
        printBack();
        System.out.println("");
        ArrayList<ArrayList<SupplyPile>> formattedSupply = formatSupply(supply);
        int num = 1;
        for (ArrayList<SupplyPile> innerList : formattedSupply) {
            for (SupplyPile supplyPile : innerList) {
                System.out.printf("%2s) %-10s ($%s)  ",
                                  num, supplyPile.getCard().getName(), supplyPile.getCost());
                supply.set(num - 1, supplyPile);
                num++;
            }
            System.out.println("\n");
        }
    }

    public ArrayList<ArrayList<SupplyPile>> formatSupply(Supply supply) {
        ArrayList<ArrayList<SupplyPile>> supplyGrid = new ArrayList<ArrayList<SupplyPile>>();
        for (int i = 0; i < 6; ++i) {
            supplyGrid.add(new ArrayList<SupplyPile>());
        }

        ArrayList<SupplyPile> victoryCards = new ArrayList<SupplyPile>();
        ArrayList<SupplyPile> treasureCards = new ArrayList<SupplyPile>();
        ArrayList<SupplyPile> fiveCostCards = new ArrayList<SupplyPile>();
        ArrayList<SupplyPile> fourCostCards = new ArrayList<SupplyPile>();
        ArrayList<SupplyPile> threeCostCards = new ArrayList<SupplyPile>();
        ArrayList<SupplyPile> twoCostCards = new ArrayList<SupplyPile>();

        for (int j = 0; j < supply.getCardList().size(); ++j) {
            SupplyPile supplyPile = supply.getCardList().get(j);
            Card card = supplyPile.getCard();
            if (card instanceof Victory) {
                victoryCards.add(supplyPile);
            }
            else if (card instanceof Treasure) {
                treasureCards.add(supplyPile);
            }
            else if (card.getCost() == 5) {
                fiveCostCards.add(supplyPile);
            }
            else if (card.getCost() == 4) {
                fourCostCards.add(supplyPile);
            }
            else if (card.getCost() == 3) {
                threeCostCards.add(supplyPile);
            }
            else if (card.getCost() == 2) {
                twoCostCards.add(supplyPile);
            }
        }

        for (int col = 0; col < victoryCards.size(); ++col) {
            supplyGrid.get(0).add(col, victoryCards.get(col));
        }
        for (int col = 0; col < treasureCards.size(); ++col) {
            supplyGrid.get(1).add(col, treasureCards.get(col));
        }
        for (int col = 0; col < fiveCostCards.size(); ++col) {
            supplyGrid.get(2).add(col, fiveCostCards.get(col));
        }
        for (int col = 0; col < fourCostCards.size(); ++col) {
            supplyGrid.get(3).add(col, fourCostCards.get(col));
        }
        for (int col = 0; col < threeCostCards.size(); ++col) {
            supplyGrid.get(4).add(col, threeCostCards.get(col));
        }
        for (int col = 0; col < twoCostCards.size(); ++col) {
            supplyGrid.get(5).add(col, twoCostCards.get(col));
        }

        return supplyGrid;
    }

    public void printControlledSupply(Supply supply, int cost) {
        System.out.println("");
        System.out.println("Supply:");
        int num = 1;
        for (SupplyPile pile : supply.getCardList()) {
            if (pile.getCost() <= cost) {
                System.out.printf("%2s) %-10s $%s%n",
                                  num, pile.getCard().getName(), pile.getCard().getCost());
            }
            num++;
        }
    }

    public void printBack() {
        System.out.println(" 0) Back");
    }

    public void printNiceHand(Player player) {
        System.out.println("");
        System.out.print("Hand: ");
        int num = 1;
        for (Card card : player.getHand()) {
            System.out.printf("|%s| ", card.getName());
            num++;
        }
        System.out.printf("%nBuying power: $%s %n", player.getBuyingPower());
    }

    public void printHand(Player player) {
        System.out.printf("%nChoose a card from your hand:%n");
        int num = 1;
        for (Card card : player.getHand()) {
            System.out.printf("|%s) %s| ", num, card.getName());
            num++;
        }
        System.out.printf("%n 0) Back%n");
        System.out.printf("Buying power: $%s %n", player.getBuyingPower());
    }

    public void printPlayArea(Player player) {
        System.out.println("");
        System.out.print("Played: ");
        for (Card card : player.getPlayArea()) {
            System.out.printf("|%s| ", card.getName());
        }
    }

    public int chooseInt() {
        System.out.print("Choice: ");

        return scanner.nextInt();
    }

    public int choosePhase(int actions, int buys) {
        System.out.println("");
        System.out.println("Choose: ");
        if (actions > 0 && !buyFlag) {
            System.out.println("1) Play Action");
        }
        if (buys > 0) {
            System.out.println("2) Buy Card");
        }
        System.out.println("3) Examine Card");
        System.out.println("4) End Turn");
        System.out.print("Choice: ");

        int input = scanner.nextInt();
        if (input >= 1 && input <= 4) {
            phase = input;
        }
        else {
            System.out.println("Input is out of range, try again.");
            choosePhase(actions, buys);
        }

        return phase;
    }

    public Card chooseTreasure(Player player, Game game) {
        print("Which treasure will you trash? ");
        int cardIndex = chooseInt();
        Card card = player.getHand().get(cardIndex);
        if (card instanceof Copper || card instanceof Silver) {
            game.trashCard(player.getHand().get(cardIndex));
        }
        else {
            print("That's an invalid card, try again.");
            chooseTreasure(player, game);
        }
        return card;
    }

    public void printBuyingPower(Player player) {
        System.out.printf("%s $%s%n", "Buying Power: ", player.getBuyingPower());

    }

    public int discardCards() {
        System.out.print("How many cards do you want to discard? ");
        return scanner.nextInt();
    }

    public void print(String string) {
        System.out.printf("%s%n", string);
    }

    public void setBuyFlag(boolean bool) {
        buyFlag = bool;
    }

    public void endGame(ArrayList<Player> players) {
        System.out.println("");
        System.out.println("Game over.");

        int victoryPoints = 0;
        ArrayList<Player> victor = new ArrayList<Player>();

        for (Player player : players) {
            int playerPoints = player.getVictoryPoints();
            System.out.printf("%s has %s victory points.%n", player.getName(), playerPoints);
            if (playerPoints > victoryPoints) {
                victoryPoints = playerPoints;
                victor.add(player);
            }
            else if (playerPoints == victoryPoints) {
                victor.add(player);
            }
        }

        if (victor.size() > 1) {
            System.out.printf("%nIt's a tie.%n");
            for (Player player : players) {
                System.out.printf("%s is a victor with %s victory points.%n",
                                  player.getName(), victoryPoints);
            }
        }
        else {
            System.out.printf("%n%s is the victor with %s victory points.%n",
                              victor.get(0).getName(), victoryPoints);
        }
    }
}