package com.example.bogarin.appmascompleto;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Recursos obj;
    private EditText password;
    private Boolean vandera=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(EditText)findViewById(R.id.password);
        obj = new Recursos() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void activar(View view){
        obj.setSensorAprosimidad((SensorManager) getSystemService(SENSOR_SERVICE));
        obj.setAlarma(MediaPlayer.create(this, R.raw.alarma));
        obj.sensorAprocimidad();
        vandera=true;
    }

    public void desactivar(View view){
        if (obj.getPassword().compareTo(password.getText().toString())==0) {
            if (vandera){
                Toast.makeText(getApplicationContext(),"deteniendo programa", Toast.LENGTH_SHORT).show();
                obj.detener();
                vandera=false;
            }else{
                Toast.makeText(getApplicationContext(),"programa no esta en ejecucion", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),"contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
        password.setText("");
    }
}


