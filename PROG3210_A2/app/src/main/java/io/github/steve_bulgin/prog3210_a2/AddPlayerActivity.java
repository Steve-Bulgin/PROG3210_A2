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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayerActivity extends Activity implements View.OnClickListener {

    //Variables
    EditText txtName;
    private Button btnAddSubmit;
    private PlayerDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        txtName = (EditText) findViewById(R.id.txtName);
        btnAddSubmit = (Button) findViewById(R.id.btnAddSubmit);

        db = new PlayerDB(this);

        //Click listener
        btnAddSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddSubmit) {
            String new_name = txtName.getText().toString();
            //Toast.makeText(getApplicationContext(), new_name, Toast.LENGTH_SHORT).show();

            try {
                db.insertPlayer(new_name);
                Toast.makeText(getApplicationContext(), "Insert made", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ScoreBoardActivity.class));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
