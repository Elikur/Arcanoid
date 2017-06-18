package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class LoseActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.lose);

        findViewById(R.id.bLRestart).setOnClickListener(this);
        findViewById(R.id.bLReturn).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bLRestart:
                setResult(Globals.RESULT_RESTART);
                finish();
                break;
            case R.id.bLReturn:
                setResult(Globals.RESULT_RETURN);
                finish();
                break;
        }
    }
}
