/* FileName: GameActivity.java
 * Purpose: Handles any logic for the game layout
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener {

    //Variables
    private Button btnP1Wins, btnP2Wins, btnTie;
    PlayerDB db;
    private TextView txtPlayer1, txtPlayer2;
    //dummy
    private String p1last,p1first,p2last,p2first;
    private boolean player1picked = false;
    private boolean player2picked = false;
    private Float x1, x2;
    private View gameview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameview = findViewById(R.id.gameview);

        gameview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        db = new PlayerDB(this);

        //Creating all the button events
        btnP1Wins = (Button) findViewById(R.id.btnP1Wins);
        btnP2Wins = (Button) findViewById(R.id.btnP2Wins);
        btnTie = (Button) findViewById(R.id.btnTie);

        txtPlayer1 = (TextView) findViewById(R.id.txtPlayer1);


        if (((GameApplication) this.getApplication()).getFull_name_one()==null){
            //txtPlayer1.setText("Player 1: " + ((GameApplication) this.getApplication()).getFull_name_one());
            txtPlayer1.setText("Player 1: Not Selected");
        }
        else {
            txtPlayer1.setText("Player 1: " + ((GameApplication) this.getApplication()).getFull_name_one());
            player1picked=true;
        }

        txtPlayer2 = (TextView) findViewById(R.id.txtPlayer2);

        if (((GameApplication) this.getApplication()).getFull_name_two() == null){
            txtPlayer2.setText("Player 2: Not Selected");
        }
        else {
            txtPlayer2.setText("Player 2: " + ((GameApplication) this.getApplication()).getFull_name_two());
            player2picked=true;
        }


        //onTouch
        btnP1Wins.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        btnP2Wins.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        btnTie.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        //Add on the click listeners
        btnP1Wins.setOnClickListener(this);
        btnP2Wins.setOnClickListener(this);
        btnTie.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        p1last = ((GameApplication) this.getApplication()).getL_name_one();
        p1first = ((GameApplication) this.getApplication()).getF_name_one();
        p2last = ((GameApplication) this.getApplication()).getL_name_two();
        p2first = ((GameApplication) this.getApplication()).getF_name_two();

        switch (v.getId()) {
            case R.id.btnP1Wins:
                if (player1picked && player2picked) {
                    db.playerOneWins(p1first, p1last, p2first, p2last);
                    Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_one() + " Wins! " + ((GameApplication) this.getApplication()).getFull_name_two() + " Loses!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameActivity.this, ScoreBoardActivity.class);
                    intent.putExtra("sender","GameActivity");
                    startActivity(intent);
                } else {
                    playerCheck();
                }
                break;
            case R.id.btnP2Wins:
                if (player1picked && player2picked) {
                    db.playerTwoWins(p1first, p1last, p2first, p2last);
                    Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_two() + " Wins! " + ((GameApplication) this.getApplication()).getFull_name_one() + " Loses!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameActivity.this, ScoreBoardActivity.class);
                    intent.putExtra("sender","GameActivity");
                    startActivity(intent);
                } else {
                    playerCheck();
                }
                break;
            case R.id.btnTie:
                if (player1picked && player2picked) {
                    db.playersTie(p1first, p1last, p2first, p2last);
                    Toast.makeText(getApplicationContext(), "Tie game", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GameActivity.this, ScoreBoardActivity.class);
                    intent.putExtra("sender","GameActivity");
                    startActivity(intent);
                }
                else {
                    playerCheck();
                }
                break;
        }
    }



    private void playerCheck() {
        if (!player1picked && !player2picked) {
            Toast.makeText(getApplicationContext(), "Please Pick a set of players", Toast.LENGTH_LONG).show();
        }
        if (!player1picked) {
            Toast.makeText(getApplicationContext(), "Please Pick a Player 1", Toast.LENGTH_LONG).show();
        }
        if (!player2picked) {
            Toast.makeText(getApplicationContext(), "Please Pick a Player 2", Toast.LENGTH_LONG).show();
        }
    }
}
