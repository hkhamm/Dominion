package com.hkhamm.android.dominion.model;

import android.widget.GridView;

import com.hkhamm.android.dominion.model.cards.*;

import java.util.ArrayList;

public class Supply {

    private ArrayList<ArrayList<SupplyPile>> cardList;
    private ArrayList<SupplyPile> victorySupply;
    private ArrayList<SupplyPile> treasureSupply;
    private ArrayList<SupplyPile> fiveCostSupply;
    private ArrayList<SupplyPile> fourCostSupply;
    private ArrayList<SupplyPile> threeCostSupply;
    private ArrayList<SupplyPile> twoCostSupply;
    private int supplySize;

    public Supply(int numberOfPlayers) {
        cardList = new ArrayList<ArrayList<SupplyPile>>();
        supplySize = setSupplySize(numberOfPlayers);

        createBaseSupply();
        createActionSupply();
        createCardList();
    }

    private void createBaseSupply() {
        SupplyPile estates = new SupplyPile(new Estate(), supplySize);
        victorySupply.add(estates);

        SupplyPile duchies = new SupplyPile(new Duchy(), supplySize);
        victorySupply.add(duchies);

        SupplyPile provinces = new SupplyPile(new Province(), supplySize);
        victorySupply.add(provinces);

        SupplyPile gold = new SupplyPile(new Gold(), 30);
        treasureSupply.add(gold);

        SupplyPile silver = new SupplyPile(new Silver(), 40);
        treasureSupply.add(silver);

        SupplyPile copper = new SupplyPile(new Copper(), 60);
        treasureSupply.add(copper);
    }

    private void createActionSupply() {

        SupplyPile market = new SupplyPile(new Market(), 10);
        fiveCostSupply.add(market);

        SupplyPile militia = new SupplyPile(new Militia(), 10);
        fourCostSupply.add(militia);

        SupplyPile mine = new SupplyPile(new Mine(), 10);
        fourCostSupply.add(mine);

        SupplyPile remodel = new SupplyPile(new Remodel(), 10);
        fourCostSupply.add(remodel);

        SupplyPile smithy = new SupplyPile(new Smithy(), 10);
        fourCostSupply.add(smithy);

        SupplyPile village = new SupplyPile(new Village(), 10);
        threeCostSupply.add(village);

        SupplyPile woodcutter = new SupplyPile(new Woodcutter(), 10);
        threeCostSupply.add(woodcutter);

        SupplyPile workshop = new SupplyPile(new Workshop(), 10);
        threeCostSupply.add(workshop);

        SupplyPile cellar = new SupplyPile(new Cellar(), 10);
        twoCostSupply.add(cellar);

        SupplyPile moat = new SupplyPile(new Moat(), 10);
        twoCostSupply.add(moat);
    }

    private void createCardList() {
        cardList.add(victorySupply);
        cardList.add(treasureSupply);
        cardList.add(fiveCostSupply);
        cardList.add(fourCostSupply);
        cardList.add(threeCostSupply);
        cardList.add(twoCostSupply);
    }

    public ArrayList<ArrayList<SupplyPile>> getCardList() {
        return cardList;
    }

    public ArrayList<SupplyPile> getVictorySupply() {
        return victorySupply;
    }

    public ArrayList<SupplyPile> getTreasureSupply() {
        return treasureSupply;
    }

    public ArrayList<SupplyPile> getFiveCostSupply() {
        return fiveCostSupply;
    }

    public ArrayList<SupplyPile> getFourCostSupply() {
        return fourCostSupply;
    }

    public ArrayList<SupplyPile> getThreeCostSupply() {
        return threeCostSupply;
    }

    public ArrayList<SupplyPile> getTwoCostSupply() {
        return twoCostSupply;
    }

    public Card getCard(ArrayList<SupplyPile> cardList, int pileIndex) {
        return cardList.get(pileIndex).getCard();
    }

    public int getCost(ArrayList<SupplyPile> cardList, int pileIndex) {
        return cardList.get(pileIndex).getCost();
    }

    public String getName(ArrayList<SupplyPile> cardList, int pileIndex) {
        return cardList.get(pileIndex).getCard().getName();
    }

    public int setSupplySize(int numberOfPlayers) {
        if (numberOfPlayers == 2) {
            return 8;
        }
        else
            return 12;
    }

    public int getProvinces() {
        return victorySupply.get(2).size();
    }

    public void purchaseCard(ArrayList<SupplyPile> cardList, int index, Player player) {
        Card card = cardList.get(index).drawCard();
        player.purchaseCard(card);
    }
}