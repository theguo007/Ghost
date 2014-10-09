package com.ghost;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Stats {	
	public Context mAppContext;
	public static Stats sStats;
	private static final String TAG = "STATS";
    private static final String FILENAME = "stats.json";
	private int currentStreak;
	private int longestWinningStreak;
	private int longestLosingStreak;
	private int totalWins;
	private int totalLosses;
	private String longestWord;
	public final String JSONLW = "longest word";
	public final String JSONLWS = "longest winning streak";
	public final String JSONLLS = "longest losing streak";
	public final String JSONCS = "computer science";
	public final String JSONTW = "totwl wins";
	public final String JSONTL = "total losses";
	JSONSerializer mSerializer;
	
	private Stats(Context AppContext) {	
		mSerializer = new JSONSerializer(AppContext, FILENAME);
       	try {
			JSONObject yup = mSerializer.loadStats();
			longestWord = yup.getString(JSONLW);
			totalWins = yup.getInt(JSONTW);
			totalLosses = yup.getInt(JSONTL);
			currentStreak = yup.getInt(JSONCS);
			longestWinningStreak = yup.getInt(JSONLWS);
			longestLosingStreak = yup.getInt(JSONLLS);
		} catch (Exception e) {
			currentStreak = 0;
	   		longestWinningStreak = 0;
	   		longestLosingStreak = 0;
	   		totalWins = 0;
	   		totalLosses = 0;
	   		longestWord = " ";
			Log.e(TAG, "Error loading stats: ", e);
		}     
       	mAppContext = AppContext;  
	}
	
	public static Stats get(Context c) throws IOException, JSONException {
		
        if (sStats == null) {
        	sStats = new Stats(c);
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
		return longestWord;
	}
	
	public int getWinPercentage() {
		if (totalLosses + totalWins == 0) {
			return 0;
		} else {
			return (totalWins * 100)/ (totalLosses + totalWins);
		}
	}
	public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSONLW, longestWord);
        json.put(JSONCS, currentStreak);
        json.put(JSONLWS, longestWinningStreak);
        json.put(JSONLLS, longestLosingStreak);
        json.put(JSONTW, totalWins);
        json.put(JSONTL, totalLosses);
        return json;
	}
	public boolean saveStats() {
        try {
            mSerializer.saveStats(sStats);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }
}
