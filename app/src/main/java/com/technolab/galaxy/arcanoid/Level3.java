package com.technolab.galaxy.arcanoid;

import java.util.LinkedList;
import static com.technolab.galaxy.arcanoid.Globals.WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.HEIGHT;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_WIDTH;
import static com.technolab.galaxy.arcanoid.Globals.BRICK_HEIGHT;

public class Level3 implements LevelData
{
    @Override
    public LinkedList<BrickData> getBricks()
    {
        LinkedList<BrickData> bricks = new LinkedList<>();

        boolean one = true;
        float bky, sx=(WIDTH-3*BRICK_WIDTH)/2;
        for(bky = HEIGHT/7; bky<=3*HEIGHT/4 ;one = !one, bky+=BRICK_HEIGHT)
        {
            if(one)
                bricks.add(new BrickData(sx+BRICK_WIDTH, bky));
            else
            {
                bricks.add(new BrickData(sx, bky));
                bricks.add(new BrickData(sx+2*BRICK_WIDTH, bky));
            }
        }

        return bricks;
    }
}
