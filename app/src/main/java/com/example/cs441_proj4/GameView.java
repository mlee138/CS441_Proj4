package com.example.cs441_proj4;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class GameView extends View {

    Handler handler;
    Runnable runnable;
    final int UPDATE_MS = 20;
    Bitmap background;
    Bitmap R1, R2, R3;
    Display display;
    Point point;
    int dWidth, dHeight;
    Rect rect;
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    CharacterSprite triangle;
    boolean gameState = false;
    int numberOfRect = 3;
    int[] RectX = new int[numberOfRect];
    int[] RectY = new int[numberOfRect];
    int rectVelocity = 12;
    int pts;

    public GameView(Context context){
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources(),R.drawable.game_background);
        R1 = BitmapFactory.decodeResource(getResources(), R.drawable.rectangle);
        R2 = BitmapFactory.decodeResource(getResources(), R.drawable.rectangle);
        R3 = BitmapFactory.decodeResource(getResources(),R.drawable.rectangle);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWidth,dHeight);


        for(int i = 0; i < numberOfRect; i++){
            //calculate new Rects
            RectX[i] = dWidth + i*300;
            RectY[i] = screenHeight;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background,null,rect,null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        String temp = String.valueOf(pts);
        canvas.drawText("Points: " + temp,0,100,paint);


        if(gameState){
            for(int i = 0; i < numberOfRect; i++){
                RectY[i] -= rectVelocity;
                if(RectY[i] < R1.getHeight()){
                    RectY[i] += numberOfRect * 200;
                    pts++;
                    if(pts%10 == 0 && pts != 0){
                        rectVelocity += 1;
                    }
                }
                canvas.drawBitmap(R1, RectX[i], RectY[i] - R1.getHeight(),null);
                canvas.drawBitmap(R2, RectX[i], RectY[i] - R2.getHeight(),null);
                canvas.drawBitmap(R3, RectX[i], RectY[i] - R3.getHeight(),null);
            }

        }

       triangle.draw(canvas);
        if (triangle.getX() == R1.getWidth() && triangle.getY() == R1.getHeight()){
            System.exit(1);
        }
        handler.postDelayed(runnable,UPDATE_MS);
    }


    @Override
    public boolean onDragEvent (DragEvent event){
        gameState = true;
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:
                // Do nothing
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                triangle.setX((int) event.getX());
                break;

            case DragEvent.ACTION_DRAG_EXITED :
                triangle.setX((int) event.getX());
                break;

            case DragEvent.ACTION_DRAG_LOCATION  :
                triangle.setX((int) event.getX());
                break;

            case DragEvent.ACTION_DRAG_ENDED   :

                // Do nothing
                break;

            case DragEvent.ACTION_DROP:
                // Do nothing
                break;
            default: break;
        }

        return true;
    }
}