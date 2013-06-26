package com.hkhamm.android.dominion;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.hkhamm.android.dominion.controller.Game;
import com.hkhamm.android.dominion.model.CardAdapter;
import com.hkhamm.android.dominion.model.Supply;
import com.hkhamm.android.dominion.model.SupplyPile;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Game game;
    private Supply supply;

    private Integer[] treasureIds = {};
    private Integer[] victoryIds = {};
    private Integer[] fiveCostIds = {};
    private Integer[] fourCostIds = {};
    private Integer[] threeCostIds = {};
    private Integer[] twoCostIds = {};
    private Integer[] playAreaIds = {};
    private Integer[] handIds = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(this);
        supply = game.getSupply();
        // TODO Add option to select supply. Random, choose individual cards, or choose a pre-made list,
        // TODO Then set this in supply

        GridView treasure = (GridView) findViewById(R.id.treasure);
        treasureIds = fillSupply(supply.getTreasureSupply());
        treasure.setAdapter(new CardAdapter(this, treasureIds));
        treasure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getTreasureSupply(), position);
            }
        });

        GridView victory = (GridView) findViewById(R.id.victory);
        victoryIds = fillSupply(supply.getVictorySupply());
        victory.setAdapter(new CardAdapter(this, victoryIds));
        victory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getVictorySupply(), position);
            }
        });

        GridView fiveCost = (GridView) findViewById(R.id.five_cost);
        fiveCostIds = fillSupply(supply.getFiveCostSupply());
        fiveCost.setAdapter(new CardAdapter(this, fiveCostIds));
        fiveCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getFiveCostSupply(), position);
            }
        });

        GridView fourCost = (GridView) findViewById(R.id.four_cost);
        fourCostIds = fillSupply(supply.getFourCostSupply());
        fourCost.setAdapter(new CardAdapter(this, fourCostIds));
        fourCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getFourCostSupply(), position);
            }
        });

        GridView threeCost = (GridView) findViewById(R.id.three_cost);
        threeCostIds = fillSupply(supply.getThreeCostSupply());
        threeCost.setAdapter(new CardAdapter(this, threeCostIds));
        threeCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getThreeCostSupply(), position);
            }
        });

        GridView twoCost = (GridView) findViewById(R.id.two_cost);
        twoCostIds = fillSupply(supply.getTwoCostSupply());
        twoCost.setAdapter(new CardAdapter(this, twoCostIds));
        twoCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.buyCard(supply.getTwoCostSupply(), position);
            }
        });

        GridView playArea = (GridView) findViewById(R.id.play_area);
        playArea.setAdapter(new CardAdapter(this, playAreaIds));
        playArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView hand = (GridView) findViewById(R.id.hand);
        hand.setAdapter(new CardAdapter(this, handIds));
        hand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                game.playAction(position);
            }
        });

        // TODO create end turn button
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public Integer[] fillSupply(ArrayList<SupplyPile> supplyList) {
        int size = supplyList.size();
        Integer[] cardIdList = new Integer[size];

        for (int i = 0; i < size; ++i) {
            cardIdList[i] = supplyList.get(i).getDrawable();
        }

        return cardIdList;
    }

    public void setTreasureIds(Integer[] treasureIds) {
        this.treasureIds = treasureIds;
    }

    public void setVictoryIds(Integer[] victoryIds) {
        this.victoryIds = victoryIds;
    }

    public void setFiveCostIds(Integer[] fiveCostIds) {
        this.fiveCostIds = fiveCostIds;
    }

    public void setFourCostIds(Integer[] fourCostIds) {
        this.fourCostIds = fourCostIds;
    }

    public void setThreeCostIds(Integer[] threeCostIds) {
        this.threeCostIds = threeCostIds;
    }

    public void setTwoCostIds(Integer[] twoCostIds) {
        this.twoCostIds = twoCostIds;
    }

    public void setPlayAreaIds(Integer[] playAreaIds) {
        this.playAreaIds = playAreaIds;
    }

    public void setHandIds(Integer[] handIds) {
        this.handIds = handIds;
    }

    public Integer[] getHandIds() {
        return handIds;
    }
    
}
