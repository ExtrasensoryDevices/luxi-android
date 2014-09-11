package com.esdevices.luxi;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MyActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mLightSensor;
    private static final String TAG = "main activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == event.sensor.TYPE_LIGHT) {

            Log.d(TAG, String.format("sensor=%d", event.sensor.getType()));

            Log.d(TAG, String.format("event.values len=%d", event.values.length));
            Log.d(TAG, String.format("event.values=%f %f %f", event.values[0], event.values[1], event.values[2]));

            TextView tv1 = (TextView)findViewById(R.id.light_value_1);
            TextView tv2 = (TextView)findViewById(R.id.light_value_2);
            TextView tv3 = (TextView)findViewById(R.id.light_value_3);

            tv1.setText(Float.toString(event.values[0]));
            tv2.setText(Float.toString(event.values[1]));
            tv3.setText(Float.toString(event.values[2]));

        }

    }
}