package org.ayo.robot.anim.svg_vectordrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import org.ayo.robot.anim.App;
import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;
import org.ayo.sample.menu.notify.ToasterDebug;

public abstract class AnimatedImageActivity extends BasePage {
  private ImageView imageView;

  @Override
  protected void onCreate2(View view, @Nullable Bundle bundle) {
    imageView = (ImageView) findViewById(R.id.image);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        animate();
      }
    });
  }

  private void animate() {
    if(!App.supportMaterial()){
      ToasterDebug.toastShort("系统版本低于5.0，不支持AnimatedVectorDrawable");
      return;
    }
    Drawable drawable = imageView.getDrawable();
    if (drawable instanceof Animatable) {
      ((Animatable) drawable).start();
    }
  }

  @Override
  protected void onDestroy2() {

  }

  @Override
  protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

  }

}