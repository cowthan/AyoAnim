package org.ayo.robot.anim.layout_tween;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;
import org.ayo.robot.anim.demolist.RecyclerHelper;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public abstract class BaseDemoLayoutAnimation extends BasePage {

    public abstract Animation getLayoutAnimation();

    FrameLayout wrapper;
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.demo_layout_transition;
    }

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {

        wrapper = id(R.id.wrapper);
        recyclerView = id(R.id.recyclerView);

        Animation a = getLayoutAnimation();
        a.setDuration(1000);
        LayoutAnimationController lc = new LayoutAnimationController(a);
        wrapper.setLayoutAnimation(lc);

        recyclerView.setLayoutAnimation(lc);
        RecyclerHelper recyclerHelper = RecyclerHelper.demoAttach(getActivity(), recyclerView);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clickAddView();
            }
        });
        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clickRemoveView();
            }
        });
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                clickChangeBound();
            }
        });

        findViewById(R.id.wrapper).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v = findViewById(R.id.iv1);
                if(v.getVisibility() == View.VISIBLE) v.setVisibility(View.GONE);
                else v.setVisibility(View.VISIBLE);
            }
        });
    }


    private void clickAddView(){
        if(iv1 != null){
            wrapper.addView(iv1, lp);
            iv1 = null;
            lp = null;
        }
    }

    ImageView iv1;
    FrameLayout.LayoutParams lp;

    private void clickRemoveView(){
        iv1 = (ImageView) wrapper.findViewById(R.id.iv1);
        if(iv1 != null){
            lp = (FrameLayout.LayoutParams) iv1.getLayoutParams();
            wrapper.removeView(iv1);
        }
    }

    private boolean isAtHome = true;
    private void clickChangeBound(){
        if(isAtHome){
            ImageView iv2 = (ImageView) wrapper.findViewById(R.id.iv2);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv2.getLayoutParams();
            lp.gravity = Gravity.BOTTOM|Gravity.RIGHT;
            iv2.setLayoutParams(lp);
        }else{
            ImageView iv2 = (ImageView) wrapper.findViewById(R.id.iv2);
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv2.getLayoutParams();
            lp.gravity = Gravity.CENTER;
            iv2.setLayoutParams(lp);
        }
        isAtHome = !isAtHome;
    }

    @Override
    protected void onDestroy2() {

    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }
}
