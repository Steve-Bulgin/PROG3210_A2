/* FileName: PlayerOneActivity.java
 * Purpose: Handles player one logic
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 *      Steven Bulgin, 2016.11.19: Have pass to game via intent working. Take break.
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlayerOneActivity extends Activity {

    //Variables
    private PlayerDB db;
    private ListView player_one_items;
    private String fullname;
    private HashMap items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerone);

        player_one_items = (ListView) findViewById(R.id.player_one_items);
        db = new PlayerDB(this);
        createList();

        player_one_items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items = (HashMap) player_one_items.getItemAtPosition(position);
                //Intent p1_intent = new Intent(view.getContext(), PlayerTwoActivity.class);
                //p1_intent.putExtra("P1_full_name", items.get("full_name").toString());
                //p1_intent.putExtra("P1_l_name", items.get("l_name").toString());
                fullname = items.get("full_name").toString();
                //((GameApplication) this.getApplication()).setFull_name_one(items.get("full_name").toString());

                //startActivity(p1_intent);
                namePasser();
            }
        });
    }



    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();
        ArrayList<HashMap<String, String>> fullname = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        String f_name;
        String l_name;
        String full_name;

        //This loops through the data returned from the getPlayers method
        //gets the first and last names, concats them nicely and HashMaps the full name
        //and adds it back to an arraylist. I though it was the easiest and maximized code reuse for me
        //Probably questionable
        for (int i = 0; i < results.size(); ++i) {
            map = (HashMap)results.get(i);
            f_name = map.get("f_name").toString();
            l_name = map.get("l_name").toString();
            full_name = f_name + " " + l_name;
            map.put("full_name", full_name);
            fullname.add(map);
            Log.d("Test Message", results.get(i).toString());
            Log.d("Hash Message", map.get("l_name").toString());
        }





        int resource = R.layout.player_list;
        String[] from = {"full_name"};
        int[] to = {R.id.lblListName};

        SimpleAdapter ad = new SimpleAdapter(this, fullname,resource,from,to);
        player_one_items.setAdapter(ad);
    }

    private void namePasser() {
        if (items!=null){
            ((GameApplication) this.getApplication()).setFull_name_one(items.get("full_name").toString());
            ((GameApplication) this.getApplication()).setL_name_one(items.get("l_name").toString());
            Toast.makeText(getApplicationContext(),((GameApplication) this.getApplication()).getFull_name_one(), Toast.LENGTH_SHORT).show();
        }
    }
}
