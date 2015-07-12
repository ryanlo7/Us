package com.tanmaychordia.us;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser==null) {
            navigateToLogin();
        }
        else
        {
            String id = currentUser.getObjectId();

//            try {
//                UsApp.pubnub.subscribe(currentUser.getObjectId(), new Callback() {
//
//                    @Override
//                    public void connectCallback(String channel, Object message) {
//                        UsApp.pubnub.publish("my_channel", "Hello from the PubNub Java SDK", new Callback() {});
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
//        }
        }
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
