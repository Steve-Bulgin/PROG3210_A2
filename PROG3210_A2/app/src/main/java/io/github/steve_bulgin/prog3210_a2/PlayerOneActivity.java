/* FileName: PlayerOneActivity.java
 * Purpose: Handles player one logic
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlayerOneActivity extends Activity {

    //Variables
    private PlayerDB db;
    private ListView player_one_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerone);

        player_one_items = (ListView) findViewById(R.id.player_one_items);

        db = new PlayerDB(this);
        createList();
    }

    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();
        HashMap<String, String> map;


        for (int i = 0; i < results.size(); ++i) {
            //Iterator iterator = map.entrySet().iterator();
            map = (HashMap)results.get(i);
            Log.d("Test Message", results.get(i).toString());
            Log.d("Hash Message", map.get("l_name").toString());


        }





//        int resource = R.layout.player_list;
//        String[] from = {"l_name", "f_name", "wins", "losses", "ties"};
//        int[] to = {R.id.lblLName, R.id.lblFName, R.id.lblWins, R.id.lblLoses, R.id.lblTies};
//
//        SimpleAdapter ad = new SimpleAdapter(this, results,resource,from,to);
//        player_one_items.setAdapter(ad);
    }
}
