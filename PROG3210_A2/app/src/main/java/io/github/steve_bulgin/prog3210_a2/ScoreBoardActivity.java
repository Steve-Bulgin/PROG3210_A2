/* FileName: ScoreBoardActivity.java
 * Purpose: Handles scoreboard logic
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class ScoreBoardActivity extends Activity implements View.OnClickListener {

    //Variables
    private ListView scoreboard_items;
    private PlayerDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        scoreboard_items = (ListView) findViewById(R.id.scoreboard_items);
        db = new PlayerDB(this);
        createList();
    }

    @Override
    public void onClick(View v) {

    }

    //My functions
    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();

        int resource = R.layout.scoreboard_list;
        String[] from = {"l_name", "f_name", "wins", "loses", "ties"};
        int[] to = {R.id.lblLName, R.id.lblFName, R.id.lblWins, R.id.lblLoses, R.id.lblTies};

        SimpleAdapter ad = new SimpleAdapter(this, results,resource,from,to);
        scoreboard_items.setAdapter(ad);
    }

}
