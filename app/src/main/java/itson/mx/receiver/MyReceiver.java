package itson.mx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ulrick on 10/10/2015.
 */
public class MyReceiver extends BroadcastReceiver {

    WifiManager mainWifi;

    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO: catch the scan of wifi network and then do something weid :P
            mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        Log.i("Networks", mainWifi.getScanResults().size()+"");
    }
}
