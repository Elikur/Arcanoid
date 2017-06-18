package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;
import android.view.Window;
import android.view.WindowManager;

public class Globals
{
    public static float WIDTH;
    public static float HEIGHT;

    public static Bitmap BMP_BRICK;
    public static float BRICK_WIDTH, BRICK_HEIGHT;

    public static Bitmap BMP_BALL;
    public static float BALL_WIDTH, BALL_HEIGHT;

    public static final int TYPE_UP = 1;
    public static final int TYPE_DOWN = 2;
    public static final int TYPE_MID = 3;
    public static final int TYPE_LEFT = 4;
    public static final int TYPE_RIGHT = 5;

    public static Bitmap BMP_BAR;
    public static float BAR_WIDTH, BAR_HEIGHT;

    public static Bitmap POW_HAMMER;

    public static Rect BAR_PANEL;
    public static Paint WHITE_PAINT;
    public static Paint BLACK_PAINT;

    public static int MULTIPLIER;
    public static int BAR_INC;

    public static float SCORE_BAR_HEIGHT;
    public static Paint SCORE_PAINT;

    public static final int RESULT_RETURN = 1;
    public static final int RESULT_RESTART = 2;
    public static final int RESULT_NEXT = 3;

    public static final int TOTAL_LEVELS = 4;
    public static final int MAX_TIME_BONUS = 10000;

    public static final String KEY_LEVEL = "Level";
    public static final String KEY_SCORE = "Score";
    public static final String KEY_BONUS = "Bonus";

    public static final String SHARED_FILE = "ArcanoidSaveData";
    public static final String SHARED_LEVEL_KEY = "LastLevelUnlocked";
    public static SharedPreferences PREFERENCES;
    public static SharedPreferences.Editor EDITOR;

    public static SoundPool SOUND_POOL;
    public static int SOUND_TOUCH_BRICK;
    public static int SOUND_TOUCH_BAR;
    public static int SOUND_WIN;

    public static void setFullScreen(Activity activity)
    {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
