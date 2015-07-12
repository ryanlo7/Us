package com.tanmaychordia.us;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.BarData;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private BarChart bchart;
    private ScatterChart schart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        } else {
            try {
                currentUser.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            String id = currentUser.getObjectId();
            UsApp.myId = currentUser.getObjectId();
//
        }
//        bchart = (BarChart) findViewById(R.id.bchart);
//        schart = (ScatterChart) findViewById(R.id.schart);
//
//
//        ArrayList<String> data = (ArrayList<String>) currentUser.get("moodHistory");
//
//        BarData bdata = new BarData(data);
//
//        startService(new Intent(getApplicationContext(), ChatHeadService.class));

//        Intent intent = new Intent(this, TouchPaint.class);
//        startActivity(intent);

        //ADDED THIS
        LineColorPicker colorPicker = (LineColorPicker) findViewById(R.id.picker);

// set color palette
        colorPicker.setColors(new int[] {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW});

// set selected color [optional]
        colorPicker.setSelectedColor(Color.RED);

// set on change listener
        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                //Log.d(TAG, "Selected color " + Integer.toHexString(c));
            }
        });

// get selected color
        int color = colorPicker.getColor();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.logoutButton) {
//            ParseUser.logOut();
//            navigateToLogin();
//        }
//        else if (id== R.id.profileButton){
//            startActivity(new Intent(this, ProfileActivity.class));
//        }

        return super.onOptionsItemSelected(item);
    }
}


//try {
//                UsApp.pubnub.subscribe(currentUser.getObjectId(), new Callback() {
//
//                    @Override
//                    public void connectCallback(String channel, Object message) {
//                        UsApp.pubnub.publish(UsApp.myId, "Hello from the PubNub Java SDK", new Callback() {});
//                    }
//
//                    @Override
//                    public void disconnectCallback(String channel, Object message) {
//                        System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
//                                + " : " + message.getClass() + " : "
//                                + message.toString());
//                    }
//
//                    public void reconnectCallback(String channel, Object message) {
//                        System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
//                                + " : " + message.getClass() + " : "
//                                + message.toString());
//                    }
//
//                    @Override
//                    public void successCallback(String channel, Object message) {
//                        System.out.println("SUBSCRIBE : " + channel + " : "
//                                + message.getClass() + " : " + message.toString());
//                    }
//
//                    @Override
//                    public void errorCallback(String channel, PubnubError error) {
//                        System.out.println("SUBSCRIBE : ERROR on channel " + channel
//                                + " : " + error.toString());
//                    }
//                });
//            }
//            catch(PubnubException e)
//            {
//                System.out.print( e);
//            }