/* FileName: AddPlayerActivity.java
 * Purpose: Handles logic for adding player
 * Revision History
 * 		Steven Bulgin, 2016.11.09: Created
 *      Steven Bulgin, 2016.11.17: Insert works. Update view with Last name field 
 */

package io.github.steve_bulgin.prog3210_a2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayerActivity extends Activity implements View.OnClickListener {

    //Variables
    private EditText txtFName, txtLName;
    private View addview;
    private Button btnAddSubmit;
    private float x1, x2;
    private PlayerDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        addview = findViewById(R.id.addview);

        txtFName = (EditText) findViewById(R.id.txtFName);
        txtLName = (EditText) findViewById(R.id.txtLName);
        btnAddSubmit = (Button) findViewById(R.id.btnAddSubmit);

        db = new PlayerDB(this);

        addview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }
        });

        //Click listener
        btnAddSubmit.setOnClickListener(this);

        btnAddSubmit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }
        });

        txtFName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }
        });

        txtLName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        if ((x1 - x2) < -150) {
                            Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddSubmit) {
            String f_name = txtFName.getText().toString();
            String l_name = txtLName.getText().toString();

            if ((f_name != null && !f_name.isEmpty()) || (l_name != null && !l_name.isEmpty())) {

                try {
                    db.insertPlayer(f_name, l_name);
                    Toast.makeText(getApplicationContext(), "Insert made", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPlayerActivity.this, ScoreBoardActivity.class);
                    intent.putExtra("sender","AddPlayerActivity");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        }
    }
}
