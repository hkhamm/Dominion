package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Estate extends Victory {

    public Estate() {
        super();
        this.name = "Estate";
        this.cost = 2;
        this.victoryPoints = 1;
        this.description = "An estate is worth 1 victory point.";
        this.drawable = R.drawable.estate;
    }
}
