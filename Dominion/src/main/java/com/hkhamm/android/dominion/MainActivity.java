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

public class MainActivity extends Activity {

    private Integer[] treasureIds = {
            R.drawable.gold, R.drawable.silver, R.drawable.copper
    };
    private Integer[] victoryIds = {
            R.drawable.estate, R.drawable.duchy, R.drawable.province
    };
    private Integer[] fiveCostIds = {
            R.drawable.action, R.drawable.action,
            R.drawable.action, R.drawable.action, R.drawable.action
    };
    private Integer[] fourCostIds = {
            R.drawable.action, R.drawable.action,
            R.drawable.action, R.drawable.action
    };
    private Integer[] threeCostIds = {
            R.drawable.action, R.drawable.action,
            R.drawable.action
    };
    private Integer[] twoCostIds = {
            R.drawable.action, R.drawable.action
    };

    private Integer[] playAreaIds = {};
    private Integer[] handIds = {R.drawable.action};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView treasure = (GridView) findViewById(R.id.treasure);
        treasure.setAdapter(new CardAdapter(this, treasureIds));
        treasure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView victory = (GridView) findViewById(R.id.victory);
        victory.setAdapter(new CardAdapter(this, victoryIds));
        victory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView fiveCost = (GridView) findViewById(R.id.five_cost);
        fiveCost.setAdapter(new CardAdapter(this, fiveCostIds));
        fiveCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView fourCost = (GridView) findViewById(R.id.four_cost);
        fourCost.setAdapter(new CardAdapter(this, fourCostIds));
        fourCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView threeCost = (GridView) findViewById(R.id.three_cost);
        threeCost.setAdapter(new CardAdapter(this, threeCostIds));
        threeCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView twoCost = (GridView) findViewById(R.id.two_cost);
        twoCost.setAdapter(new CardAdapter(this, twoCostIds));
        twoCost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView playArea = (GridView) findViewById(R.id.play_area);
        playArea.setAdapter(new CardAdapter(this, playAreaIds));
        playArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        GridView hand = (GridView) findViewById(R.id.hand);
        hand.setAdapter(new CardAdapter(this, handIds));
        hand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        // TODO create end turn button

        //new Game(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setPlayAreaIds(Integer[] playAreaIds) {
        this.playAreaIds = playAreaIds;
    }

    public Integer[] getHandIds() {
        return handIds;
    }

    public void setHandIds(Integer[] handIds) {
        this.handIds = handIds;
    }
    
}
