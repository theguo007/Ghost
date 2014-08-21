package com.ghost;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	public static ArrayList<String> readLines(InputStream file) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();		
	    Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			lines.add(sc.next());
		}
		sc.close();
	    return lines;
	}
}

