import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This file holds a 'MyHashSet' of strings to be used a dictionary
 * by the AnagramSolver class.
 * @author sgb
 *
 */
public class Dictionary {
	
	private String fileName;
	private MyHashSet<String> wordSet;
	
	/**
	 * The constructor. Takes in a file name to indicate the list
	 * of words you want to use as a dictionary.
	 * @param fileName
	 */
	public Dictionary(String fileName) {
		this.fileName = fileName;
		wordSet = new MyHashSet<>();
		readFile();
	}
	
	private void readFile() {
		try {
			File inputFile = new File(fileName);
			Scanner in = new Scanner(inputFile, "utf-8");
			while (in.hasNextLine()) {
				String word = in.nextLine();
				word = word.toLowerCase();
				wordSet.add(word);
			}
			in.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.getMessage();
		}
	}
	
	/**
	 * returns true if the dictionary file contains the 
	 * string passed to this method (case-insensitive),
	 * and false otherwise.
	 * @param s any string
	 * @return true or false
	 */
	public boolean contains(String s) {
		String wordToCheck = s.toLowerCase();
		return wordSet.contains(wordToCheck);
	}
	
	public int size() {
		return wordSet.size();
	}
	
	public double loadFactor() {
		return wordSet.loadFactor();
	}
}
