/* FileName: PlayerOneActivity.java
 * Purpose: Handles player one logic
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 *      Steven Bulgin, 2016.11.19: Have pass to game via intent working. Take break.
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
    private View p1view;
    private ListView player_one_items;
    private String fullname;
    private HashMap items;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerone);

        p1view = findViewById(R.id.player1view);

        player_one_items = (ListView) findViewById(R.id.player_one_items);
        db = new PlayerDB(this);
        createList();

        p1view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(PlayerOneActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        player_one_items.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(PlayerOneActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }

        });

        player_one_items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items = (HashMap) player_one_items.getItemAtPosition(position);
                fullname = items.get("full_name").toString();
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

        DBAdapter ad = new DBAdapter(this, fullname,resource,from,to);
        player_one_items.setAdapter(ad);
    }

    private void namePasser() {
        if (items!=null){

            if (((GameApplication) this.getApplication()).getFull_name_two() != null && ((GameApplication) this.getApplication()).getFull_name_two().equals(items.get("full_name").toString())) {
                AlertDialog alertDialog = new AlertDialog.Builder(PlayerOneActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Player One can not be the same as Player Two");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else {
                ((GameApplication) this.getApplication()).setFull_name_one(items.get("full_name").toString());
                ((GameApplication) this.getApplication()).setL_name_one(items.get("l_name").toString());
                ((GameApplication) this.getApplication()).setF_name_one(items.get("f_name").toString());
                Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_one(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
