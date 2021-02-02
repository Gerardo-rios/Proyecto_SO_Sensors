package com.example.proyecto_so.recycler;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_so.R;
import com.example.proyecto_so.data_models.SensorModel;
import com.example.proyecto_so.threats.AcelerometerThread;
import com.example.proyecto_so.threats.LightThread;




public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderSensors>{

    public final String TAG = Adapter.class.getName();
    private AcelerometerThread acelerometerThread;
    private Thread threadA;
    private Thread threadL;
    private LightThread lightThread;

    SensorModel sensor;
    Activity activity;

    public Adapter (SensorModel modelo, Activity activity){
        this.sensor = modelo;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolderSensors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, null);

        return new ViewHolderSensors(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSensors holder, int position) {

        float [] datos = sensor.getData();
        float x = 0, y = 0 , z = 0;
        for (int i = 0; i < datos.length; i++){
            x = datos[0];
            y = datos[1];
            z = datos[2];
        }

        holder.nameSensor.setText(sensor.getNameSensor());
        holder.sensorImage.setImageResource(sensor.getImage());
        holder.left.setText(String.valueOf(x));
        holder.center.setText(String.valueOf(y));
        holder.center.setText(String.valueOf(z));
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i(TAG, "ACELEROMETER START");
                try {
                    acelerometerThread = new AcelerometerThread(activity);
                    threadA = new Thread(acelerometerThread);
                    threadA.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i(TAG, "ACELEROMETER STOP");
                if (acelerometerThread != null){
                    acelerometerThread.stop();
                    //threadA.stop();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderSensors extends RecyclerView.ViewHolder{

        ImageView btnStart, btnStop, btnRestart, sensorImage;
        TextView center, left, right, nameSensor;
        ScrollView scrollView;

        public ViewHolderSensors(@NonNull View itemView) {

            super(itemView);

            btnStart = itemView.findViewById(R.id.btn_start);
            btnStop = itemView.findViewById(R.id.btn_stop);
            btnRestart = itemView.findViewById(R.id.btn_restart);
            sensorImage = itemView.findViewById(R.id.imageSensor);
            center = itemView.findViewById(R.id.txt_dataCenter);
            left = itemView.findViewById(R.id.txt_dataLeft);
            right = itemView.findViewById(R.id.txt_dataRight);
            nameSensor = itemView.findViewById(R.id.txt_namesensor);
            scrollView = itemView.findViewById(R.id.scrollView);


        }

    }

}
