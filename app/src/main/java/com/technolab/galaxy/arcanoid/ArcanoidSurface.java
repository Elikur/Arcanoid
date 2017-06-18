package com.technolab.galaxy.arcanoid;

import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import static com.technolab.galaxy.arcanoid.Globals.*;

public class ArcanoidSurface extends SurfaceView implements Runnable, View.OnClickListener, SensorEventListener
{
    ArcanoidActivity activity;
    SurfaceHolder holder;
    SensorManager sensorManager;
    Canvas canvas;

    Intent intent;

    float ax;
    boolean isRunning;
    boolean isBallLaunched;
    BrickManager brickManager;
    Thread thread;
    int position;
    LevelData level;

    public float ballX, ballY;
    public float ballXInc, ballYInc;
    public float barX, barY;

    public double theta;
    public double bounceTheta;

    public final double MAX_ANGLE = Math.toRadians(80);
    public final double MIN_ANGLE = Math.toRadians(25);

    public boolean up;
    public boolean left;

    public String levelName;
    public long score;
    public long startTime, endTime, timeBonus;
    int retValue, ty, tx;

    public ArcanoidSurface(ArcanoidActivity activity)
    {
        super(activity);
        this.activity = activity;
        holder = getHolder();
        setOnClickListener(this);
        sensorManager = (SensorManager)activity.getSystemService(activity.SENSOR_SERVICE);
        intent = activity.getIntent();
        position = intent.getIntExtra(KEY_LEVEL, 1);

        restartPosition();
    }

    public void run()
    {
        while(isRunning)
        {
            if(!holder.getSurface().isValid())
                continue;

            if(isBallLaunched)
            {
                retValue = brickManager.removeOnBallTouch(ballX, ballY);
                if(retValue != 0)
                {
                    SOUND_POOL.play(SOUND_TOUCH_BRICK, 1, 1, 0, 0, 1);
                    score+=50;
                    ty = retValue/10;
                    tx = retValue%10;
                    if(tx==TYPE_MID)
                    {
                        up = !up;
                        if(ty == TYPE_MID)
                            left = !left;
                    }
                    else if(ty==TYPE_MID)
                    {
                        left=!left;
                        if(tx == TYPE_MID)
                            up = !up;
                    }
                    else if(ty==TYPE_UP)
                    {
                        up = true;
                        if(tx==TYPE_LEFT)
                            left = true;
                        else
                            left = false;
                    }
                    else if(ty==TYPE_DOWN)
                    {
                        up = false;
                        if(tx==TYPE_LEFT)
                            left = true;
                        else
                            left = false;
                    }
                }

                if(brickManager.isEmpty())
                {
                    endTime = System.currentTimeMillis();
                    timeBonus = (endTime - startTime)/100;

                    SOUND_POOL.play(SOUND_WIN, 1, 1, 0, 0, 1);
                    intent = new Intent(activity, WinActivity.class);
                    intent.putExtra(KEY_SCORE, score);
                    intent.putExtra(KEY_BONUS, (MAX_TIME_BONUS-timeBonus)>0?(MAX_TIME_BONUS-timeBonus)/10:0);
                    activity.startActivityForResult(intent, 0);
                }

                if(up)
                {
                    ballY -= ballYInc;
                    if(ballY < SCORE_BAR_HEIGHT)
                    {
                        ballY = SCORE_BAR_HEIGHT;
                        up = false;
                    }
                }
                else
                {
                    ballY += ballYInc;
                    if(ballY <= barY && ballY+BALL_HEIGHT>=barY)
                    {
                        if(ballX<=barX && ballX+BALL_WIDTH>=barX)
                        {
                            up = true;
                            left = true;
                            SOUND_POOL.play(SOUND_TOUCH_BAR, 1, 1, 0, 0, 1);
                        }
                        else if(ballX>=barX && ballX<=barX+BAR_WIDTH)
                        {
                            up = true;
                            bounceTheta = Math.abs(Math.asin((barX+BAR_WIDTH/2-ballX)/(BAR_WIDTH/2)));
                            if(ballX+BALL_WIDTH<=barX+BAR_WIDTH/2)
                            {
                                if(!left)
                                    theta -= bounceTheta;
                                else
                                    theta += bounceTheta;
                            }
                            else
                            {
                                if(left)
                                    theta -= bounceTheta;
                                else
                                    theta += bounceTheta;
                            }
                            SOUND_POOL.play(SOUND_TOUCH_BAR, 1, 1, 0, 0, 1);
                            updateIncrements();
                        }
                        else if(ballX<=barX+BAR_WIDTH && ballX+BALL_WIDTH>=barX+BAR_WIDTH)
                        {
                            up = true;
                            left = false;
                            SOUND_POOL.play(SOUND_TOUCH_BAR, 1, 1, 0, 0, 1);
                        }
                        else
                        {
                            intent = new Intent(activity, LoseActivity.class);
                            activity.startActivityForResult(intent, 0);
                        }
                    }
                    else if(ballY>=barY)
                    {
                        intent = new Intent(activity, LoseActivity.class);
                        activity.startActivityForResult(intent, 0);
                    }
                }

                if(left)
                {
                    ballX -= ballXInc;
                    if(ballX < 0)
                    {
                        ballX = 0;
                        left = false;
                    }
                }
                else
                {
                    ballX += ballXInc;
                    if(ballX+BALL_WIDTH >= WIDTH)
                    {
                        ballX = WIDTH-BALL_WIDTH;
                        left = true;
                    }
                }

                try {
                    thread.sleep(20);
                } catch (Exception e) { }
            }

            canvas = holder.lockCanvas();
            canvas.drawRGB(255, 255, 255);
            canvas.drawLine(0,SCORE_BAR_HEIGHT, WIDTH, SCORE_BAR_HEIGHT, BLACK_PAINT);
            canvas.drawLine(0,SCORE_BAR_HEIGHT-1, WIDTH, SCORE_BAR_HEIGHT-1, BLACK_PAINT);

            canvas.drawText(levelName, 20, 40, SCORE_PAINT);
            canvas.drawText("Score: "+score, 2*WIDTH/3, 40, SCORE_PAINT);

            brickManager.drawOnCanvas(canvas);
            canvas.drawBitmap(BMP_BAR, barX, barY, null);
            canvas.drawBitmap(BMP_BALL, ballX, ballY, null);

            holder.unlockCanvasAndPost(canvas);

        }
    }


    public void restartPosition()
    {
        barX = WIDTH/2 - BAR_WIDTH/2;
        barY = HEIGHT - BAR_HEIGHT;
        ballX = WIDTH/2 - BALL_WIDTH/2;
        ballY = HEIGHT - BAR_HEIGHT - BALL_HEIGHT;

        isRunning = isBallLaunched = false;

        theta = 60;
        bounceTheta = 0;
        up = true;
        left = false;

        score = timeBonus = 0;
        try {
            levelName = "Level "+position;
            level = (LevelData)Class.forName("com.technolab.galaxy.arcanoid.Level"+position).newInstance();
            brickManager = new BrickManager(level);
        }catch(Exception e) {}
    }

    public void resume()
    {
        // Create a thread and start it
        isRunning = true;

        if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0)
            sensorManager.registerListener(this, sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);
        
        thread = new Thread(this);
        thread.start();
    }

    public void pause()
    {
        // Destroy the running thread
        isRunning = false;
        try {
            thread.join();
        }catch(Exception e) {}
        thread = null;

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        // Get the acceleration of the bar along the x-axis
        if(isBallLaunched) {
            ax = event.values[0];
            barX -= ax * BAR_INC;
            if (barX < 0)
                barX = 0;
            if (barX + BAR_WIDTH > WIDTH)
                barX = WIDTH - BAR_WIDTH;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)   {}

    @Override
    public void onClick(View v) {
        // If sensor is unregistered, then on click -> register it
        if(!isBallLaunched)
        {
            // Generate a random theta b/w -PI/2 to PI/2
            theta = -Math.PI/2 + 2*Math.random()*Math.PI;

            // update directions
            up = true;
            left = true;
            if (theta>0)
                left = false;
            theta = Math.abs(theta);
            updateIncrements();

            // Update predicate for sensor to be true, so that it is not registered again
            isBallLaunched = true;

            startTime = System.currentTimeMillis();
        }
    }


    private void updateIncrements()
    {
        theta = Math.abs(theta);
        if(theta>MAX_ANGLE)
            theta = MAX_ANGLE;
        if(theta<MIN_ANGLE)
            theta = MIN_ANGLE;
        ballXInc = (float) Math.cos(theta) * MULTIPLIER;
        ballYInc = (float) Math.sin(theta) * MULTIPLIER;
    }
}
