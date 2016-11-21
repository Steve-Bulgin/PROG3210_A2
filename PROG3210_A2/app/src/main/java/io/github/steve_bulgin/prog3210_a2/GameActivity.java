/* FileName: GameActivity.java
 * Purpose: Handles any logic for the game layout
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private String name1;
    private String name2;
    private boolean player1picked = false;
    private boolean player2picked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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




        //Add on the click listeners
        btnP1Wins.setOnClickListener(this);
        btnP2Wins.setOnClickListener(this);
        btnTie.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        name1 = ((GameApplication) this.getApplication()).getL_name_one();
        name2 = ((GameApplication) this.getApplication()).getL_name_two();

        switch (v.getId()) {
            case R.id.btnP1Wins:
                if (player1picked && player2picked) {
                    db.playerOneWins(name1, name2);
                    Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_one() + " Wins! " + ((GameApplication) this.getApplication()).getFull_name_two() + " Loses!", Toast.LENGTH_SHORT).show();
                } else {
                    playerCheck();
                }
                break;
            case R.id.btnP2Wins:
                if (player1picked && player2picked) {
                    db.playerTwoWins(name1, name2);
                    Toast.makeText(getApplicationContext(), ((GameApplication) this.getApplication()).getFull_name_two() + " Wins! " + ((GameApplication) this.getApplication()).getFull_name_one() + " Loses!", Toast.LENGTH_SHORT).show();
                } else {
                    playerCheck();
                }
                break;
            case R.id.btnTie:
                if (player1picked && player2picked) {
                    db.playersTie(name1, name2);
                    Toast.makeText(getApplicationContext(), "Tie game", Toast.LENGTH_SHORT).show();
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
