package org.ayo.robot.anim.svg_vectordrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ayo.robot.anim.App;
import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;
import org.ayo.sample.menu.notify.ToasterDebug;

public class ExampleActivity extends BasePage {
  private ViewGroup container;


  private void animateDrawables(View view) {
    if(!App.supportMaterial()){
      ToasterDebug.toastShort("系统版本低于5.0，不支持AnimatedVectorDrawable");
      return;
    }
    if (!(view instanceof TextView)) {
      return;
    }
    TextView textView = (TextView) view;
    for (final Drawable drawable : textView.getCompoundDrawables()) {
      if (drawable instanceof Animatable) {
        ((Animatable) drawable).start();
        AnimatedVectorDrawable d = null;
      }
    }
  }

  @Override
  protected int getLayoutId() {
    return R.layout.ac_demo_vector_example;
  }

  @Override
  protected void onCreate2(View view, @Nullable Bundle bundle) {
    container = (ViewGroup) findViewById(R.id.container);
    container.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        for (int i = 0; i < container.getChildCount(); ++i) {
          animateDrawables(container.getChildAt(i));
        }
      }
    });
    for (int i = 0; i < container.getChildCount(); ++i) {
      View child = container.getChildAt(i);
      if (child instanceof TextView) {
        child.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            for (int i = 0; i < container.getChildCount(); ++i) {
              animateDrawables(container.getChildAt(i));
            }
          }
        });
      }
    }
  }

  @Override
  protected void onDestroy2() {

  }

  @Override
  protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

  }
}