package com.example.sarsintitespiti1;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor accelerometer;
    private TextView txt_mevcutIvme, txt_gecmisIvme, txt_ivmedegisim;
    private double Ag_ort;
    private double gecmisIvme;
    private double Ag_ort_trev;
    private double ts= 0.025 ;
    private int orneksay=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        txt_mevcutIvme = findViewById(R.id.txt_mevcutIvme);
        txt_gecmisIvme = findViewById(R.id.txt_gecmisIvme);
        txt_ivmedegisim = findViewById(R.id.txt_Ivmelenme);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double accelerationChangeThreshold = 2.5;  // Ivme değişiminin min değeri
            double Ag = Math.sqrt((x * x + y * y + z * z));

            // 4 bellekli FIFO buffer
            Ag_ort = (Ag_ort * 3 + Ag) / 4;

            // Türev filtresiyle yerçekimi ivmesinin etkisini yok et
            Ag_ort_trev = Math.abs(Ag_ort - gecmisIvme) / ts;

            //   Bildirim olarak gönder
            if (Ag_ort_trev > accelerationChangeThreshold && orneksay >= 40) {
                orneksay = 0;
                Toast.makeText(getApplicationContext(), "Deprem sarsıntısı tespit edildi! Lütfen güvenli bir yere gidin.", Toast.LENGTH_SHORT).show();

            }
            gecmisIvme = Ag_ort;

            orneksay++;
            if (orneksay > 40) {
                orneksay = 0;
            }

            txt_mevcutIvme.setText("İvme = " + Ag);
            txt_ivmedegisim.setText("İvme değişimi = " + Ag_ort_trev);
            txt_gecmisIvme.setText("Geçmiş ivme = " + gecmisIvme);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something when accuracy changes
    }
}

