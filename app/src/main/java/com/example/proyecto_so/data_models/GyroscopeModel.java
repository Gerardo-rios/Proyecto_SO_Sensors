package com.example.proyecto_so.data_models;

public class GyroscopeModel implements SensorModel{

    private float [] data;

    public GyroscopeModel() {
        this.data = new float[3];
    }

    public GyroscopeModel(float [] d) {
        this.data = d;
    }

    public void setX(float x) {
        this.data[0] = x;
    }

    public void setY(float y) {
        this.data[1] = y;
    }

    public void setZ(float z) {
        this.data[2] = z;
    }

    public void setData(float [] d){
        this.data = d;
    }

    public float [] getData(){
        return this.data;
    }

    @Override
    public String getNameSensor() {
        return "GYROSCOPE";
    }
}
