package com.ghost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverDialog extends DialogFragment {
	private Button mPlayAgain;
	private Button mMainMenu;
	private Button mDefinition;
	private TextView mWinStatement;
	private TextView mExplanation;
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
		String currentWord;
		String definitionWord = getArguments().getString(definition);
		View v = getActivity().getLayoutInflater()
		        .inflate(R.layout.game_over_dialog, null);
		mWinStatement = (TextView) v.findViewById(R.id.game_status);
		mExplanation = (TextView) v.findViewById(R.id.game_reason);
		if (getArguments().getBoolean(winStatus)) {
			mWinStatement.setText(R.string.win);
			mExplanation.setText(getString(R.string.completed_word, definitionWord.substring(0, 1).toUpperCase() + definitionWord.substring(1)));
		} else if ((currentWord = getArguments().getString(impossible)) != null) {
			mWinStatement.setText(R.string.lose);
			mExplanation.setText(getString(R.string.impossible_word, currentWord, definitionWord));
		} else {
			mWinStatement.setText(R.string.lose);
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
		
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.game_over)
			.create();
	}
}
