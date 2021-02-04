package com.example.proyecto_so.data_models;

public class AcelerometerModel implements SensorModel{

    private float [] data;

    public AcelerometerModel() {
        this.data = new float[3];
    }

    public AcelerometerModel(float [] d) {
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

    @Override
    public String getNameSensor() {
        return "ACCELEROMETER";
    }

    @Override
    public Integer getImage() {
        return 0;
    }

    @Override
    public float[] getData() {
        return data;
    }
}
