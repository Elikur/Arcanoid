package com.technolab.galaxy.arcanoid;

import android.graphics.Canvas;

import java.util.Iterator;
import java.util.LinkedList;
import static com.technolab.galaxy.arcanoid.Globals.BMP_BRICK;

public class BrickManager
{
    LinkedList<BrickData> bricks;
    Iterator iterator;
    BrickData brick;
    int retValue;

    public BrickManager(LevelData level)
    {
        bricks = level.getBricks();
    }

    public boolean isEmpty()
    {
        return bricks.isEmpty();
    }

    public int removeOnBallTouch(float a, float b)
    {
        iterator = bricks.iterator();
        while (iterator.hasNext())
        {
            brick = (BrickData) iterator.next();
            retValue = brick.touches(a, b);

            if (retValue != 0)
            {
                bricks.remove(brick);
                return retValue;
            }
        }
        return 0;
    }

    public void drawOnCanvas(Canvas canvas)
    {
        iterator = bricks.iterator();
        while(iterator.hasNext())
        {
            brick = (BrickData) iterator.next();
            canvas.drawBitmap(BMP_BRICK, brick.x, brick.y, null);
        }
    }
}

