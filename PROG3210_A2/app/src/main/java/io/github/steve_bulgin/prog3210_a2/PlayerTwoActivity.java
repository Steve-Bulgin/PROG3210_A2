/* FileName: PlayerTwoActivity.java
 * Purpose: Handles logic for player 2
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 *      Steven Bulgin, 2017.02.25: Added onBackPressed to relaunch main activity on
 *                              pressing of the back android button
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.app.AlertDialog;
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

public class PlayerTwoActivity extends Activity {
    //Variables
    private View p2view;
    private Float x1,x2;
    private PlayerDB db;
    private ListView player_two_items;
    private HashMap items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playertwo);

        p2view = findViewById(R.id.p2view);

        player_two_items = (ListView) findViewById(R.id.player_two_items);

        db = new PlayerDB(this);
        createList();

        p2view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(PlayerTwoActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }
        });

        player_two_items.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(PlayerTwoActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }

        });

        player_two_items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items = (HashMap) player_two_items.getItemAtPosition(position);
                namePasser();
            }
        });
    }

    //OnBackpress fire the main oncreate
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlayerTwoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();
        ArrayList<HashMap<String, String>> fullname = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        String f_name;
        String l_name;
        String full_name;

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

        DBAdapter ad = new DBAdapter(this, ((GameApplication) this.getApplication()).getFull_name_two(), fullname,resource,from,to);
        player_two_items.setAdapter(ad);
    }

    private void namePasser() {
        if (items!=null){

            if (((GameApplication) this.getApplication()).getFull_name_one() != null && ((GameApplication) this.getApplication()).getFull_name_one().equals(items.get("full_name").toString())) {
                AlertDialog alertDialog = new AlertDialog.Builder(PlayerTwoActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Player Two can not be the same as Player One");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else {

                ((GameApplication) this.getApplication()).setFull_name_two(items.get("full_name").toString());
                ((GameApplication) this.getApplication()).setL_name_two(items.get("l_name").toString());
                ((GameApplication) this.getApplication()).setF_name_two(items.get("f_name").toString());
                Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_two(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
