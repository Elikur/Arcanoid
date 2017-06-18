package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.technolab.galaxy.arcanoid.Globals.KEY_LEVEL;
import static com.technolab.galaxy.arcanoid.Globals.PREFERENCES;
import static com.technolab.galaxy.arcanoid.Globals.SHARED_FILE;

public class CommandActivity extends Activity implements View.OnClickListener
{

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        Globals.setFullScreen(this);

        setContentView(R.layout.command);
        findViewById(R.id.bContinue).setOnClickListener(this);
        findViewById(R.id.bStart).setOnClickListener(this);
        findViewById(R.id.bExit).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bContinue:
                Intent i = new Intent(this, ArcanoidActivity.class);
                PREFERENCES = getSharedPreferences(SHARED_FILE, 0);
                int pos = PREFERENCES.getInt(Globals.SHARED_LEVEL_KEY, 1);
                i.putExtra(KEY_LEVEL, pos);
                startActivity(i);
                finish();
                break;
            case R.id.bStart:
                startActivity(new Intent(this, ArcanoidActivity.class));
                finish();
                break;
         case R.id.bExit:
                finish();
                break;
        }
    }
}
