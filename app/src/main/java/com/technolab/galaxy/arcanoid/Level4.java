package com.technolab.galaxy.arcanoid;

import java.util.LinkedList;
import static com.technolab.galaxy.arcanoid.Globals.WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.HEIGHT;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_HEIGHT;

public class Level4 implements LevelData
{
    @Override
    public LinkedList<BrickData> getBricks()
    {
        LinkedList<BrickData> bricks = new LinkedList<>();

        float sx = WIDTH/2-BRICK_WIDTH/2, sy = HEIGHT/3;
        bricks.add(new BrickData(sx, sy));

        bricks.add(new BrickData(sx-BRICK_WIDTH, sy-BRICK_HEIGHT));
        bricks.add(new BrickData(sx+BRICK_WIDTH, sy-BRICK_HEIGHT));

        bricks.add(new BrickData(sx-3*BRICK_WIDTH/2, sy));
        bricks.add(new BrickData(sx+3*BRICK_WIDTH/2, sy));

        bricks.add(new BrickData(sx-2*BRICK_WIDTH, sy+BRICK_HEIGHT));
        bricks.add(new BrickData(sx+2*BRICK_WIDTH, sy+BRICK_HEIGHT));

        bricks.add(new BrickData(sx-3*BRICK_WIDTH/2, sy+2*BRICK_HEIGHT));
        bricks.add(new BrickData(sx+3*BRICK_WIDTH/2, sy+2*BRICK_HEIGHT));

        bricks.add(new BrickData(sx-BRICK_WIDTH, sy+3*BRICK_HEIGHT));
        bricks.add(new BrickData(sx+BRICK_WIDTH, sy+3*BRICK_HEIGHT));

        bricks.add(new BrickData(sx-BRICK_WIDTH/2, sy+4*BRICK_HEIGHT));
        bricks.add(new BrickData(sx+BRICK_WIDTH/2, sy+4*BRICK_HEIGHT));

        bricks.add(new BrickData(sx, sy+5*BRICK_HEIGHT));

        return bricks;
    }
}
