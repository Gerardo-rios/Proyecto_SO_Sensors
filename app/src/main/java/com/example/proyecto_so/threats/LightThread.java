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
import com.example.proyecto_so.data_models.LightModel;
import com.example.proyecto_so.data_models.SensorModel;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

public class LightThread implements SensorThread, Serializable {

    private String TAG = LightThread.class.getName();
    private Activity activity;
    private AtomicBoolean exit;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener eventListener;

    private LightModel data;
    TextView data_light;

    public LightThread(Activity a) throws Exception{

        if (a == null){
            throw new Exception("Null activity on  " + TAG);
        }
        this.activity = a;
        this.sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.exit = new AtomicBoolean(false);
        //data_light = activity.findViewById(R.id.txt_dataL);
        this.data = new LightModel();

    }

    @Override
    public void run() {

        while (!exit.get()){

            this.eventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    data = new LightModel();
                    data.setX(sensorEvent.values[0]);
                    Log.i(TAG, String.valueOf(sensorEvent.values[0]));
                    //data_light.setText(" -- " + data.getX() + " -- ");
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };

        }

        this.sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void stop(){
        exit.set(true);
        sensorManager.unregisterListener(this.eventListener);
    }

    @Override
    public SensorModel getModel() {
        return data;
    }

}
