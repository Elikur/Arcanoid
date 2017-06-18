package com.technolab.galaxy.arcanoid;

import java.util.LinkedList;
import static com.technolab.galaxy.arcanoid.Globals.WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.HEIGHT;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_HEIGHT;

public class Level1 implements LevelData
{
    @Override
    public LinkedList<BrickData> getBricks()
    {
        LinkedList<BrickData> bricks = new LinkedList<>();

        int nw = (int)(5 * WIDTH / (6 * BRICK_WIDTH)), t;
        float bky;

        for (bky = HEIGHT / 4, t = 1; bky <= HEIGHT / 2 && t <= nw; bky += BRICK_HEIGHT, ++t) {
            for (float bkx = (WIDTH - t * BRICK_WIDTH) / 2, countT = 0; countT < t; ++countT, bkx += BRICK_WIDTH)
                bricks.add(new BrickData(bkx, bky));
        }

        for (--t; t>=1; bky += BRICK_HEIGHT, --t) {
            for (float bkx = (WIDTH - t * BRICK_WIDTH) / 2, countT = 0; countT < t; ++countT, bkx += BRICK_WIDTH)
                bricks.add(new BrickData(bkx, bky));
        }

        return bricks;
    }
}
