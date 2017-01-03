package io.github.steve_bulgin.prog3210_a2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DeletePlayerActivity extends AppCompatActivity  {

    //Variables

    private ListView delete_player_items;
    private View deleteview;
    private HashMap items;
    private PlayerDB db;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteplayer);

        deleteview = findViewById(R.id.deleteview);

        delete_player_items = (ListView) findViewById(R.id.delete_player_items);

        db = new PlayerDB(this);
        createList();

        deleteview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(DeletePlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        delete_player_items.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(DeletePlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }

        });


        delete_player_items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items = (HashMap) delete_player_items.getItemAtPosition(position);
                if (items.get("full_name").toString().equals("Delete All")) {
                    AlertDialog.Builder alertall = new AlertDialog.Builder(DeletePlayerActivity.this);
                    alertall.setMessage("Do you really want to delete all names?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteAll();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = alertall.create();
                    alert.setTitle("Delete All");
                    alert.show();
                }
                else {

                    AlertDialog.Builder alertone = new AlertDialog.Builder(DeletePlayerActivity.this);
                    alertone.setMessage("Do you really want to delete this player?").setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteOne(items.get("f_name").toString(),items.get("l_name").toString());
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = alertone.create();
                    alert.setTitle("Delete Player");
                    alert.show();

                }
            }
        });
    }



    private void createList() {
        ArrayList<HashMap<String, String>> results = db.getPlayers();
        ArrayList<HashMap<String, String>> fullname = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<>();
        String f_name;
        String l_name;
        String full_name;

        map.put("full_name", "Delete All");
        fullname.add(map);

        for (int j = 0; j <results.size(); ++j) {
            map = (HashMap) results.get(j);
            f_name = map.get("f_name").toString();
            l_name = map.get("l_name").toString();
            full_name = f_name + " " + l_name;
            map.put("full_name", full_name);
            fullname.add(map);
            Log.d("Test Message", results.get(j).toString());
            Log.d("Hash Message", map.get("l_name").toString());
        }




        int resource = R.layout.player_list;
        String[] from = {"full_name"};
        int[] to = {R.id.lblListName};

        //SimpleAdapter ad = new SimpleAdapter(this, fullname,resource,from,to);
        DBAdapter ad = new DBAdapter(this, fullname,resource,from,to);
        delete_player_items.setAdapter(ad);
    }
}
