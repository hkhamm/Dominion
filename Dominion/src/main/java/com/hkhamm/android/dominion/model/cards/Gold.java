package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Gold extends Treasure {

    public Gold() {
        super();
        this.name = "Gold";
        this.cost = 6;
        this.value = 3;
        this.description = "A gold is worth 3 treasure.";
        this.drawable = R.drawable.gold;
    }
}