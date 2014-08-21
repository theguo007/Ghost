package com.ghost;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GhostFragment extends Fragment {
	private EditText mUserInput;
	private Button mOkButton;
	private TextView mComputerResponse;
	private TextView mCurrentWord;
	public static final String challenge = "challenge";
	public static final String win = "You win!";
	public static final String notWordLose="That won't form a word. You lose.";
	public static final String completedLose = "You formed a word. You lose.";
	private String mUserTyping;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                        
        getActivity().setTitle(R.string.app_name);
    }
	
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ghost_fragment, parent, false);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActivity().getActionBar().hide();	
		}
		mCurrentWord = (TextView) v.findViewById(R.id.current_word);
		mComputerResponse = (TextView) v.findViewById(R.id.computer_response);
		mOkButton = (Button) v.findViewById(R.id.ok_button);
		mOkButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 if (mUserTyping != null) {
					 char mUserMove = mUserTyping.toLowerCase()
							 .charAt(0);
					 if (mUserMove >= 'a' && mUserMove <= 'z') {
						 makeComputerMove();
						 mUserInput.setText("");
					 } else {
						 Toast.makeText(getActivity(), R.string.must_be_letter, Toast.LENGTH_SHORT).show();
					 }
				 } 
			}
		});
		mUserInput = (EditText) v.findViewById(R.id.user_input);
		mUserInput.addTextChangedListener(new TextWatcher() {
            @Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
                GhostFragment.this.mUserTyping = c.toString();
            }

            @Override
			public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
			public void afterTextChanged(Editable c) {
                // This one too
            }
        });
		return v;
	}
	
	private void updateCurrentWord(String word) {
		mCurrentWord.setText(word);
	}
	
	private void makeComputerMove() {		
		updateCurrentWord(mCurrentWord.getText() + mUserTyping);		
		mUserTyping = null;
		if (isWord(mCurrentWord.getText()
			.toString())) {			 
			 gameOver(false, mCurrentWord.getText().toString(), null);
		} else {			
			String computerMove = ComputerAI.chooseLetter(getActivity(), mCurrentWord.getText().toString());
			if (computerMove == challenge) {				 
				 gameOver(false, ComputerAI.getComputerWord(), mCurrentWord.getText().toString());
			} else {
				updateCurrentWord(mCurrentWord.getText() + computerMove);
				if (isWord(mCurrentWord.getText()
					.toString())) {					 
					gameOver(true, mCurrentWord.getText().toString(), null);
				 }
			 }
		 }
	}
	
	private void gameOver(Boolean isWin, String definition, String currentWord) {
		mUserInput.setFocusable(false);
		mOkButton.setEnabled(false);
		FragmentManager fm = getActivity().getSupportFragmentManager();
		GameOverDialog gG = GameOverDialog.newInstance(isWin, definition, currentWord);
		gG.show(fm, "doesn't matter");
	}

	/*
	 * Takes in a string and returns whether it's a word.
	 * @word is the word that the function is testing.
	 */
	private Boolean isWord(String word) {
		ArrayList<String> possibleWords = ListOfWords.get(getActivity())
				.getPossibleWords(word);
		for (String i : possibleWords) {
			if (word.equals(i)) {
				return true;
			}
		}
		return false;
	}
}