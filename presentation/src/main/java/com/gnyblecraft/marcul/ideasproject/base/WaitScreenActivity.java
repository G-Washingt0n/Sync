package com.gnyblecraft.marcul.ideasproject.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;

/**
 * Created by lenovo on 13.10.2017.
 */

public class WaitScreenActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_wait);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WaitScreenActivity.this, ExploreActivity.class);
                startActivity(intent);
                WaitScreenActivity.this.finish();
            }
        }, 5000);

    }
}
