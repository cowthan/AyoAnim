package org.ayo.robot.anim.layout;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;
import org.ayo.robot.anim.demolist.RecyclerHelper;
import org.ayo.sample.menu.notify.ToasterDebug;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class DemoLayoutTransition extends BasePage {

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

        ObjectAnimator slide_out_to_left = new ObjectAnimator();
        slide_out_to_left.setPropertyName("translationX");
        slide_out_to_left.setDuration(1000);
        slide_out_to_left.setFloatValues(0, -500);

        ObjectAnimator slide_in_from_left = new ObjectAnimator();
        slide_in_from_left.setPropertyName("translationX");
        slide_in_from_left.setDuration(1000);
        slide_in_from_left.setFloatValues(-500, 0);

        ObjectAnimator slide_out_to_top = new ObjectAnimator();
        slide_out_to_top.setPropertyName("translationY");
        slide_out_to_top.setDuration(1000);
        slide_out_to_top.setFloatValues(0, -500);

        ObjectAnimator slide_in_from_top = new ObjectAnimator();
        slide_in_from_top.setPropertyName("translationY");
        slide_in_from_top.setDuration(1000);
        slide_in_from_top.setFloatValues(-500, 0);

        ObjectAnimator scaleX = new ObjectAnimator();
        scaleX.setPropertyName("scaleX");
        scaleX.setDuration(1000);
        scaleX.setFloatValues(1, 0.2f);

        LayoutTransition transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                if(transitionType == LayoutTransition.APPEARING){
                    ToasterDebug.toastShort("APPEARING");
                }else if(transitionType == LayoutTransition.DISAPPEARING){
                    ToasterDebug.toastShort("DISAPPEARING");
                }else if(transitionType == LayoutTransition.CHANGE_APPEARING){
                    ToasterDebug.toastShort("CHANGE_APPEARING");
                }else if(transitionType == LayoutTransition.CHANGE_DISAPPEARING){
                    ToasterDebug.toastShort("CHANGE_DISAPPEARING");
                }else if(transitionType == LayoutTransition.CHANGING){
                    ToasterDebug.toastShort("CHANGING");
                }
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

            }
        });
        transition.setAnimator(LayoutTransition.APPEARING, slide_in_from_left);
        transition.setAnimator(LayoutTransition.DISAPPEARING, slide_out_to_left);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, slide_in_from_top);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, slide_out_to_top);
        transition.setAnimator(LayoutTransition.CHANGING, scaleX);
        wrapper.setLayoutTransition(transition);

        transition = new LayoutTransition();

        transition.setAnimator(LayoutTransition.APPEARING, slide_in_from_left);
        transition.setAnimator(LayoutTransition.DISAPPEARING, slide_out_to_left);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, slide_in_from_top);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, slide_out_to_top);
        transition.setAnimator(LayoutTransition.CHANGING, scaleX);
        recyclerView.setLayoutTransition(transition);
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

    @Override
    protected void onDestroy2() {
        
    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

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
}
