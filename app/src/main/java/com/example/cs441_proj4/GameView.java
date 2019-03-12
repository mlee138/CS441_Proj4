package com.example.cs441_proj4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public CharacterSprite characterSprite;

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        characterSprite = new CharacterSprite((BitmapFactory.decodeResource(getResources(),R.drawable.Triangle)));
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){
        characterSprite.update();
    }
}
