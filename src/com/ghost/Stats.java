package com.ghost;

import android.content.Context;

public class Stats {
	private Context mAppContext;
	public static Stats sStats;
	private int currentStreak = 0;
	private int longestWinningStreak = 0;
	private int longestLosingStreak = 0;
	private int totalWins = 0;
	private int totalLosses = 0;
	private String longestWord = " ";
	
	private Stats(Context appContext) {
		mAppContext = appContext;  
	}
	
	public static Stats get(Context c) {
        if (sStats == null) {
            sStats = new Stats(c.getApplicationContext());
        }
        return sStats;
    }
	
	public void updateWin(String word) {
		if (currentStreak < 0) {
			currentStreak = 1;
		} else {
			currentStreak++;
			if (currentStreak > longestWinningStreak){
				longestWinningStreak=currentStreak;
			}
		}
		totalWins++;
		if (word.length() > longestWord.length()) {
			longestWord = word;
		}
	}
	
	public void updateLoss(String word) {
		if (currentStreak > 0) {
			currentStreak = -1;
		} else {
			currentStreak--;
			if (currentStreak < longestLosingStreak) {
				longestLosingStreak = currentStreak;
			}
		}
		totalLosses++;
		if (word.length() > longestWord.length()) {
			longestWord = word;
		}
	}
	
	public int getCurrentStreak() {
		return currentStreak;
	}
	
	public int getLongestLosingStreak() {
		return longestLosingStreak;
	}
	
	public int getLongestWinningStreak() {
		return longestWinningStreak;
	}
	
	public int getTotalLosses() {
		return totalLosses;
	}
	
	public int getTotalWins() {
		return totalWins;
	}

	public String getLongestWord() {
		// TODO Auto-generated method stub
		return longestWord;
	}
	
	public int getWinPercentage() {
		if (totalLosses + totalWins == 0) {
			return 0;
		} else {
			return (totalWins * 100)/ (totalLosses + totalWins);
		}
	}
}
