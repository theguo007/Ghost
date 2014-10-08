package com.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TutorialPages extends Fragment{
	private TextView mTutorialInstruction;
	private TextView mTutorialExample;
	private Button mPlay;
	public final static String TUTORIAL = "tut";
	private int[] instructions = new int[]{R.string.tut1, R.string.tut2, R.string.tut3, R.string.tut4};
	private int[] examples = new int[]{R.string.ex1, R.string.ex2, R.string.ex3, R.string.ex4};	
	private int index;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		index = getArguments().getInt(TUTORIAL);
	}
	
	public static TutorialPages newInstance(int index) {
		Bundle args = new Bundle();
		args.putInt(TUTORIAL, index);
		
		TutorialPages page = new TutorialPages();
		page.setArguments(args);
		
		return page;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tutorial_page, parent, false);
		mTutorialInstruction = (TextView) v.findViewById(R.id.instructionText);
		mTutorialInstruction.setText(instructions[index]);
		mTutorialExample = (TextView) v.findViewById(R.id.instructionExample);
		mTutorialExample.setText(examples[index]);
		mPlay = (Button) v.findViewById(R.id.play_again_button);
		if (index != 3 && index != 0) {
			mPlay.setVisibility(View.GONE);
		} else if (index == 3) {
			mPlay.setText(R.string.play_game);
			mPlay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(),GhostActivity.class);
					startActivity(i);
					getActivity().finish();
				}
			});
		} else {
			mPlay.setText(R.string.back_button);
			mPlay.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getActivity().finish();
				}
			});
		}
		return v;
		
	}
}
