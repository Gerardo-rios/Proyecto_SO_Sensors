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
import com.example.proyecto_so.data_models.SensorModel;

import java.io.Serializable;

/*
* ESTA CLASE FUNCIONA BIEN, REALIZANDO EL SENSO Y DETENENDOSE
* EXISTEN INCONVENIENTES AL MOMENTO DE VOLVER A INICIAR EL HILO, SE DEBE PRESIONAR START Y LUEGO STOP PARA QUE VUELVA A CORRER
* SI SE PRESIONA EL BOTON START 2 VECES ES IMPOSIBLE DETENERLO
* */

public class AcelerometerThread implements Serializable, SensorThread {

    private String TAG = AcelerometerThread.class.getName();
    private Activity activity;
    private volatile boolean exit;

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    AcelerometerModel data;
    TextView data_accelerometer;

    public AcelerometerThread (Activity a) throws Exception{

        if (a == null){
            throw new Exception("NULL ACTIVITY ON  " + TAG);
        }
        this.activity = a;
        this.sensorManager = (SensorManager) this.activity.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.exit = false;
        //data_accelerometer = a.findViewById(R.id.txt_dataA);
        this.data = new AcelerometerModel();
    }

    public void stop(){
        exit = true;
        sensorManager.unregisterListener(this.sensorEventListener);
    }

    @Override
    public SensorModel getModel() {
        return data;
    }

    public SensorModel setData(float x, float y, float z){
        this.data = new AcelerometerModel();
        this.data.setX(x);
        this.data.setY(y);
        this.data.setZ(z);
        Log.i(TAG, String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
        return data;
    }

    @Override
    public void run() {

        while (!exit){

            this.sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {

                    setData(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);


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
