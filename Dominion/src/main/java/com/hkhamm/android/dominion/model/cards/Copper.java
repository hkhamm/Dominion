package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Copper extends Treasure {

    public Copper() {
        super();
        this.name = "Copper";
        this.cost = 0;
        this.value = 1;
        this.description = "A copper is worth 1 treasure.";
        this.drawable = R.drawable.copper;
    }
}