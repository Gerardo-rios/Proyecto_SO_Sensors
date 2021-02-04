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
import com.example.proyecto_so.threats.SensorThread;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderSensors>{

    public final String TAG = Adapter.class.getName();

    List<SensorThread> sensors;
    Thread thread;

    public Adapter (List<SensorThread> sensores){
        this.sensors = sensores;
    }


    @NonNull
    @Override
    public ViewHolderSensors onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, null);

        return new ViewHolderSensors(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSensors holder, final int position) {

        holder.nameSensor.setText(sensors.get(position).getModel().getNameSensor());
        holder.sensorImage.setImageResource(sensors.get(position).getModel().getImage());

        try {
            holder.left.setText(String.valueOf(sensors.get(position).getModel().getData()[0]));
            holder.center.setText(String.valueOf(sensors.get(position).getModel().getData()[1]));
            holder.right.setText(String.valueOf(sensors.get(position).getModel().getData()[2]));
        } catch (IndexOutOfBoundsException ex){
            Log.i(TAG, ex.getMessage());
        }

        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, sensors.get(position).getModel().getNameSensor() + " START");
                try {
                    thread = new Thread(sensors.get(position));
                    thread.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, sensors.get(position).getModel().getNameSensor() + " STOP");
                if (sensors.get(position) != null){
                    sensors.get(position).stop();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public static class ViewHolderSensors extends RecyclerView.ViewHolder{

        ImageView btnStart, btnStop, btnRestart, sensorImage;
        TextView center, left, right, nameSensor;
        ScrollView scrollView;

        public ViewHolderSensors(@NonNull View itemView) {

            super(itemView);

            btnStart = itemView.findViewById(R.id.btn_start);
            btnStop = itemView.findViewById(R.id.btn_stop);
            //btnRestart = itemView.findViewById(R.id.btn_restart);
            sensorImage = itemView.findViewById(R.id.imageSensor);
            center = itemView.findViewById(R.id.txt_dataCenter);
            left = itemView.findViewById(R.id.txt_dataLeft);
            right = itemView.findViewById(R.id.txt_dataRight);
            nameSensor = itemView.findViewById(R.id.txt_namesensor);
            scrollView = itemView.findViewById(R.id.scrollView);


        }

    }

}
