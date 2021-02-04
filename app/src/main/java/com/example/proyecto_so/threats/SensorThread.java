package com.example.proyecto_so.threats;

import com.example.proyecto_so.data_models.SensorModel;

public interface SensorThread extends Runnable{

    @Override
    void run();

    void stop();

    SensorModel getModel();

}
