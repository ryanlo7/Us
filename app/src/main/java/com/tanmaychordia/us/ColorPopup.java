//package com.tanmaychordia.us;
//
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.PopupMenu;
//import android.widget.PopupWindow;
//
//public class ColorPopup extends PopupWindow {
//
//    private WindowManager windowManager;
//
//
//    public View mView;
//    /**
//     * Is fading mode enabled?
//     */
//
//
//
//
//
//
//
//
//    public ColorPopup(Service c)
//    {
//    super(c);
//
//        setHeight(500);
//        setWidth(500);
//        setFocusable(true);
//
//        LineColorPicker colorPicker = new LineColorPicker(c, null);
//
//// set color palette
//        colorPicker.setColors(new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW});
//
//// set selected color [optional]
//        colorPicker.setSelectedColor(Color.RED);
//
//// set on change listener
//        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
//            @Override
//            public void onColorChanged(int c) {
//                //Log.d(TAG, "Selected color " + Integer.toHexString(c));
//            }
//        });
//
//// get selected color
//        int color = colorPicker.getColor();
//        mView = colorPicker;
////        mView.setLayoutParams(new ViewGroup.LayoutParams(500,500));
//
//
//
//
//
//        // vie
////        chatHead = new ImageView(this);
////        chatHead.setElevation(20);
////        updateImage(R.drawable.skulduggerycool);//image is your image
//
//
//
//        ChatHeadService.params.x = 10;
//        ChatHeadService.params.y = 50;
//        ChatHeadService.params.height = 500;
//        ChatHeadService.params.width = ChatHeadService.params.MATCH_PARENT;
//        ChatHeadService.moveable = false;
//        setElevation(20);
//        showAsDropDown(ChatHeadService.chatHead);
//        setContentView(mView);
//        ChatHeadService.windowManager.updateViewLayout(ChatHeadService.chatHead, ChatHeadService.params);
//
//
//    }
//
//
//    /**
//     * Start up the pulse to fade the screen, clearing any existing pulse to
//     * ensure that we don't have multiple pulses running at a time.
//     * //
//     */
////    void startFading() {
////        mHandler.removeMessages(FADE_MSG);
////        mHandler.sendMessageDelayed(
////                mHandler.obtainMessage(FADE_MSG), FADE_DELAY);
////    }
////
//
//}
//
///**
// * Stop the pulse to fade the screen.
// */
////    void stopFading() {
////        mHandler.removeMessages(FADE_MSG);
////    }
//
////    private Handler mHandler = new Handler() {
////        @Override public void handleMessage(Message msg) {
////            switch (msg.what) {
////                // Upon receiving the fade pulse, we have the view perform a
////                // fade and then enqueue a new message to pulse at the desired
////                // next time.
////                case FADE_MSG: {
////                    mView.fade();
////                    mHandler.sendMessageDelayed(
////                            mHandler.obtainMessage(FADE_MSG), FADE_DELAY);
////                    break;
////                }
////                default:
////                    super.handleMessage(msg);
////            }
////        }
////    };
//
////    public class MyView extends View {
////        private static final int FADE_ALPHA = 0x06;
////        private static final int MAX_FADE_STEPS = 256/FADE_ALPHA + 4;
////        private static final int TRACKBALL_SCALE = 10;
////
////        private Bitmap mBitmap;
////        private Canvas mCanvas;
////        private final Rect mRect = new Rect();
////        private final Paint mPaint;
////        private final Paint mFadePaint;
////        private float mCurX;
////        private float mCurY;
////        private int mFadeSteps = MAX_FADE_STEPS;
////
////        public MyView(Context c) {
////            super(c);
////            setFocusable(true);
////            mPaint = new Paint();
////            mPaint.setAntiAlias(true);
////            mPaint.setARGB(255, 255, 255, 255);
////            mFadePaint = new Paint();
////            mFadePaint.setDither(true);
////            mFadePaint.setARGB(FADE_ALPHA, 0, 0, 0);
////            startFading();
////        }
////
////        public void clear() {
////            if (mCanvas != null) {
////                mPaint.setARGB(0xff, 0, 0, 0);
////                mCanvas.drawPaint(mPaint);
////                invalidate();
////                mFadeSteps = MAX_FADE_STEPS;
////            }
////        }
////
////        public void fade() {
////            if (mCanvas != null && mFadeSteps < MAX_FADE_STEPS) {
////                mCanvas.drawPaint(mFadePaint);
////                invalidate();
////                mFadeSteps++;
////            }
////        }
////
////        @Override protected void onSizeChanged(int w, int h, int oldw,
////                                               int oldh) {
////            int curW = mBitmap != null ? mBitmap.getWidth() : 0;
////            int curH = mBitmap != null ? mBitmap.getHeight() : 0;
////            if (curW >= w && curH >= h) {
////                return;
////            }
////
////            if (curW < w) curW = w;
////            if (curH < h) curH = h;
////
////            Bitmap newBitmap = Bitmap.createBitmap(curW, curH,
////                    Bitmap.Config.RGB_565);
//////            Bitmap newBitmap = Bitmap.createBitmap(150, 150,
//////                    Bitmap.Config.RGB_565);
////            Canvas newCanvas = new Canvas();
////            newCanvas.setBitmap(newBitmap);
////            if (mBitmap != null) {
////                newCanvas.drawBitmap(mBitmap, 0, 0, null);
////            }
////            mBitmap = newBitmap;
////            mCanvas = newCanvas;
////            mFadeSteps = MAX_FADE_STEPS;
////        }
////
////        @Override protected void onDraw(Canvas canvas) {
////            if (mBitmap != null) {
////                canvas.drawBitmap(mBitmap, 0, 0, null);
////            }
////        }
////
////        @Override public boolean onTrackballEvent(MotionEvent event) {
////            int N = event.getHistorySize();
////            final float scaleX = event.getXPrecision() * TRACKBALL_SCALE;
////            final float scaleY = event.getYPrecision() * TRACKBALL_SCALE;
////            for (int i=0; i<N; i++) {
////                //Log.i("TouchPaint", "Intermediate trackball #" + i
////                //        + ": x=" + event.getHistoricalX(i)
////                //        + ", y=" + event.getHistoricalY(i));
////                mCurX += event.getHistoricalX(i) * scaleX;
////                mCurY += event.getHistoricalY(i) * scaleY;
////                drawPoint(mCurX, mCurY, 1.0f, 16.0f);
////            }
////            //Log.i("TouchPaint", "Trackball: x=" + event.getX()
////            //        + ", y=" + event.getY());
////            mCurX += event.getX() * scaleX;
////            mCurY += event.getY() * scaleY;
////            drawPoint(mCurX, mCurY, 1.0f, 16.0f);
////            return true;
////        }
////
////        @Override public boolean onTouchEvent(MotionEvent event) {
////            int action = event.getActionMasked();
////            if (action != MotionEvent.ACTION_UP && action != MotionEvent.ACTION_CANCEL) {
////                int N = event.getHistorySize();
////                int P = event.getPointerCount();
////                for (int i = 0; i < N; i++) {
////                    for (int j = 0; j < P; j++) {
////                        mCurX = event.getHistoricalX(j, i);
////                        mCurY = event.getHistoricalY(j, i);
////                        drawPoint(mCurX, mCurY,
////                                event.getHistoricalPressure(j, i),
////                                event.getHistoricalTouchMajor(j, i));
////                        float midx;
////                        float midy;
////                        //Start adding extra circles here
////                        if(i< N-1)
////                        {
////                            midx = (mCurX + event.getHistoricalX(j, i+1))/2.0f;
////                            midy = (mCurY + event.getHistoricalY(j, i+1))/2.0f;
////                            drawPoint( midx, midy,
////                                    event.getHistoricalPressure(j, i+1),
////                                    event.getHistoricalTouchMajor(j, i+1));
////
////                            float tempx = (mCurX + midx)/2.0f;
////                            float tempy = (mCurY + midy)/2.0f;
////                            drawPoint( tempx, tempy,
////                                    event.getHistoricalPressure(j, i),
////                                    event.getHistoricalTouchMajor(j, i));
////
////                            tempx = (midx +event.getHistoricalX(j, i+1) )/2.0f;
////                            tempy = (mCurY + event.getHistoricalY(j, i + 1))/2.0f;
////                            drawPoint( tempx, tempy,
////                                    event.getHistoricalPressure(j, i+1),
////                                    event.getHistoricalTouchMajor(j, i+1));
////                        }
////
////
////
////                    }
////                }
////                for (int j = 0; j < P; j++) {
////                    mCurX = event.getX(j);
////                    mCurY = event.getY(j);
////                    drawPoint(mCurX, mCurY, event.getPressure(j), event.getTouchMajor(j));
////                }
////            }
////            return true;
////        }
////
////        private void drawPoint(float x, float y, float pressure, float width) {
////            //Log.i("TouchPaint", "Drawing: " + x + "x" + y + " p="
////            //        + pressure + " width=" + width);
////            if (width < 1) width = 1;
////            if (mBitmap != null) {
////                float radius = width / 2;
////                int pressureLevel = (int)(pressure * 255);
////
//////                RadialGradient gradient = new RadialGradient(x, y, radius, 0xFF6666,
//////                        Color.TRANSPARENT, android.graphics.Shader.TileMode.MIRROR);
////
////
////
//////                mPaint.setShader(gradient);
////                // set color gradient here
////                mPaint.setARGB(pressureLevel, 255, 102, 102);
////
////
////                mCanvas.drawCircle(x, y, radius, mPaint);
//////                mCanvas.drawCircle(x, y, radius, mPaint);
////                mRect.set((int) (x - radius - 2), (int) (y - radius - 2),
////                        (int) (x + radius + 2), (int) (y + radius + 2));
////                invalidate(mRect);
////            }
////            mFadeSteps = 0;
////        }
////    }
////}
