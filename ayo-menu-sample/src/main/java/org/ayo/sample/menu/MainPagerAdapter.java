package org.ayo.sample.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<Fragment> fragments;
	private FragmentManager fm;

	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public MainPagerAdapter(FragmentManager fm,
			ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}


	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}

}
