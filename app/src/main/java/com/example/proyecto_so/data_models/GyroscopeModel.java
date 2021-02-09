package com.example.proyecto_so.data_models;

public class GyroscopeModel implements SensorModel{

    private float [] data;

    public GyroscopeModel() {
        this.data = new float[5];
    }

    public GyroscopeModel(float[] data) {
        this.data = data;
    }

    // Velocidades Angulares en los diferentes ejes / s
    public void setWX(float wx){
        this.data[0] = wx;
    }

    public void setWY(float wy){
        this.data[1] = wy;
    }

    public void setWZ(float wz){
        this.data[2] = wz;
    }

    //Derivada estimada alrededor de un eje / s
    public void setX(float x){
        this.data[3] = x;
    }

    public void setY(float y){
        this.data[4] = y;
    }

    public void setZ(float z){
        this.data[5] = z;
    }

    // Métodos de la interfaz del sensor
    @Override
    public String getNameSensor() {
        return "GYROSCOPE";
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
