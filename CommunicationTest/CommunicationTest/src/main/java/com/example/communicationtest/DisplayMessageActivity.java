package com.example.communicationtest;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.content.Intent;
import android.widget.Button;


import java.lang.reflect.Array;
import java.util.ArrayList;

//gesture stuff


public class DisplayMessageActivity extends Activity {
    private HorizontalScrollView scrollViewer;
    private LinearLayout linearLayout;
    private Button questionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_message);


        /*if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            //show the up button in the action bar
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

    }

    protected void onResume(Bundle saveInstanceState) {
        //this is the next step after onCreate
        super.onResume();
        addPictures();
    }

    protected void onStop(Bundle savedInstanceState)
    {//This is called when we stop the activity

        super.onStop();
        //removePictures();

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
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void previousPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

/* -------------------addPictures -----------------------
   The functions creates the images that will be placed in
   the linear view.  The user will be able to double tap
   to select it as their result.  need to do double click
    ------------------------------------------------------*/

    private Boolean addPictures() {

        //load images into the scroll viewer
        scrollViewer = (HorizontalScrollView)findViewById(R.id.results);
        int scroll_height = scrollViewer.getLayoutParams().height;
        int scroll_width = scrollViewer.getLayoutParams().width;
        linearLayout = (LinearLayout)findViewById(R.id.results_holder);
        int layout_height = linearLayout.getLayoutParams().height;
        int layout_width = linearLayout.getLayoutParams().width;
        questionBtn = (Button)findViewById(R.id.question_box);
        int btn_height = questionBtn.getHeight();
        int btn_width = questionBtn.getWidth();

        DisplayMetrics displayInfo = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayInfo);

        int info_height = displayInfo.heightPixels;
        int info_width = displayInfo.widthPixels;

        int[] imgList;
        imgList = new int[4];
        imgList[0] = R.drawable.af_memorial;
        imgList[1] = R.drawable.af_memorial1;
        imgList[2] = R.drawable.af_memorial2;
        imgList[3] = R.drawable.af_memorial3;
        ImageView imgV = new ImageView(this);

        //get the picture

        String msg = "layout_width: " + Integer.toString(layout_width);
        Log.i("App_Name",msg);
        msg = "layout_height: " + Integer.toString(layout_height);
        Log.i("App_Name",msg);
        msg = "scroll_width: " + Integer.toString(scroll_width);
        Log.i("App_Name",msg);
        msg = "scroll_height: " + Integer.toString(scroll_height);
        Log.i("App_Name",msg);
        msg = "info_width: " + Integer.toString(info_width);
        Log.i("App_Name",msg);
        msg = "info_height: " + Integer.toString(info_height);
        Log.i("App_Name",msg);
        msg = "btn_width: " + Integer.toString(btn_width);
        Log.i("App_Name",msg);
        msg = "btn_height: " + Integer.toString(btn_height);
        Log.i("App_Name",msg);

        //set the picture to the imageView
        //imgV.setImageBitmap(bmp);
        for( int i = 0; i < imgList.length; i++) {
            Bitmap bmp;
            bmp = BitmapFactory.decodeResource(getResources(),imgList[i]);
            bmp= Bitmap.createScaledBitmap(bmp, info_width - 100, info_height - 100, false);
            imgV = new ImageView(this);
            imgV.setImageBitmap(bmp);
            //imgV.setOnLongClickListener(this);
            linearLayout.addView(imgV);
        }
        //scrollViewer.addView(imgV,0);
        /*for(int i = 0; i < imgList.length; i++)
        {
            ImageView imgV = new ImageView(this);
            //imgV.wid
            imgV.setImageResource(imgList[i]);
            linearLayout.addView(imgV);
        }*/


        return true;
    }


    /* -------------------removePictures -----------------------
   The functions creates the images that will be placed in
   the linear view.  The user will be able to double tap
   to select it as their result.  need to do double click
    ------------------------------------------------------*/
    private boolean removePictures() {
        linearLayout = (LinearLayout)findViewById(R.id.results_holder);
        int imageLayoutLength = linearLayout.getChildCount();
        for(int i = 0; i < imageLayoutLength; i++) {
            linearLayout.removeViewAt(0); //remove each image
        }

        return false;
    }

    public boolean onLongClick(View v) {
        return true;
    }








}
