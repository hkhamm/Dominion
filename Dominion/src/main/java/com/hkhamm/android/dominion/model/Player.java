package com.hkhamm.android.dominion.model;

import com.hkhamm.android.dominion.Game;
import com.hkhamm.android.dominion.model.cards.*;

import java.util.ArrayList;

public class Player implements Comparable<Player> {

    private String name;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> playArea;
    private ArrayList<Card> hand;
    private int buyingPower;
    private int usedBuyingPower;
    private int tempBuyingPower;
    private int actions;
    private int buys;


    public Player(String name, ArrayList<Card> deck) {
        this.name = name;
        this.deck = deck;
        this.hand = new ArrayList<Card>();
        this.playArea = new ArrayList<Card>();
        this.discardPile = new ArrayList<Card>();
        this.usedBuyingPower = 0;
        this.tempBuyingPower = 0;
        this.actions = 1;
        this.buys = 1;
    }

    public void draw(int num) {
        for (int i = 0; i < num; ++i) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }

    public void draw() {
        hand.add(deck.get(0));
        deck.remove(0);
    }

    public void drawCards(int num) {
        if (deck.size() >= num) {
            draw(num);
        }
        else if (deck.size() < num && deck.size() > 0) {
            int size = deck.size();
            draw(size);

            deck.addAll(discardPile);
            discardPile.clear();
            Game.shuffle(deck);

            draw(num - size);
        }
        else {
            deck.addAll(discardPile);
            Game.shuffle(deck);
            discardPile.clear();
            draw(num);
        }
    }

    public void cleanUp() {
        discardPile.addAll(playArea);
        playArea.clear();
        discardPile.addAll(hand);
        hand.clear();
        buyingPower = 1;
        actions = 1;
        buys = 1;
        tempBuyingPower = 0;
        usedBuyingPower = 0;
        drawCards(5);
        setBuyingPower(); // Necessary?
    }

    public void discardCard(Card card, ArrayList<Card> origin) {
        discardPile.add(card);
        origin.remove(origin.indexOf(card));
    }

    public void discardCard(int index) {
        discardPile.add(hand.get(index));
        hand.remove(index);
    }

    public void moveCard(Card card, ArrayList<Card> origin, ArrayList<Card> destination) {
        origin.remove(origin.indexOf(card));
        destination.add(card);
    }

    public void playCard(Card card) {
        playArea.add(card);
        hand.remove(card);
        card.play(this);
    }

    public void purchaseCard(Card card) {
        discardPile.add(card);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getBuys() {
        return buys;
    }

    public void setBuys(int buys) {
        this.buys = buys;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Card> getPlayArea() {
        return playArea;
    }

    public int getVictoryPoints() {
        deck.addAll(hand);
        hand.clear();
        deck.addAll(discardPile);
        discardPile.clear();
        int points = 0;
        for (Card card : deck) {
            points += card.getVictoryPoints();
        }

        return points;
    }

    public void increaseTempBuyingPower(int num) {
        tempBuyingPower += num;
    }

    public void setBuyingPower() {
        int totalValue = 0;
        if (hand.size() > 0) {
            for (Card card : hand) {
                int cardValue = card.getValue();
                if (cardValue > 0) {
                    totalValue += cardValue;
                }
            }
        }
        else {
            for (Card card : playArea) {
                int cardValue = card.getValue();
                if (cardValue > 0) {
                    totalValue += cardValue;
                }
            }
        }
        buyingPower = totalValue + tempBuyingPower - usedBuyingPower;
    }

    public int getBuyingPower() {
        return buyingPower;
    }

    public int getUsedBuyingPower() {
        return usedBuyingPower;
    }

    public void increaseUsedBuyingPower(int num) {
        usedBuyingPower += num;
    }

    @Override
    public String toString() {
        return Integer.toString(getVictoryPoints());
    }

    @Override
    public int compareTo(Player other) {
        return other.toString().compareTo(toString());
    }
}