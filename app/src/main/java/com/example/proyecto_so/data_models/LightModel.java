package com.example.proyecto_so.data_models;

public class LightModel implements SensorModel{

    private float [] x;

    public LightModel() {
        this.x = new float[3];
    }

    public LightModel(float [] d){
        this.x = d;
    }

    public void setX(float [] data) {
        this.x = data;
    }

    @Override
    public String getNameSensor() {
        return "LIGHT";
    }

    @Override
    public Integer getImage() {
        return 0;
    }

    @Override
    public float[] getData() {
        return x;
    }
}
