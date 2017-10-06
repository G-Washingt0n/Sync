package com.gnyblecraft.marcul.ideasproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by AndroidDeveloper on 29.09.17.
 */

public class StartLogoActivity extends Activity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartLogoActivity.this, ExploreActivity.class);
                StartLogoActivity.this.startActivity(intent);
                StartLogoActivity.this.finish();
            }
        }, 2500);

    }
}
