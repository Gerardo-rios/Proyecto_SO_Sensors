package com.example.proyecto_so.threats;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_so.R;
import com.example.proyecto_so.data_models.GyroscopeModel;
import com.example.proyecto_so.data_models.LightModel;
import com.example.proyecto_so.data_models.ProximityModel;
import com.example.proyecto_so.data_models.SensorModel;
import com.example.proyecto_so.enumSensor.SensorsEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Threads implements Runnable, Serializable {

    private String TAG = Threads.class.getName();

    private Activity activity;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    private SensorModel modelo;

    TextView l1, c1, r1, l2, l3;


    public Threads(Activity a, SensorsEnum type) throws Exception {

        if (a == null) {
            throw new Exception("NULL ACTIVITY ON  " + TAG);
        }

        this.activity = a;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);

        switch (type) {
            case GYROSCOPE:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                this.modelo = new GyroscopeModel();
                break;
            case LIGHT:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                this.modelo = new LightModel();
                break;
            case PROXIMITY:
                this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                this.modelo = new ProximityModel();
                break;
        }

        l1 = activity.findViewById(R.id.txt_dataLeft1);
        l2 = activity.findViewById(R.id.txt_dataLeft2);
        l3 = activity.findViewById(R.id.txt_dataLeft3);
        c1 = activity.findViewById(R.id.txt_dataCenter1);
        r1 = activity.findViewById(R.id.txt_dataRight1);


    }


    public void stop() {
        sensorManager.unregisterListener(this.sensorEventListener);
    }

    public void setData(float data[]) {

        if (this.modelo.getNameSensor().equals("GYROSCOPE")) {

            l1.setText(String.valueOf(data[0]));
            c1.setText(String.valueOf(data[1]));
            r1.setText(String.valueOf(data[2]));

        } else if (this.modelo.getNameSensor().equals("LIGHT")) {

            l2.setText(String.valueOf(data[0]));

        } else {

            l3.setText(String.valueOf(data[0]));

        }

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
