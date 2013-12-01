package com.example.communicationtest;

import android.app.Fragment;
import android.content.Context;
import android.gesture.Prediction;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by westf_000 on 12/1/13.
 */
public class ViewPollButton extends Fragment {

    private Context context = null;
    private String name = null;
    //private SipSession.Listener mListner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_poll_button, container, false);

    }

    public void setName(String str) {
        String msg = "str:  " + this.getId();
        Log.i("openPoll: ", msg);
        name = str;
    }

    public String getName() {
        return name;
    }

    public interface openPoll{

        public void openPoll(View v);
        /*if(context != null) {
            CharSequence text = "You clicked me!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context.getApplicationContext(), text, duration);
            toast.show();
            return true;
        }*/
    }

}