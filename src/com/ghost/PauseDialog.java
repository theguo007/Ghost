package com.ghost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;

public class PauseDialog extends DialogFragment {
	private Button mRestart;
	private Button mMainMenu;
	private Button mTutorial;
	private Button mStatsButton;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View v = getActivity().getLayoutInflater().inflate(R.layout.pause_dialog, null);
		mRestart = (Button) v.findViewById(R.id.restart_button);
		mRestart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getActivity().getIntent();
				getActivity().finish();
				startActivity(intent); 			
			}
		});
		mMainMenu = (Button) v.findViewById(R.id.main_menu_button);
		mMainMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 getActivity().finish();
			}
		});
		mTutorial = (Button) v.findViewById(R.id.instructions_button);
		mTutorial.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),TutorialPager.class);
				startActivity(i);					
			}
		});
		mStatsButton = (Button) v.findViewById(R.id.button4);
		mStatsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(getActivity(), StatsActivity.class);
				 startActivity(i);
			}
		});
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.pause)
		.create();	
	}
}
