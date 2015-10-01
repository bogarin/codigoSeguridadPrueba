package com.example.bogarin.appmascompleto;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;

import java.util.List;

/**
 * Created by bogarin on 30/09/15.
 */
public abstract class Recursos implements SensorEventListener{
    private MediaPlayer alarma;
    private TextToSpeech ablar;
    private SensorManager sensorAprosimidad;
    private List<Sensor> listaSensores;
    private String password;

    public Recursos(){
        password="ramon";
    }

    public void setSensorAprosimidad(SensorManager sensorAprosimidad){
        this.sensorAprosimidad = sensorAprosimidad;
    }

    public String getPassword() {
        return password;
    }

    public void setAlarma(MediaPlayer alarma){
        this.alarma=alarma;
    }

    public void sensorAprocimidad(){
        listaSensores= sensorAprosimidad.getSensorList(Sensor.TYPE_ALL);
        listaSensores = sensorAprosimidad.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorAprosimidad.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }

    }

    public void onSensorChanged(SensorEvent evento) {
        if (evento.values[0] > 5 && alarma.isPlaying()==true && alarma.isLooping()==true){
            alarma.setLooping(false);
            alarma.pause();
        }else {
            alarma.start();
            alarma.setLooping(true);
        }
    }

    public void detener(){
        alarma.stop();
        alarma.setLooping(false);
    }




}
