package com.example.xinyue.rotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyue.rotationdemo.RotationDetector.OnRotationGestureListener;

public class MainActivity extends AppCompatActivity implements RotationDetector.OnRotationGestureListener{

    private RotationDetector mRotationDetector;
    private TextView gRotateTextView;
    //private ImageView gImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gRotateTextView = (TextView)findViewById(R.id.rotateTextView);
        //gImageView = (ImageView)findViewById(R.id.imageView);
        mRotationDetector = new RotationDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt){
        mRotationDetector.onTouchEvent(evt);
        return super.onTouchEvent(evt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnRotation(RotationDetector rotationDetector) {
        float angle = mRotationDetector.getAngle();
        gRotateTextView.setText(String.valueOf(angle));
    }
}
