/* FileName: PlayerDB.java
 * Purpose: DB handler for project
 * Revision History
 *         Steven Bulgin, 2016.11.12: Created
 *         Steven Bulgin, 2016.11.12: Have db creating, added some dummy data for testing
 */

package io.github.steve_bulgin.prog3210_a2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "playerlist.db";

        private SQLiteDatabase db;

        public PlayerDB(Context context) {
            super(context, DB_NAME, null, 1);
            db = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL("CREATE TABLE tbl_players (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                       " l_name VARCHAR NOT NULL, f_name VARCHAR NOT NULL, wins INTEGER NOT NULL DEFAULT 0, " +
                       " losses INTEGER NOT NULL  DEFAULT 0, ties INTEGER NOT NULL  DEFAULT 0)");
            //dummy data
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Thole','Josh',1,5,4)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Travis','Devon',6,2,8)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Smoak','Justin',7,3,0)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Colon','Bartollo',2,7,4)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tbl_players");
            onCreate(db);
        }

    ArrayList<HashMap<String, String>> getPlayers(){
        ArrayList<HashMap<String, String>> results =
                new ArrayList<HashMap<String, String>>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT l_name,f_name, wins, losses, ties FROM players",null );
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("l_name", cursor.getString(0));
            map.put("f_name", cursor.getString(1));
            map.put("wins", cursor.getString(2));
            map.put("losses", cursor.getString(3));
            map.put("ties", cursor.getString(4));
            results.add(map);
        }
        if (cursor != null)
            cursor.close();
        db.close();

        return results;
    }
    }
