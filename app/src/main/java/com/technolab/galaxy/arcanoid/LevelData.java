package com.technolab.galaxy.arcanoid;

public interface LevelData
{
    /**
     * The following method is used to fetch the details of the bricks
     * i.e. the position to remove some when the ball touches it.
     *
     * @return The Linked List containing details of the bricks
     */
    public java.util.LinkedList<BrickData> getBricks();
}
