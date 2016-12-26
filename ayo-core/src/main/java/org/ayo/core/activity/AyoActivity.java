package org.ayo.core.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.ayo.core.app.AyoActivityManager;

/**
 */
public class AyoActivity extends AppCompatActivity {

    public ActivityDelegate agent = new ActivityDelegate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        agent.attach(this);
        AyoActivityManager.getDefault().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        agent.detach();
        AyoActivityManager.getDefault().popActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AyoActivityManager.getDefault().onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AyoActivityManager.getDefault().onStop(this);
    }

    protected AyoActivity getActivity(){
        return this;
    }

    public <T> T id(int id){
        return (T)findViewById(id);
    }

    public ActivityDelegate getAgent(){
        return agent;
    }
}
