package io.github.steve_bulgin.prog3210_a2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.util.Log;


public class DBAdapter extends SimpleAdapter {

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */

    LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> results;
    private Context context;

    public DBAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);

        this.context = context;
        this.results = (ArrayList<HashMap<String, String>>) data;
        inflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View item = super.getView(position,convertView,parent);

        

        if (results.get(position).get("full_name").toString().equals("Delete All") && results.get(position).get("l_name") == null) {
            item.setBackgroundResource(R.drawable.redbutton);    
        }
        else {
            item.setBackgroundResource(R.drawable.buttonshape);
        }
        return item;
    }
}
