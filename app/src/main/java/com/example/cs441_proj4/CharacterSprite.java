package com.example.cs441_proj4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    private int x,y;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite(Bitmap bmp){
        image = bmp;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(image, 100, 100, null);
    }

    public void update(){
        y++;
    }
}
