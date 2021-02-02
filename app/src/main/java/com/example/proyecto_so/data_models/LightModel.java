package com.example.proyecto_so.data_models;

public class LightModel implements SensorModel{

    private float x;

    public LightModel() {

    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    @Override
    public String getNameSensor() {
        return null;
    }

    @Override
    public Integer getImage() {
        return null;
    }

    @Override
    public float[] getData() {
        return new float[0];
    }
}
