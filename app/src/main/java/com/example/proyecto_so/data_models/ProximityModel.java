package com.example.proyecto_so.data_models;

public class ProximityModel implements SensorModel {

    private float x;

    public ProximityModel() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public String getNameSensor() {
        String res = "Sensor de Proximidad";
        return res;
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
