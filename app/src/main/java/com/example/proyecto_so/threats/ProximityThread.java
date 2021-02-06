package com.example.proyecto_so.threats;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import com.example.proyecto_so.R;
import com.example.proyecto_so.data_models.ProximityModel;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProximityThread implements Runnable, Serializable {

    private String TAG = ProximityThread.class.getName();
    private Activity activity;
    private AtomicBoolean exit;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener eventListener;

    private ProximityModel data = new ProximityModel();
    TextView data_proximity;

    public ProximityThread(Activity a) throws Exception {

        if (a == null) {
            throw new Exception("Null activity on  " + TAG);
        }
        this.activity = a;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        this.exit = new AtomicBoolean(false);
        data_proximity = a.findViewById(R.id.txt_dataP);

    }

    @Override
    public void run() {
        //while (!exit.get()) {
            this.eventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    Log.i(TAG, "Hola entro en el run");
                    data.setX(sensorEvent.values[0]);
                    Log.i(TAG, String.valueOf(data.getX()));
                    data_proximity.setText(" -- " + data.getX() + " -- ");
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }
            };
        //}
        this.sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        exit.set(true);
        sensorManager.unregisterListener(this.eventListener);
    }
}
