package com.ghost;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

public class ComputerAI {
	
	private static String randomWord;
	@SuppressWarnings("unchecked")
	public static String chooseLetter(Context c, String currentWord){
//		stallComputer(1500);
		Random mRandom = new Random();
		ArrayList<String> possibleWords = (ArrayList<String>) ListOfWords
				.get(c)
				.getPossibleWords(currentWord)
				.clone();
		if (possibleWords.size() == 0) {
			return GhostFragment.challenge; // replace this with a final constant later
		} else {
			int fullWordLength = currentWord.length() + 1;
			int numberOfWords = possibleWords.size();
			int index = 0;
			for (int i = 0; i < numberOfWords; i++) {
				String word = possibleWords.get(index);
				if (word.length() == (fullWordLength)) {
					possibleWords.remove(index);
				}
			}
			if (possibleWords.isEmpty()) {
				possibleWords = (ArrayList<String>) ListOfWords
						.get(c)
						.getPossibleWords(currentWord)
						.clone();
			}
			randomWord = possibleWords.get(mRandom.nextInt(possibleWords.size()));
//			System.out.println(randomWord);
			return randomWord.substring(currentWord.length(), currentWord.length() + 1);
		}
	}
	
	private static void stallComputer(int milliseconds) {
		try {
		    Thread.sleep(milliseconds);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}		
	}
	
	public static String getComputerWord() {
		return randomWord;
	}
}
