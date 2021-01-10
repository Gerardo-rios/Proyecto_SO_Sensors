package com.example.proyecto_so.activities;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_so.R;
import com.example.proyecto_so.data_models.AcelerometerModel;
import com.example.proyecto_so.threats.AcelerometerThread;

import java.io.Serializable;
/*
* ESTA CLASE ES UN INTENTO DE REALIZAR EL SENSO, CON ACELEROMETRO PERO HAY INCOVENIENTES AL MOMENTO DE DETENER EL HILO
* SE NECESITA INICIAR LA ACTIVITY CON EL HILO CORRIENDO PARA QUE EL SENSORLISTENER NO SEA NULO
* */

public class Acelerometer extends AppCompatActivity implements Runnable, Serializable, View.OnClickListener {

    private String TAG = AcelerometerThread.class.getName();
    private volatile boolean exit = false;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    Thread thread;
    TextView x,y,z;
    Button sta, sto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        Obtener();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null){
            Toast.makeText(this, "SENSOR NULO", Toast.LENGTH_SHORT);
        }
        thread = new Thread(this);
        thread.start();
    }

    private void Obtener(){
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
        sta = findViewById(R.id.btn_accelerometerStart);
        sto = findViewById(R.id.btn_accelerometerStop);
        sta.setOnClickListener(this);
        sto.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        if (sensorEventListener == null){
            Toast.makeText(this, "LISTENER NULO", Toast.LENGTH_SHORT);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        this.sensorManager.unregisterListener(this.sensorEventListener);
        super.onPause();
    }

    public void stop(){
        this.exit = true;
        this.sensorManager.unregisterListener(this.sensorEventListener);
    }

    @Override
    public void run() {

        while (!exit){
            this.sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float a, b, c;
                    a = sensorEvent.values[0];
                    b = sensorEvent.values[1];
                    c = sensorEvent.values[2];

                    x.setText(String.valueOf(a));
                    y.setText(String.valueOf(b));
                    z.setText(String.valueOf(c));
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_accelerometerStart:
                Log.i(TAG, "ACELEROMETER START");
                /*if (thread != null){
                    thread.start();
                }*/
                break;

            case R.id.btn_accelerometerStop:
                Log.i(TAG, "ACELEROMETER STOP");
                if (thread != null){
                    stop();
                    try {
                        thread.join(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Ya esta detenido", Toast.LENGTH_SHORT);
                }
                break;
        }

    }
}
