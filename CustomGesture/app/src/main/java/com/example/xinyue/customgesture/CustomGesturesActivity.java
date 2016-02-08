package com.example.xinyue.customgesture;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomGesturesActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gestures);

        gLibrary = GestureLibraries.fromRawResource(this,R.raw.gestures);
        if(!gLibrary.load()){
            finish();
        }
        GestureOverlayView gOverlayView = (GestureOverlayView) findViewById(R.id.gOverlay);
        gOverlayView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
        gOverlayView.addOnGesturePerformedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_gestures, menu);
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

    public void onGesturePerformed(GestureOverlayView gview, Gesture g){
        Log.d("GESTURE","DETECT");
        ArrayList<Prediction> ps = gLibrary.recognize(g);
        for(int i=0;i<ps.size();i++){
            Log.d("GESTURE", ps.get(i).name+" "+ps.get(i).score);
        }
        if(ps.size()>0 && ps.get(0).score >1){
            String action = ps.get(0).name;
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
        }
    }
}
