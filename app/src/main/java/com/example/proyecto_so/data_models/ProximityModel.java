package com.example.proyecto_so.data_models;

public class ProximityModel implements SensorModel{


    private float x [];

    public ProximityModel(){
        this.x = new float[3];
    }

    public ProximityModel(float [] d ){
        this.x = d;
    }

    @Override
    public String getNameSensor() {
        return "PROXIMITY";
    }

    @Override
    public Integer getImage() {
        return 0;
    }

    @Override
    public float[] getData() {
        return this.x;
    }
}
