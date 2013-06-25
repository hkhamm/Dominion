package com.hkhamm.android.dominion.model;

import com.hkhamm.android.dominion.model.states.CardState;

public interface CardObserver {

    public void update(CardState cardState);
}
