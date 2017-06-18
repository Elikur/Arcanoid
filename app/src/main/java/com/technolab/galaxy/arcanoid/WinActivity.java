package com.technolab.galaxy.arcanoid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class WinActivity extends Activity implements View.OnClickListener
{
    long score, bonus;

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.win);

        score = getIntent().getLongExtra(Globals.KEY_SCORE, 0);
        bonus = getIntent().getLongExtra(Globals.KEY_BONUS, 0);
        ((TextView) findViewById(R.id.tvScore)).setText("Score: "+score);
        ((TextView) findViewById(R.id.tvBonus)).setText("Time Bonus: "+bonus);
        ((TextView) findViewById(R.id.tvTotalScore)).setText("Total Score: "+(score+bonus));

        findViewById(R.id.bWNext).setOnClickListener(this);
        findViewById(R.id.bWReturn).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.bWNext:
                setResult(Globals.RESULT_NEXT);
                finish();
                break;
            case R.id.bWReturn:
                setResult(Globals.RESULT_RETURN);
                finish();
                break;
        }
    }
}
