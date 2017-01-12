package org.ayo.robot.anim.layout;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class DemoLayoutTransition2 extends BasePage {



    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);
    }

    @Override
    protected void onDestroy2() {

    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }
}
