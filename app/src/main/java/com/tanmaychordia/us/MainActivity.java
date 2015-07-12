package com.tanmaychordia.us;

import android.content.Intent;
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

//        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();

        if(currentUser==null) {
            navigateToLogin();
        }
        else
        {
            try {
                currentUser.fetch();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            String id = currentUser.getObjectId();
            UsApp.myId= currentUser.getObjectId();
//
        }
        bchart = (BarChart) findViewById(R.id.bchart);
        schart = (ScatterChart) findViewById(R.id.schart);



        ArrayList<Integer> data =(ArrayList<Integer>)currentUser.get("moodHistory");

        System.out.println(data.isEmpty());
        ArrayList<String> a = new ArrayList<>();
        for(int ab:data)
        {
            a.add(ab + "");
        }
        System.out.println(a);
        BarData bdata = new BarData(a);

        bchart.setData(bdata);
        bchart.setFocusable(true);
        bchart.setLogEnabled(true);
        bchart.invalidate();
        startService(new Intent(getApplicationContext(), ChatHeadService.class));

//        Intent intent = new Intent(this, TouchPaint.class);
//        startActivity(intent);

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