package com.tanmaychordia.us;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadService extends Service {

    private static int curX;
    private static int curY;
    public static WindowManager windowManager;
    public static ImageView chatHead;
    public static int width = 150;
    public static int height = width;
    public static int currentColor;
    public static boolean isDrawActive = false;
//    private ColorPopup cp;
    public static boolean moveable = true;
    @Override public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }
    public static final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            /*|WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY*/,
            PixelFormat.TRANSLUCENT);

    public static void updateImage(int color, int decrease)
    {
        width-= decrease;
        height-=decrease;
        currentColor = color;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bmp=Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);;//image is your image
        bmp.eraseColor(color);
//        bmp=Bitmap.createScaledBitmap(bmp, width,height, true);
        bmp = getRoundedShape(bmp);
        chatHead.setImageBitmap(bmp);
    }

    public static void updateImage(int id)
    {
        updateImage(id, 0);
    }

    public static void updateSize(int decrease)
    {
        updateImage(currentColor, decrease);
    }

    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = width;
        int targetHeight = height;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        canvas.drawBitmap(scaleBitmapImage,
                new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }


    @Override public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);


        chatHead = new ImageView(this);
        chatHead.setElevation(20);
        updateImage(R.drawable.skulduggerycool);//image is your image




        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;


        windowManager.addView(chatHead, params);



        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private boolean isOnClick;
            private int SCROLL_THRESHOLD = 10;
            private VelocityTracker mVelocityTracker = null;
            private int delta = width/10;
            private void onMove(MotionEvent event)
            {
                if(moveable) {
                    params.x = initialX + (int) (event.getRawX() - initialTouchX);
                    params.y = initialY + (int) (event.getRawY() - initialTouchY);
                    windowManager.updateViewLayout(chatHead, params);
                }
            }

            private void onTouch()
            {

                moveable = true;

                if(!isDrawActive) {
                    startService(new Intent(getApplicationContext(), DrawingService.class));
//                    ColorPopup cp; // = new ColorPopup(ChatHeadService.this);
//                    cp = new ColorPopup(ChatHeadService.this);
                }
                else
                {
//                    cp.dismiss();
                    stopService(new Intent(getApplicationContext(), DrawingService.class));
                }
                isDrawActive = !isDrawActive;

            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int index = event.getActionIndex();
                int action = event.getActionMasked();
                int pointerId = event.getPointerId(index);
                int vThreshold = 10;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        isOnClick = true;

                        updateSize(delta);
                        if(mVelocityTracker == null) {
                            // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                            mVelocityTracker = VelocityTracker.obtain();
                        }
                        else {
                            // Reset the velocity tracker back to its initial state.
                            mVelocityTracker.clear();
                        }


                        // Add a user's movement to the tracker.
                        mVelocityTracker.addMovement(event);

                        break;

                    case MotionEvent.ACTION_UP:
                        if(isOnClick) {
                            onTouch();
                        }
                        updateSize(-delta);
                        break;


                    case MotionEvent.ACTION_MOVE:




                        mVelocityTracker.addMovement(event);
                        // When you want to determine the velocity, call
                        // computeCurrentVelocity(). Then call getXVelocity()
                        // and getYVelocity() to retrieve the velocity for each pointer ID.
                        mVelocityTracker.computeCurrentVelocity(1000);
                        double xv = mVelocityTracker.getXVelocity();
                        double yv = mVelocityTracker.getYVelocity();
                        if (isOnClick && (Math.abs( initialTouchX - event.getRawX()) > SCROLL_THRESHOLD || Math.abs(initialTouchY - event.getRawY()) > SCROLL_THRESHOLD)) {
//                            Log.i(LOG_TAG, "movement detected");
                            isOnClick = false;

                            onMove(event);
                        }
                        else if(!isOnClick)
                        {
                            onMove(event);
                        }
//                        if(Math.sqrt(xv*xv + yv*yv) > vThreshold)
//                        {
//                            onMove(event);
//                            isOnClick = false;
//                        }

//                        isOnClick = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mVelocityTracker.recycle();
                        break;
                }
                        return false;
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }
}