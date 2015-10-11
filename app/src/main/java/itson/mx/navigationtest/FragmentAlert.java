package itson.mx.navigationtest;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ulrick on 10/10/2015.
 */
public class FragmentAlert extends Fragment {

    TextView tvEmpty;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    List<String> alerts;

    public FragmentAlert(Context context) {
        alerts = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alerts, container, false);
        tvEmpty = (TextView) view.findViewById(R.id.empty);
        ActionButton actionButton = (ActionButton) view.findViewById(R.id.action_button);
        actionButton.setImageResource(R.drawable.fab_plus_icon);

        if(alerts.isEmpty()){
           tvEmpty.setVisibility(View.VISIBLE);
        }

        return view;
    }

}
