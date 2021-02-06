package com.example.proyecto_so.data_models;

public class ProximityModel implements SensorModel {

    private float x;

    public ProximityModel() {
        this.x = 0;
    }

    public ProximityModel(float d) {
        this.x = d;
    }

    public void setX(float d) {
        this.x = d;
    }

    public float getX() {
        return this.x;
    }

    @Override
    public String getNameSensor() {
        return "PROXIMITY";
    }

}
