package com.example.cs441_proj4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    private int x;
    private int y;
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite(Bitmap bmp){
        image = bmp;

        x = screenWidth/2;
        y = 1200;
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(image, x, y, null);
    }

    public void setX(int X){
        int x = X;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;

    }
}
