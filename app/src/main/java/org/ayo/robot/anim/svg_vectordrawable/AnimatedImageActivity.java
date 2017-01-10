package org.ayo.robot.anim.svg_vectordrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.ayo.robot.anim.R;
import org.ayo.sample.menu.attacher.ActivityAttacher;

public abstract class AnimatedImageActivity extends ActivityAttacher {
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    imageView = (ImageView) findViewById(R.id.image);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        animate();
      }
    });
  }

  private void animate() {
    Drawable drawable = imageView.getDrawable();
    if (drawable instanceof Animatable) {
      ((Animatable) drawable).start();
    }
  }

  protected abstract int getLayoutId();
}