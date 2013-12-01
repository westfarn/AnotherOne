package com.example.communicationtest;

import android.app.Activity;
//import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


//gesture stuff
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


import java.util.ArrayList;


public class MainActivity extends Activity implements OnGesturePerformedListener {
    private GestureLibrary gestureLib;
    private Button btnSendSMS;
    public final static String EXTRA_MESSAGE = "com.example.communicationtest.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendSMS = (Button)findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("5556","Hello my Friend");
            }
        });

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String[] strList;
        strList = new String[5];
        strList[0] = "first one";
        strList[1] = "second one";
        strList[2] = "third one";
        strList[3] = "fourth one";
        strList[4] = "fifth one";
        for (int i = 0; i < 5; i++) {
            ViewPollButton fragment = new ViewPollButton();
            fragment.setName(strList[i]);
            fragmentTransaction.add(R.id.mainHolder, fragment);

        }
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        for (Prediction prediction : predictions) {
            if (prediction.score > 1.0) {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void sendSMS(String phoneNumber, String msg) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, msg, null, null);

    }

    public void nextPage(View v) { //for an onClick attribute for an object this must be public!
        Intent intent;
        intent = new Intent(this, com.example.communicationtest.DisplayMessageActivity.class);
        //intent.setClass(this,com.example.communicationtest.DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String msg = "something";
        intent.putExtra(EXTRA_MESSAGE, msg);
        startActivity(intent);
    }

    public void openPoll(View view) {

        //ViewPollButton viewBtn = (ViewPollButton)findViewById(view.getId());

        ViewPollButton viewBtn = (ViewPollButton)getSupportFragmentManager().findFragmentById(R.id.view_poll_btn);

        String msg = "view.getId: : " + view.getId();
        Log.i("openPoll: ", msg);

        if(viewBtn != null) {
            CharSequence text = viewBtn.getName();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }


    }

}
