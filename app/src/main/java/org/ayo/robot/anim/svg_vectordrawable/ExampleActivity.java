package org.ayo.robot.anim.svg_vectordrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ayo.robot.anim.R;
import org.ayo.sample.menu.attacher.ActivityAttacher;

public class ExampleActivity extends ActivityAttacher {
  private ViewGroup container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ac_demo_vector_example);

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

  private void animateDrawables(View view) {
    if (!(view instanceof TextView)) {
      return;
    }
    TextView textView = (TextView) view;
    for (final Drawable drawable : textView.getCompoundDrawables()) {
      if (drawable instanceof Animatable) {
        ((Animatable) drawable).start();
      }
    }
  }
}