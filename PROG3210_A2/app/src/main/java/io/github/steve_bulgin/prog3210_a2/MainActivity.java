/* FileName: MainActivity.java
 * Purpose: Handles MainActivity on app load
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Added click events for buttons
 *      Steven Bulgin, 2016.11.12: Call PlayerdB in on create for testing
 *      Steven Bulgin, 2017.02.25: Added in green btns if player is selected on main 
 *                              else standard button
 */

package io.github.steve_bulgin.prog3210_a2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    //Variables
    private Button btnStartGame, btnScoreBoard, btnPlayerOne, btnPlayerTwo, btnPlayerNew, btnDeletePlayers;
    PlayerDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB create
        database = new PlayerDB(this);

        //Creating all the button events
        btnStartGame = (Button) findViewById(R.id.start);
        btnScoreBoard = (Button) findViewById(R.id.scoreboard);
        btnPlayerOne = (Button) findViewById(R.id.player1);
        btnPlayerTwo = (Button) findViewById(R.id.player2);
        btnPlayerNew = (Button) findViewById(R.id.add_player);
        btnDeletePlayers = (Button) findViewById(R.id.delete_player);

        //Add on the click listeners
        btnStartGame.setOnClickListener(this);
        btnScoreBoard.setOnClickListener(this);
        btnPlayerOne.setOnClickListener(this);
        btnPlayerTwo.setOnClickListener(this);
        btnPlayerNew.setOnClickListener(this);
        btnDeletePlayers.setOnClickListener(this);

        //Set player button background
        //Player 1
        if (((GameApplication) this.getApplication()).getFull_name_one() != null) {
            btnPlayerOne.setBackgroundResource(R.drawable.greenbutton);
            btnPlayerOne.setText("player 1: " + ((GameApplication) this.getApplication()).getFull_name_one());
        }
        else {
            btnPlayerOne.setBackgroundResource(R.drawable.buttonshape);
            btnPlayerOne.setText("player 1");
        }

        //Player 2
        if (((GameApplication) this.getApplication()).getFull_name_two() != null) {
            btnPlayerTwo.setBackgroundResource(R.drawable.greenbutton);
            btnPlayerTwo.setText("player 2: " + ((GameApplication) this.getApplication()).getFull_name_two());
        }
        else {
            btnPlayerTwo.setBackgroundResource(R.drawable.buttonshape);
            btnPlayerTwo.setText("player 2");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
                break;
            case R.id.scoreboard:
                Intent intent = new Intent(MainActivity.this, ScoreBoardActivity.class);
                intent.putExtra("sender","MainActivity");
                startActivity(intent);
                break;
            case R.id.player1:
                //Toast.makeText(getApplicationContext(), "Player 1", Toast.LENGTH_SHORT).show();
            	startActivity(new Intent(getApplicationContext(), PlayerOneActivity.class));
                break;
            case R.id.player2:
                //Toast.makeText(getApplicationContext(), "Player 2", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), PlayerTwoActivity.class));
                break;
            case R.id.add_player:
                //Toast.makeText(getApplicationContext(), "Add Player", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AddPlayerActivity.class));
                break;
            case R.id.delete_player:
                startActivity(new Intent(getApplicationContext(), DeletePlayerActivity.class));
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
