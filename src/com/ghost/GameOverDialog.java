package com.ghost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverDialog extends DialogFragment {
	private Button mPlayAgain;
	private Button mMainMenu;
	private Button mDefinition;	
	private Button mStatsButton;
	private TextView mExplanation;
	private String currentWord;
	private String definitionWord;
	public final static String winStatus = "Did you win?";
	public final static String definition = "definition";
	public final static String impossible = "not a word";
	
	public static GameOverDialog newInstance(Boolean win, String definitionWord, String currentWord) {
		Bundle bundle = new Bundle();
		bundle.putBoolean(winStatus, win);
		bundle.putString(definition, definitionWord);
		if (currentWord != null) {
			bundle.putString(impossible, currentWord);	
		} else {
			bundle.putString(impossible, null);
		}
		
		GameOverDialog instance = new GameOverDialog();
		instance.setArguments(bundle);
		
		return instance;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {		
		definitionWord = getArguments().getString(definition);
		View v = getActivity().getLayoutInflater()
		        .inflate(R.layout.game_over_dialog, null);		
		mExplanation = (TextView) v.findViewById(R.id.game_reason);
		if (getArguments().getBoolean(winStatus)) {			
			mExplanation.setText(getString(R.string.completed_word, definitionWord.substring(0, 1).toUpperCase() + definitionWord.substring(1)));
		} else if ((currentWord = getArguments().getString(impossible)) != null) {			
			mExplanation.setText(getString(R.string.impossible_word, currentWord, definitionWord));
		} else {			
			mExplanation.setText(getString(R.string.completed_word, definitionWord.substring(0, 1).toUpperCase() + definitionWord.substring(1)));
		}
		mPlayAgain = (Button) v.findViewById(R.id.play_again_button);
		mPlayAgain.setOnClickListener(new View.OnClickListener() {
			
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
		mDefinition = (Button) v.findViewById(R.id.definition);
		mDefinition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = GhostFragment.urlBeginning + definitionWord;
				Intent browserIntent = new Intent(Intent.ACTION_VIEW);
				browserIntent.setData(Uri.parse(url));
				getActivity().startActivity(browserIntent);				
			}
		});
		mStatsButton = (Button) v.findViewById(R.id.button5);
		mStatsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(getActivity(), StatsActivity.class);
				 startActivity(i);
			}
		});
		if (getArguments().getBoolean(winStatus)) {
			return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.win)
			.create();			
		} else {
			return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.lose)
			.create();
		}

	}
}
