package org.ayo.robot.anim.layout;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.ViewGroup;

import org.ayo.robot.anim.R;
import org.ayo.sample.menu.attacher.ActivityAttacher;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class DemoLayoutTransition2 extends ActivityAttacher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);

    }
}
