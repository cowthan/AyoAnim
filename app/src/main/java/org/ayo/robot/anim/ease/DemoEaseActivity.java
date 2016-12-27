package org.ayo.robot.anim.ease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.ayo.core.activity.AyoActivity;
import org.ayo.robot.anim.R;


/**
 * 主界面
 */
public class DemoEaseActivity extends AyoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_ease_demo_main);
    }

    public void btnInterpolator(View view) {
        getActivity().startActivity(new Intent(getActivity(), InterpolatorActivity.class));
    }

    public void btnEvaluator(View view) {
        getActivity().startActivity(new Intent(getActivity(), EvaluatorActivity.class));
    }

    public void btnDemo(View view) {
        getActivity().startActivity(new Intent(getActivity(), DemoActivity.class));
    }

    public void btnBounce(View view) {
        getActivity().startActivity(new Intent(getActivity(), BounceActivity.class));
    }

    public void btnBounce2(View view) {
        getActivity().startActivity(new Intent(getActivity(), Bounce2Activity.class));
    }

    public void btnCustom(View view) {
        getActivity().startActivity(new Intent(getActivity(), CustomActivity.class));
    }
}
