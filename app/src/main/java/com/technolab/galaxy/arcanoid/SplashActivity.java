package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import static com.technolab.galaxy.arcanoid.Globals.*;

public class SplashActivity extends Activity
{
    Resources resources;
    Loader loader;

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);

        setFullScreen(this);

        setContentView(R.layout.splash);
        resources = getResources();
        loader = new Loader();
        loader.execute("Load");

    }

    class Loader extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            WIDTH = resources.getDisplayMetrics().widthPixels;
            HEIGHT = resources.getDisplayMetrics().heightPixels;

            BMP_BALL = BitmapFactory.decodeResource(resources, R.drawable.ball);
            BALL_WIDTH = BMP_BALL.getWidth();
            BALL_HEIGHT = BMP_BALL.getHeight();

            BMP_BAR = BitmapFactory.decodeResource(resources, R.drawable.bar);
            BAR_WIDTH = BMP_BAR.getWidth();
            BAR_HEIGHT = BMP_BAR.getHeight();

            BMP_BRICK = BitmapFactory.decodeResource(resources, R.drawable.brick);
            BRICK_WIDTH = BMP_BRICK.getWidth();
            BRICK_HEIGHT = BMP_BRICK.getHeight();

            POW_HAMMER = BitmapFactory.decodeResource(resources, R.drawable.pow_hammer);

            BAR_PANEL = new Rect(0, (int)(HEIGHT-BAR_HEIGHT), (int)WIDTH, (int)HEIGHT);
            WHITE_PAINT = new Paint();
            WHITE_PAINT.setColor(Color.WHITE);
            BLACK_PAINT = new Paint();
            BLACK_PAINT.setColor(Color.BLACK);

            SCORE_BAR_HEIGHT = HEIGHT/15;
            SCORE_PAINT = new Paint();
            SCORE_PAINT.setColor(Color.RED);
            SCORE_PAINT.setTextSize(30);

            SOUND_POOL = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            SOUND_TOUCH_BAR = SOUND_POOL.load(SplashActivity.this, R.raw.touch_bar, 1);
            SOUND_TOUCH_BRICK = SOUND_POOL.load(SplashActivity.this, R.raw.touch_brick, 1);
            SOUND_WIN = SOUND_POOL.load(SplashActivity.this, R.raw.win, 1);

            MULTIPLIER = (int)(15*WIDTH/720);
            BAR_INC = (int)(15*WIDTH/720);
            return null;
        }

        protected void onPostExecute(String result)
        {
            startActivity(new Intent(SplashActivity.this, CommandActivity.class));
            finish();
        }
    }
}
