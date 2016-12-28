/* FileName: PlayerDB.java
 * Purpose: DB handler for project
 * Revision History
 *         Steven Bulgin, 2016.11.12: Created
 *         Steven Bulgin, 2016.11.12: Have db creating, added some dummy data for testing
 *         Steven Bulgin, 2016.11.17: Removed not null constraint on f_name for testing
 *         Steven Bulgin, 2016.11.17: Insert works. Update to accept f_name and l_name
 */

package io.github.steve_bulgin.prog3210_a2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDB {
    public static final String DB_NAME = "playerlist.db";
    public static final int DB_VERSION = 1;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL("CREATE TABLE tbl_players (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " l_name VARCHAR NOT NULL, f_name VARCHAR, wins INTEGER NOT NULL DEFAULT 0, " +
                    " losses INTEGER NOT NULL  DEFAULT 0, ties INTEGER NOT NULL  DEFAULT 0)");

            //dummy data
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Thole','Josh',1,5,4)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Travis','Devon',6,2,8)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Smoak','Justin',7,3,0)");
            db.execSQL("INSERT INTO tbl_players(l_name,f_name,wins,losses,ties) VALUES('Colon','Bartolo',2,7,4)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE \"tbl_players\"");
            Log.d("Player list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public PlayerDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWriteableDB();
        closeDB();
    }
    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    ArrayList<HashMap<String, String>> getPlayers(){
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        openReadableDB();
        Cursor cursor = db.rawQuery("SELECT l_name, f_name, wins, losses, ties FROM tbl_players",null );
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("l_name", cursor.getString(0));
            map.put("f_name", cursor.getString(1));
            map.put("wins", cursor.getString(2));
            map.put("losses", cursor.getString(3));
            map.put("ties", cursor.getString(4));
            data.add(map);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return data;
    }

    void insertPlayer(String f_name, String l_name)throws Exception{

        openWriteableDB();
        ContentValues content = new ContentValues();
        content.put("f_name", f_name);
        content.put("l_name", l_name);
        long nResult = db.insert("tbl_players",null, content);
        if(nResult == -1) throw new Exception("no data");
        closeDB();
    }

    void playerOneWins(String playerone_first, String playerone_last, String playertwo_first, String playertwo_last) {
        openWriteableDB();
        db.execSQL("UPDATE tbl_players SET wins=wins+1 WHERE l_name='" + playerone_last + "' AND f_name='" + playerone_first + '\'');
        db.execSQL("UPDATE tbl_players SET losses=losses+1 WHERE l_name='" + playertwo_last + "' AND f_name='" + playertwo_first + '\'');
        closeDB();
    }

    void playerTwoWins(String playerone_first, String playerone_last, String playertwo_first, String playertwo_last) {
        openWriteableDB();
        db.execSQL("UPDATE tbl_players SET wins=wins+1 WHERE l_name='" + playertwo_last + "' AND f_name='" + playertwo_first + '\'');
        db.execSQL("UPDATE tbl_players SET losses=losses+1 WHERE l_name='" + playerone_last + "' AND f_name='" + playerone_first + '\'');
        closeDB();
    }

    void playersTie(String playerone_first, String playerone_last, String playertwo_first, String playertwo_last) {
        openWriteableDB();
        db.execSQL("UPDATE tbl_players SET ties=ties+1 WHERE (l_name='" + playerone_last + "' AND f_name='" + playerone_first +"') OR (l_name='" + playertwo_last + "' AND f_name='" + playertwo_first + "')");
        closeDB();
    }

}
