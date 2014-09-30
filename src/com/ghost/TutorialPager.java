package com.ghost;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class TutorialPager extends FragmentActivity{
	private ViewPager mViewPager;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActionBar().hide();	
		}
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {
				return 4;
			}
			
			@Override
			public Fragment getItem(int pos) {
				return TutorialPages.newInstance(pos);
			}
		});
	}
}
