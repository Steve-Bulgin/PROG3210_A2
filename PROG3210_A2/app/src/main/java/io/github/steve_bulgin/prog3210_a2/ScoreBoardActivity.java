/* FileName: ScoreBoardActivity.java
 * Purpose: Handles scoreboard logic
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 *      Steven Bulgin, 2016.11.17: Got the lack of loses fixed (spelling mis-match)
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ScoreBoardActivity extends Activity implements View.OnClickListener {

    //Variables
    private ListView scoreboard_items;
    private View sbview;
    private Float x1,x2;
    private PlayerDB db;

    Intent sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        sender = getIntent();
        sbview = findViewById(R.id.sbview);
        scoreboard_items = (ListView) findViewById(R.id.scoreboard_items);
        db = new PlayerDB(this);
        createList();

        sbview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            if (sender != null) {
                                String senderStr = sender.getExtras().getString("sender");

                                if (senderStr.equals("MainActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else if (senderStr.equals("GameActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, GameActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else if (senderStr.equals("AddPlayerActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                            }

                        }
                        break;
                }
                return false;
            }
        });

        scoreboard_items.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            if (sender != null) {
                                String senderStr = sender.getExtras().getString("sender");

                                if (senderStr.equals("MainActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else if (senderStr.equals("GameActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, GameActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else if (senderStr.equals("AddPlayerActivity")) {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                                else {
                                    Intent intent = new Intent(ScoreBoardActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                }
                            }

                        }
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    //My functions
    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();

        for (int i = 0; i < results.size(); ++i) {
            Log.d("Test Message", results.get(i).toString());
        }

        int resource = R.layout.scoreboard_list;
        String[] from = {"l_name", "f_name", "wins", "losses", "ties"};
        int[] to = {R.id.lblLName, R.id.lblFName, R.id.lblWins, R.id.lblLoses, R.id.lblTies};

        SimpleAdapter ad = new SimpleAdapter(this, results,resource,from,to);
        scoreboard_items.setAdapter(ad);
    }

}
