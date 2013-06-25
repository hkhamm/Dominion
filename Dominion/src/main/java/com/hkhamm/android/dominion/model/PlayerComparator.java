package com.hkhamm.android.dominion.model;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.compareTo(p2);
    }
}