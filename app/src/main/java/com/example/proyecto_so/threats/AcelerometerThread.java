package com.example.proyecto_so.threats;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import com.example.proyecto_so.data_models.AcelerometerModel;

import java.io.Serializable;

/*
* ESTA CLASE FUNCIONA BIEN, REALIZANDO EL SENSO Y DETENENDOSE
* EXISTEN INCONVENIENTES AL MOMENTO DE VOLVER A INICIAR EL HILO, SE DEBE PRESIONAR START Y LUEGO STOP PARA QUE VUELVA A CORRER
* SI SE PRESIONA EL BOTON START 2 VECES ES IMPOSIBLE DETENERLO
* */

public class AcelerometerThread implements Runnable, Serializable {

    private String TAG = AcelerometerThread.class.getName();
    private Activity activity;
    private volatile boolean exit;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    public AcelerometerThread (Activity activity) throws Exception{

        if (activity == null){
            throw new Exception("NULL ACTIVITY");
        }
        this.activity = activity;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.exit = false;
    }

    public void stop(){
        exit = true;
        sensorManager.unregisterListener(this.sensorEventListener);
    }


    @Override
    public void run() {

        while (!exit){
            this.sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    AcelerometerModel data = new AcelerometerModel(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                    Log.i(TAG, String.valueOf(data.getX()) + " || " + String.valueOf(data.getY()) + " || "+String.valueOf(data.getZ()));
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        //this.exit = false;
    }

}
