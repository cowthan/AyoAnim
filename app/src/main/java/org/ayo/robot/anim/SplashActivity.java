package org.ayo.robot.anim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by Administrator on 2016/12/20.
 */

public class SplashActivity extends AyoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.ayoa_fade_in, R.anim.ayoa_fade_out);
                finish();
            }
        }, 1500);
    }
}
