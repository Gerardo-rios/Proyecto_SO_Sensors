package com.example.proyecto_so.data_models;

public class AcelerometerModel implements SensorModel{

    private float x;
    private float y;
    private float z;

    public AcelerometerModel(){

    }

    public AcelerometerModel(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
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
