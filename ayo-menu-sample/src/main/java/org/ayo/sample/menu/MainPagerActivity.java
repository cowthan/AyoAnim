package org.ayo.sample.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.ayo.core.activity.AyoActivity;
import org.ayo.sample.menu.widget.CustomRadioGroup;
import org.ayo.sample.menu.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页，主要就是个ViewPager，和下面的导航，纠结的问题是标题栏要放在这里，还是放在各个Fragment，
 * 
 * 后来一想，左右滑动切换时，标题栏不能滑动吧，所以就在主Activity里加标题栏吧
 *
 */
public abstract class MainPagerActivity extends AyoActivity implements View.OnClickListener{
	
	private CustomRadioGroup footer;
	private ViewPager body;


	public abstract List<Menu> getMenus();

	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ayo_sample_menu_ac_main_cw);


		titlebar = (TitleBar) findViewById(R.id.titlebar);
		titlebar.callback(titlebarCallback)
			.leftButton(0)
			.bgColor(R.color.common_top_bar_blue)
			.title(getMenus().get(0).name);



		initContentView();


	}
	
	private TitleBar.Callback titlebarCallback = new TitleBar.Callback() {

		@Override
		public void onRightButtonClicked(int index, View v) {

		}

		@Override
		public void onLeftButtonClicked(View v) {
		}
	};


	private TitleBar titlebar;

	public void initContentView() {
		// 底部
		footer = (CustomRadioGroup) findViewById(R.id.main_footer);
		for (int i = 0; i <getMenus().size(); i++) {
			footer.addItem(getMenus().get(i).iconNormal,
					getMenus().get(i).iconPressed,
					getMenus().get(i).name);
		}
		// 主体
		body = (ViewPager) findViewById(R.id.main_body);
		
		final MainBodyPageChangeListener bodyChangeListener = new MainBodyPageChangeListener(
				footer);
		body.setOnPageChangeListener(bodyChangeListener);

		initFragments();
		body.setOffscreenPageLimit(10);
		MainPagerAdapter mAdapetr = new MainPagerAdapter(getSupportFragmentManager(), fragments);
		body.setAdapter(mAdapetr);
		body.setCurrentItem(0);
		
		footer.setCheckedIndex(body.getCurrentItem());
		footer.setOnItemChangedListener(new CustomRadioGroup.OnItemChangedListener() {
			public void onItemChanged() {
				body.setCurrentItem(footer.getCheckedIndex(), false);
			}
		});
		
		//footer.setItemNewsCount(1, 10);// 设置消息数量

	}

	private void initFragments() {
		fragments.clear();

		for(Menu menu: getMenus()){
			MenuPagerFragment frag1 = new MenuPagerFragment();
			frag1.setMenu(menu);
			fragments.add(frag1);
		}

//		for(Menu menu: Config.getDefault().getMenus()){
//			MenuFragment frag1 = new MenuFragment();
//			frag1.setMenu(menu);
//			fragments.add(frag1);
//		}

	}

	class MainBodyPageChangeListener implements ViewPager.OnPageChangeListener {
		private CustomRadioGroup customRadioGroup;

		public MainBodyPageChangeListener(CustomRadioGroup c) {
			this.customRadioGroup = c;
		}

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if (arg1 != 0.0f) {
				int right, left;
				if (arg0 == customRadioGroup.getCheckedIndex()) {
					// 方向向右
					left = customRadioGroup.getCheckedIndex();
					right = customRadioGroup.getCheckedIndex() + 1;
				} else {
					// 方向向左
					left = customRadioGroup.getCheckedIndex() - 1;
					right = customRadioGroup.getCheckedIndex();

				}
				customRadioGroup.itemChangeChecked(left, right, arg1);
			} else {
				customRadioGroup.setCheckedIndex(arg0);
			}
		}

		public void onPageSelected(int arg0) {
			titlebar.title(getMenus().get(arg0).name);
		}

	}

	@Override
	public void onClick(View v) {

	}

}
