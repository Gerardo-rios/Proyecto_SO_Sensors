package com.example.proyecto_so.threats;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_so.R;
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

    AcelerometerModel data = new AcelerometerModel();
    TextView data_accelerometer;

    public AcelerometerThread (Activity a) throws Exception{

        if (a == null){
            throw new Exception("NULL ACTIVITY ON  " + TAG);
        }
        this.activity = a;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.exit = false;
        data_accelerometer = a.findViewById(R.id.txt_dataA);

    }

    public void stop(){
        exit = true;
        sensorManager.unregisterListener(this.sensorEventListener);
    }


    @Override
    public void run() {

        //while (!exit){
            this.sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    data.setX(sensorEvent.values[0]);
                    data.setY(sensorEvent.values[1]);
                    data.setZ(sensorEvent.values[2]);
                    //Log.i(TAG, String.valueOf(data.getX()) + " || " + String.valueOf(data.getY()) + " || "+String.valueOf(data.getZ()));
                    data_accelerometer.setText(" -- " + data.getX() + " -- " + data.getY() + " -- " + data.getZ() + " -- ");
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        //}
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        //this.exit = false;
    }

    public AcelerometerModel getData() {
        return data;
    }

    public void setData(AcelerometerModel data) {
        this.data = data;
    }
}
