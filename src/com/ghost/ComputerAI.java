package com.ghost;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

public class ComputerAI {
	
	private static String randomWord;
	@SuppressWarnings("unchecked")
	public static String chooseLetter(Context c, String currentWord){
		Random random = new Random();
		ArrayList<String> possibleWords = (ArrayList<String>) ListOfWords
				.get(c)
				.getPossibleWords(currentWord)
				.clone();
		if (possibleWords.size() == 0) {
			return GhostFragment.challenge;
		} else {
			int fullWordLength = currentWord.length() + 1;
			int numberOfWords = possibleWords.size();
			ArrayList<String> newWords = new ArrayList<String>();
			int index = 0;
			for (int i = 0; i < numberOfWords; i++) {
				String word = possibleWords.get(index);
				if (word.length() > fullWordLength) {
					newWords.add(word);
				}
				index++;
			}
			if (!newWords.isEmpty()) {
				randomWord = newWords.get(random.nextInt(newWords.size()));
				return randomWord.substring(currentWord.length(), currentWord.length() + 1);
			} else {
				randomWord = possibleWords.get(random.nextInt(possibleWords.size()));
				return randomWord.substring(currentWord.length(), currentWord.length() + 1);
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static String chooseLetter(Context c) {
		Random random = new Random();
		char letter = (char) (random.nextInt(26) + 'a');
		ArrayList<String> possibleWords = (ArrayList<String>) ListOfWords
				.get(c)
				.getPossibleWords(Character.toString(letter))
				.clone();
		randomWord = possibleWords.get(random.nextInt(possibleWords.size()));
		return Character.toString(letter);
	}
	
	public static String getComputerWord() {
		return randomWord;
	}
}
