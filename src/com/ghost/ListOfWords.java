package com.ghost;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.content.Context;

public class ListOfWords {

	private ArrayList<String> mPossibleWords;
	private String mCurrentWord;
	private static ListOfWords sListOfWords;
    private Context mAppContext;
    
    private ListOfWords(Context appContext) {    	
        mAppContext = appContext;             
    }
    
    public static ListOfWords get(Context c) {    	
    	if (sListOfWords == null) {
    		sListOfWords = new ListOfWords(c.getApplicationContext());
    	}    	
    	return sListOfWords;
    }
    
    private void updateCurrentWord(String currentWord) {
    	mCurrentWord = currentWord;
    }
    
    private InputStream generateStream(String letter) {
    	if (letter.equals("a")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_a);
    	} else if (letter.equals("b")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_b);
    	} else if (letter.equals("c")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_c);
    	} else if (letter.equals("d")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_d);
    	} else if (letter.equals("e")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_e);
    	} else if (letter.equals("f")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_f);
    	} else if (letter.equals("g")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_g);
    	} else if (letter.equals("h")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_h);
    	} else if (letter.equals("i")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_i);
    	} else if (letter.equals("j")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_j);
    	} else if (letter.equals("k")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_k);
    	} else if (letter.equals("l")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_l);
    	} else if (letter.equals("m")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_m);
    	} else if (letter.equals("n")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_n);
    	} else if (letter.equals("o")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_o);
    	} else if (letter.equals("p")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_p);
    	} else if (letter.equals("q")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_q);
    	} else if (letter.equals("r")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_r);
    	} else if (letter.equals("s")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_s);
    	} else if (letter.equals("t")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_t);
    	} else if (letter.equals("u")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_u);
    	} else if (letter.equals("v")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_v);
    	} else if (letter.equals("w")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_w);
    	} else if (letter.equals("x")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_x);
    	} else if (letter.equals("y")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_y);
    	} else if (letter.equals("z")) {
    		return mAppContext.getResources().openRawResource(R.raw.words_z);
    	}
    	return null;
    }
    
    private void update(String currentWord) {
    	updateCurrentWord(currentWord);
    	if (currentWord.length() == 1) {    		
            try {
            	InputStream wordStream = generateStream(currentWord);
    			mPossibleWords = FileReader.readLines(wordStream);
    		} catch (IOException e) {			 
    			e.printStackTrace();
    		}  
    	} else {
    		int numberOfWords = mPossibleWords.size();
    		int index = 0;
    		ArrayList<String> newWords = new ArrayList<String>();
    		for (int i = 0; i < numberOfWords; i++) {
    			String word = mPossibleWords.get(index);
    			if (word.startsWith(mCurrentWord)) {
    				newWords.add(word);
    			} else {
    				if (!newWords.isEmpty()) {
    					mPossibleWords = newWords;
    					break;
    				}
    			}
    			index++;
    		}
    		mPossibleWords = newWords;
    	}
    }
    
    public ArrayList<String> getPossibleWords(String currentWord) {
    	update(currentWord);
    	return mPossibleWords;
    }
}
