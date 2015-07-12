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
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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
        else {
            try {
                currentUser.fetch();
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            String id = currentUser.getObjectId();

//            UsApp.myId= currentUser.getObjectId();
//

            bchart = (BarChart) findViewById(R.id.bchart);


            ArrayList<String> xVals = new ArrayList<String>();
            xVals.add("Happy");
            xVals.add("Sad");
            xVals.add("Angry");
            xVals.add("Anxious");

            ArrayList<Integer> data = (ArrayList<Integer>) currentUser.get("moodHistory");

            //System.out.println(data.isEmpty());

            BarDataSet bdset = new BarDataSet(getY(data), "Moods");
            bdset.setBarSpacePercent(35f);
            BarData bdata = new BarData(xVals, bdset);

            bchart.setTouchEnabled(false);
            bchart.setData(bdata);
            bchart.setFocusable(true);
            bchart.setLogEnabled(true);
            bchart.invalidate();
            startService(new Intent(getApplicationContext(), ChatHeadService.class));


            //CREATING Scatter Chart
            schart = (ScatterChart) findViewById(R.id.schart);
            int height = 10;

            //creates entries
            ArrayList<Entry> happyEntries = new ArrayList<Entry>();
            ArrayList<Entry> sadEntries = new ArrayList<Entry>();
            ArrayList<Entry> angryEntries = new ArrayList<Entry>();
            ArrayList<Entry> anxiousEntries = new ArrayList<Entry>();

            //i might not be x values
            //populates entries
            for(int i = 0; i < data.size(); i++)
            {
                if (data.get(i)== 1)
                {
                    happyEntries.add(new Entry(height, i));
                }
                else if (data.get(i)== 2)
                {
                    sadEntries.add(new Entry(height, i));
                }
                else if (data.get(i)== 3)
                {
                    angryEntries.add(new Entry(height, i));
                }
                else if (data.get(i)== 4)
                {
                    anxiousEntries.add(new Entry(height, i));
                }

            }

            //map entries to data sets, color data sets, shape data sets
            ScatterDataSet happySet = new ScatterDataSet(happyEntries, "Happy");
            happySet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            happySet.setColor(ColorTemplate.COLORFUL_COLORS[0]);
            happySet.setScatterShapeSize(20f);

            ScatterDataSet sadSet = new ScatterDataSet(sadEntries, "Sad");
            sadSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            sadSet.setColor(ColorTemplate.COLORFUL_COLORS[1]);
            sadSet.setScatterShapeSize(20f);

            ScatterDataSet angrySet = new ScatterDataSet(angryEntries, "Angry");
            angrySet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            angrySet.setColor(ColorTemplate.COLORFUL_COLORS[2]);
            angrySet.setScatterShapeSize(20f);

            ScatterDataSet anxiousSet = new ScatterDataSet(anxiousEntries, "Anxious");
            anxiousSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            anxiousSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);
            anxiousSet.setScatterShapeSize(20f);

            ArrayList<ScatterDataSet> moodsDataSets = new ArrayList<ScatterDataSet>();
            moodsDataSets.add(happySet); // add the datasets
            moodsDataSets.add(sadSet);
            moodsDataSets.add(angrySet);
            moodsDataSets.add(anxiousSet);

            //sets xValues
            ArrayList<String> xValues = new ArrayList<String>();
            for(int i = 0; i < data.size(); i++)
            {
                xValues.add((i) + "");
            }

            // create a data object with the datasets
            ScatterData scatterdata = new ScatterData(xValues, moodsDataSets);
            ////scatterdata.setValueTypeface(tf);

            schart.setData(scatterdata);
            schart.invalidate();



//        Intent intent = new Intent(this, TouchPaint.class);
//        startActivity(intent);


        }

    }

    private ArrayList<BarEntry> getY(ArrayList<Integer> hist)
    {
        int[] ys = new int[4];
        for(int a:hist)
        {
            ys[a-1]+=1;
        }
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for(int a = 0; a < ys.length; a++) {

            yVals1.add(new BarEntry(ys[a], a));
        }
        return yVals1;

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