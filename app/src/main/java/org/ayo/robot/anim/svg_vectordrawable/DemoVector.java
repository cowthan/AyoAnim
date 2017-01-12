package org.ayo.robot.anim.svg_vectordrawable;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.ayo.component.Master;
import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;

import java.util.Arrays;
import java.util.List;

public class DemoVector extends ListActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final List<Demo> demos = Arrays.asList(
        new Demo(this, ExampleActivity.class, R.string.example_from_documentation),
        new Demo(this, RotateActivity.class, R.string.clock),
        new Demo(this, PathMorphActivity.class, R.string.smiling_face),
        new Demo(this, FillInHeartActivity.class, R.string.fill_in_heart)
    );
    ArrayAdapter<Demo> adapter = new ArrayAdapter<Demo>(
        this,
        android.R.layout.simple_list_item_1,
        demos);
    getListView().setAdapter(adapter);

    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Demo demo = demos.get(position);
        Master.startPage(DemoVector.this, demo.activityClass, null);
      }
    });

    getListView().setBackgroundColor(Color.WHITE);
  }

  public static class Demo {
    public final Class<? extends BasePage> activityClass;
    public final String title;

    public Demo(Context context, Class<?extends BasePage> activityClass, int titleId) {
      this.activityClass = activityClass;
      this.title = context.getString(titleId);
    }

    @Override
    public String toString() {
      return this.title;
    }
  }
}