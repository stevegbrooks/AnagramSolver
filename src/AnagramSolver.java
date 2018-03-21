import java.util.ArrayList;
import java.util.HashMap;

public class AnagramSolver {
	private Dictionary dictionary;
	private ArrayList<String> wordCandidates;
	private HashMap<Character, Integer> lettersMap;
	
	public AnagramSolver(String dictFileName) {
		dictionary = new Dictionary(dictFileName);
		wordCandidates = new ArrayList<>();
	}
	
	public ArrayList<String> solveAnagram(String jumbledWord) {
		char[] letters = jumbledWord.toCharArray();
		lettersMap = createCharCountMap(letters, letters);
		getCombination(letters, "");

		ArrayList<String> solutions = new ArrayList<>();
		
		for (String candidate : wordCandidates) {
			if (dictionary.size() == 0) {
				throw new IllegalStateException("Dictionary is empty!");
			}
			if (dictionary.contains(candidate)) { 
				solutions.add(candidate); 
			}
		}
		return solutions;
	}
	private HashMap<Character, Integer> createCharCountMap(char[] keys, char[] charsToCount) {
		HashMap<Character, Integer> output = new HashMap<>();
		int counter = 0;
		for (char c : keys) {
			for (char s : charsToCount) {
				if (c == s) {
					counter++;
				}
			}
			output.put(c, counter);
			counter = 0;
		}
		return output;
	}
	private void getCombination(char[] letters, String current) {
		if (current.length() == letters.length) {
			char[] currentArray = current.toCharArray();
			HashMap<Character, Integer> currentMap = createCharCountMap(letters, currentArray);
			boolean areMapsEqual = true;
			for (Character c : currentMap.keySet()) {
				if (currentMap.get(c) != lettersMap.get(c)) {
					areMapsEqual = false;
				}
			}
			if (areMapsEqual && !wordCandidates.contains(current)) {
				wordCandidates.add(current);
			}
			//wordCandidates.add(current);
		} else {
			for (char c : letters) {
				String old = current;
				current = current + c;
				getCombination(letters, current);
				current = old;
			}
		}
	}
	
	public static void main(String[] args) {
		AnagramSolver anagramSolver = new AnagramSolver("words.txt");
		ArrayList<String> solutions = anagramSolver.solveAnagram("bake");
		if (solutions.size() == 0) { System.out.println("No solutions."); }
		for (String s : solutions) {
			System.out.println(s);
		}
		
//		System.out.println("Does dictionary contain 'pathetic'?: " + dict.contains("pathetic"));
//		System.out.println("Size of dictionary: " + dict.size());
//		System.out.println("Load factor of dictionary hashTable: " + dict.loadFactor());
//		System.out.println("Capacity of dictionary hashTable: " + (double) dict.size()/dict.loadFactor());
//		System.out.println("Bucket of 'pathetic': " + "pathetic".hashCode() % ((double) dict.size()/dict.loadFactor()));
//		System.out.println("HashCode of 'pathetic': " + "pathetic".hashCode());
	}
}
