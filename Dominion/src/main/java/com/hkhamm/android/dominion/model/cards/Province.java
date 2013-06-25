package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Province extends Victory {

    public Province() {
        super();
        this.name = "Province";
        this.cost = 8;
        this.victoryPoints = 6;
        this.description = "A province is worth 6 victory points.";
        this.drawable = R.drawable.province;
    }
}
