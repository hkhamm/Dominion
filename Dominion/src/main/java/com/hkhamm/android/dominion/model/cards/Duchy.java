package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Duchy extends Victory {

    public Duchy() {
        super();
        this.name = "Duchy";
        this.cost = 5;
        this.victoryPoints = 3;
        this.description = "A duchy is worth 3 victory points.";
        this.drawable = R.drawable.duchy;
    }
}
