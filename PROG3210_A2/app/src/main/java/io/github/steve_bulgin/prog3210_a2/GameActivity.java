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
    private String name = "Thole";
    private String name2 = "Travis";


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
        txtPlayer1.setText("Player 1: " + getIntent().getExtras().getString("Test"));

        //Add on the click listeners
        btnP1Wins.setOnClickListener(this);
        btnP2Wins.setOnClickListener(this);
        btnTie.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnP1Wins:
                db.playerOneWins(name, name2);
                Toast.makeText(getApplicationContext(), "Player 1 Wins", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), GameActivity.class));
                break;
            case R.id.btnP2Wins:
                Toast.makeText(getApplicationContext(), "Player 2 Wins", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), ScoreBoardActivity.class));
                break;
            case R.id.btnTie:
                Toast.makeText(getApplicationContext(), "Tie game", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), PlayerOneActivity.class));
                break;
        }
    }
}
