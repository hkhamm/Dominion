package com.hkhamm.android.dominion.model;

import com.hkhamm.android.dominion.model.cards.*;

import java.util.ArrayList;

public class Supply {

    private ArrayList<SupplyPile> cardList; // TODO create lists for every gridView in the supply
    private int supplySize;

    public Supply(int numberOfPlayers) {
        cardList = new ArrayList<SupplyPile>();
        supplySize = setSupplySize(numberOfPlayers);

        createBaseSupply();
        createActionSupply();
    }

    private void createBaseSupply() {
        SupplyPile estates = new SupplyPile(new Estate(), supplySize);
        cardList.add(estates);

        SupplyPile duchies = new SupplyPile(new Duchy(), supplySize);
        cardList.add(duchies);

        SupplyPile provinces = new SupplyPile(new Province(), supplySize);
        cardList.add(provinces);

        SupplyPile copper = new SupplyPile(new Copper(), 60);
        cardList.add(copper);

        SupplyPile silver = new SupplyPile(new Silver(), 40);
        cardList.add(silver);

        SupplyPile gold = new SupplyPile(new Gold(), 30);
        cardList.add(gold);
    }

    private void createActionSupply() {
    // TODO add supplyPiles to their unique supply row lists, then to their respective gridView lists
        SupplyPile cellar = new SupplyPile(new Cellar(), 10);
        cardList.add(cellar);

        SupplyPile market = new SupplyPile(new Market(), 10);
        cardList.add(market);

        SupplyPile militia = new SupplyPile(new Militia(), 10);
        cardList.add(militia);

        SupplyPile mine = new SupplyPile(new Mine(), 10);
        cardList.add(mine);

        SupplyPile moat = new SupplyPile(new Moat(), 10);
        cardList.add(moat);

        SupplyPile remodel = new SupplyPile(new Remodel(), 10);
        cardList.add(remodel);

        SupplyPile smithy = new SupplyPile(new Smithy(), 10);
        cardList.add(smithy);

        SupplyPile village = new SupplyPile(new Village(), 10);
        cardList.add(village);

        SupplyPile woodcutter = new SupplyPile(new Woodcutter(), 10);
        cardList.add(woodcutter);

        SupplyPile workshop = new SupplyPile(new Workshop(), 10);
        cardList.add(workshop);
    }

    public ArrayList<SupplyPile> getCardList() {
        return cardList;
    }

    public void set(int index, SupplyPile supplyPile) {
        cardList.set(index, supplyPile);
    }

    public Card getCard(int pileIndex) {
        return cardList.get(pileIndex).getCard();
    }

    public int getCost(int pileIndex) {
        return cardList.get(pileIndex).getCost();
    }

    public String getName(int pileIndex) {
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
        return cardList.get(2).size();
    }

    public void purchaseCard(int index, Player player) {
        Card card = cardList.get(index).drawCard();
        player.purchaseCard(card);
    }
}