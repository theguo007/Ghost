package com.ghost;

import java.io.IOException;

import org.json.JSONException;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StatsFragment extends Fragment {
	private Stats mStats;
	private TextView mLongestWinningStreak;
	private TextView mLongestLosingStreak;
	private TextView mCurrentStreak;
	private TextView mTotalWins;
	private TextView mTotalLosses;
	private TextView mLongestWord;
	private TextView mWinPercentage;
	private Button mBackButton;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			mStats = Stats.get(getActivity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.stats_sheet, parent, false);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActivity().getActionBar().hide();	
		}
		mLongestWinningStreak = (TextView) v.findViewById(R.id.longest_winning_streak);
		mLongestWinningStreak.setText("Longest Winning Streak: " + mStats.getLongestWinningStreak());
		mLongestLosingStreak = (TextView) v.findViewById(R.id.longest_losing_streak);
		mLongestLosingStreak.setText("Longest Losing Streak: " + mStats.getLongestLosingStreak());
		mCurrentStreak = (TextView) v.findViewById(R.id.current_streak);
		mCurrentStreak.setText("Current Streak: " + mStats.getCurrentStreak());
		mTotalWins = (TextView) v.findViewById(R.id.total_wins);
		mTotalWins.setText("Total Wins: " + mStats.getTotalWins());
		mTotalLosses = (TextView) v.findViewById(R.id.total_losses);
		mTotalLosses.setText("Total Losses: " + mStats.getTotalLosses());
		mLongestWord = (TextView) v.findViewById(R.id.longest_word);
		mLongestWord.setText("Longest Word: " + mStats.getLongestWord());
		mWinPercentage = (TextView) v.findViewById(R.id.win_percentage);
		mWinPercentage.setText("Win Percentage: " + mStats.getWinPercentage() + "%");
		mBackButton = (Button) v.findViewById(R.id.backButton);
		mBackButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish(); 				
			}
		});
		return v;
	}
}
