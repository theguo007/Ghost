package com.ghost;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GhostMainFragment extends Fragment {
	private Button mPlayButton;
	private Button mTutorialButton;
	private Button mStatsButton;
	
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.title_fragment, parent, false);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActivity().getActionBar().hide();	
		}
		mPlayButton = (Button) v.findViewById(R.id.backButton);
		mPlayButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				 
				Intent i = new Intent(getActivity(),GhostActivity.class);
				startActivity(i);
			}
		});
		mTutorialButton = (Button) v.findViewById(R.id.button2);
		mTutorialButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),TutorialPager.class);
				startActivity(i);
			}
		});
				
		mStatsButton = (Button) v.findViewById(R.id.button3);
		mStatsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(getActivity(), StatsActivity.class);
				 startActivity(i);
			}
		});
		return v;
	}
}
