package com.hkhamm.android.dominion.model.cards;

import com.hkhamm.android.dominion.R;

public class Silver extends Treasure {

    public Silver() {
        super();
        this.name = "Silver";
        this.cost = 3;
        this.value = 2;
        this.description = "A silver is worth 2 treasure.";
        this.drawable = R.drawable.silver;
    }
}