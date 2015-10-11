package itson.mx.adapter;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

import itson.mx.model.NetworkModel;
import itson.mx.navigationtest.R;

/**
 * Created by Ulrick on 11/10/2015.
 */
public class MyAdapter extends ArrayAdapter<NetworkModel> {

    private final List<NetworkModel> list;
    private final Activity context;

    public MyAdapter(Activity context, List<NetworkModel> list) {
        super(context, R.layout.network_row_layout, list);
        this.context = context;
        this.list = list;
    }
}
