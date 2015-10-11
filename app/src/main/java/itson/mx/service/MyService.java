package itson.mx.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    WifiManager mainWifi;

    /**
     * Calls the Method to scan for wifi network
     */
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            wifiScan();
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startScan();
        return START_STICKY;
    }

    /**
     * Creates a new Handler with the main looper
     * @return The new Handler
     */
    private Handler getMainHandler() {
        return new Handler(getMainLooper());
    }

    /**
     * Post the task over the new Handler
     */
    private void startScan(){
        getMainHandler().postDelayed(task, 3*1000);
    }

    /**
     * Scans for wifi networks
     */
    private void wifiScan() {
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (mainWifi.isWifiEnabled()){
            mainWifi.startScan();
            startScan();
        }
    }
}
