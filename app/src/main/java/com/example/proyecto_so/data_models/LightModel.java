package com.example.proyecto_so.data_models;

public class LightModel implements SensorModel{

    private float [] x;

    public LightModel() {
        this.x = new float[3];
    }

    public void setX(float x) {
        this.x[0] = x;
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
