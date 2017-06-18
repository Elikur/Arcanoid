package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;

import static com.technolab.galaxy.arcanoid.Globals.PREFERENCES;
import static com.technolab.galaxy.arcanoid.Globals.RESULT_NEXT;
import static com.technolab.galaxy.arcanoid.Globals.RESULT_RESTART;
import static com.technolab.galaxy.arcanoid.Globals.EDITOR;
import static com.technolab.galaxy.arcanoid.Globals.SHARED_FILE;
import static com.technolab.galaxy.arcanoid.Globals.SHARED_LEVEL_KEY;
import static com.technolab.galaxy.arcanoid.Globals.TOTAL_LEVELS;

public class ArcanoidActivity extends Activity
{
    ArcanoidSurface view;
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);

        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "POWER");

        Globals.setFullScreen(this);
        view = new ArcanoidSurface(this);
        setContentView(view);
    }

    public void onResume()
    {
        super.onResume();
        wakeLock.acquire();
        view.resume();
    }

    public void onPause()
    {
        super.onPause();
        view.pause();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent intent)
    {
        if(resCode == RESULT_RESTART)
            view.restartPosition();
        else if(resCode == RESULT_NEXT)
        {
            view.position++;
            if(view.position>TOTAL_LEVELS)
            {
                onBackPressed();
            }
            else
            {
                PREFERENCES = getSharedPreferences(SHARED_FILE, 0);
                EDITOR = PREFERENCES.edit();
                EDITOR.putInt(SHARED_LEVEL_KEY, view.position);
                EDITOR.commit();
                view.restartPosition();
            }
        }
        else
        {
            onBackPressed();
        }

    }

    @Override
    public void onBackPressed()
    {
        wakeLock.release();
        startActivity(new Intent(this, CommandActivity.class));
        finish();
    }
}
