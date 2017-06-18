package com.technolab.galaxy.arcanoid;

import static com.technolab.galaxy.arcanoid.Globals.*;

public class BrickData
{
    public float x;
    public float y;
    public int tx, ty;

    public BrickData(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public int touches(float a, float b)
    {
        tx = ty = 0;
        if(b<=y && b+BALL_WIDTH>=y)
            ty = TYPE_UP;
        else if(b>=y && b+BALL_WIDTH<=y+BRICK_HEIGHT)
            ty = TYPE_MID;
        else if(b+BALL_HEIGHT>=y+BRICK_HEIGHT && b<=y+BRICK_HEIGHT)
            ty = TYPE_DOWN;

        if (a <= x && a + BALL_WIDTH >= x)
            tx =  TYPE_LEFT;
         else if (a >= x && a <= x + BRICK_WIDTH)
            tx = TYPE_MID;
        else if (a <= x + BRICK_WIDTH && a + BALL_WIDTH >= x + BRICK_WIDTH)
            tx = TYPE_RIGHT;

        if(tx==0 || ty==0)
            return 0;
        else
            return ty*10 + tx;
    }
}
