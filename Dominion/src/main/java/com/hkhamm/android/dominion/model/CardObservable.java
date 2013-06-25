package com.hkhamm.android.dominion.model;

import com.hkhamm.android.dominion.model.states.CardState;

import java.util.ArrayList;

public class CardObservable {

    ArrayList<CardObserver> observers;

    public CardObservable() {
        observers = new ArrayList<CardObserver>();
    }

    public void addObserver(CardObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(CardObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observers.indexOf(observer));
        }
    }

    public void notifyObservers(CardState cardState) {
        for (CardObserver observer : observers) {
            observer.update(cardState);
        }
    }
}
