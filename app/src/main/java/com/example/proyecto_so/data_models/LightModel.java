package com.example.proyecto_so.data_models;

public class LightModel implements SensorModel{

    private float x;

    public LightModel() {
        this.x = 0;
    }

    public LightModel(float d){
        this.x = d;
    }

    public void setX(float data) {
        this.x = data;
    }

    public float getX(){
        return this.x;
    }

    @Override
    public String getNameSensor() {
        return "LIGHT";
    }
}
