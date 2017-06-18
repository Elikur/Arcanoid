package com.technolab.galaxy.arcanoid;

import java.util.LinkedList;
import static com.technolab.galaxy.arcanoid.Globals.WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.HEIGHT;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_HEIGHT;

public class Level2 implements LevelData
{
    @Override
    public LinkedList<BrickData> getBricks()
    {
        LinkedList<BrickData> bricks = new LinkedList<>();

        int nw = (int)(3 * WIDTH / (4 * BRICK_WIDTH));

        for (float bky = HEIGHT / 6, t = nw; bky <= 7*HEIGHT / 12 && t <= nw; bky += BRICK_HEIGHT) {
            for (float bkx = (WIDTH - t * BRICK_WIDTH) / 2, countT = 0; countT < t; ++countT, bkx += BRICK_WIDTH)
                bricks.add(new BrickData(bkx, bky));
        }

        return bricks;
    }
}
