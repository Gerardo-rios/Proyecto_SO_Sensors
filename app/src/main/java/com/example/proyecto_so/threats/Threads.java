package com.example.proyecto_so.threats;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_so.R;
import com.example.proyecto_so.data_models.AcelerometerModel;
import com.example.proyecto_so.data_models.LightModel;
import com.example.proyecto_so.data_models.ProximityModel;
import com.example.proyecto_so.data_models.SensorModel;
import com.example.proyecto_so.enumSensor.SensorsEnum;
import com.example.proyecto_so.recycler.Adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Threads implements Runnable, Serializable {

    private String TAG = Threads.class.getName();

    private Activity activity;
    private volatile boolean exit;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private SensorModel data;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Threads> sensores;

    public Threads(Activity a, SensorsEnum type) throws Exception {

        if (a == null) {
            throw new Exception("NULL ACTIVITY ON  " + TAG);
        }

        this.activity = a;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);

        switch (type) {
            case ACCELEROMETER:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                this.data = new AcelerometerModel();
                break;
            case LIGHT:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                this.data = new LightModel();
                break;
            case PROXIMITY:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                this.data = new ProximityModel();
                break;
        }

        this.exit = false;
        this.recyclerView = activity.findViewById(R.id.recycler);


    }


    public void stop() {
        exit = true;
        sensorManager.unregisterListener(this.sensorEventListener);
    }

    public void setData(float data[]) {

        this.sensores = new ArrayList<>();

        if (this.data.getNameSensor().equals("ACCELEROMETER")) {
            this.data = new AcelerometerModel(data);
            sensores.add(this);
        } else if (this.data.getNameSensor().equals("LIGHT")) {
            this.data = new LightModel(data);
            sensores.add(this);
        } else {
            this.data = new ProximityModel(data);
            sensores.add(this);
        }
        try {
            Log.i(TAG, String.valueOf(data[0]) + String.valueOf(data[1]) + String.valueOf(data[2]));
        } catch (IndexOutOfBoundsException ex) {
            Log.i(TAG, "Te saliste del limite la concha de tu hermana");
        }

        adapter = new Adapter(sensores);
        recyclerView.setAdapter(adapter);

    }

    public SensorModel getModelo() {
        return this.data;
    }

    @Override
    public void run() {
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                setData(sensorEvent.values);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
}
