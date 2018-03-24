import java.util.ArrayList;
import java.util.HashMap;
/**
 * The AnagramSolver class contains the main() method. It implements a recursive
 * method called getCombination() that assembles every possible letter combination
 * given the entered string. If the entered string is 'bkae', it will proceed to 
 * create combinations like 'bbbb', 'bbbk', 'bbba', and so on. Clearly these cannot
 * be considered candidates to search the dictionary for matches with, so it only accepts
 * combinations that have the same HashMap as what was entered: in this case 1 'b', 1 'k',
 * 1 'a', and 1 'e'. 
 * 
 * @author sgb
 *
 */
public class AnagramSolver {
	private Dictionary dictionary;
	private ArrayList<String> wordCandidates;
	private HashMap<Character, Integer> lettersMap;
	
	/**
	 * Constructor takes in a file name for the dictionary that the AnagramSolver
	 * will check against.
	 * 
	 * @param dictFileName
	 */
	public AnagramSolver(String dictFileName) {
		dictionary = new Dictionary(dictFileName);
		wordCandidates = new ArrayList<>();
	}
	
	/**
	 * This method creates the 'lettersMap' to verify candidate strings with.
	 * It checks the results of the getCombination() method against the dictionary,
	 * and adds the solutions to an ArrayList.
	 * 
	 * @param jumbledWord the string to be 'un-jumbled'
	 * @return an ArrayList of solutions
	 */
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
	/**
	 * A helper function to create a HashMap where the letters of a string are the keys, and the
	 * values are the number of times that letter appears.
	 * 
	 * If you wanted to create a 'CharCountMap' of a string, first create a char[], then pass it to
	 * both arguments of this method. If you wanted to create a 'CharCountMap' of a string as a function of another string,
	 * then create a char[] of both and pass the one you want to use as the keys to the first argument, and the one you want to
	 * use as the values to the second argument.
	 * 
	 * @param keys a char array to be used as the keySet for the HashMap, can include dups
	 * @param charsToCount a char array to be used as the values for the HashMap - chars to count instances of
	 * @return
	 */
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
	/**
	 * This recursive method assembles all possible letter combinations, without replacement, of a given char array.
	 * When you first use the method, enter '""', or an empty string, to the 'current' argument.
	 * 
	 * @param letters
	 * @param current
	 */
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
		} else {
			for (char c : letters) {
				String old = current;
				current = current + c;
				getCombination(letters, current);
				current = old;
			}
		}
	}
	/**
	 * Main runs the game. Simply enter the string that you want 'un-jumbled' into
	 * the solveAnagram() method.
	 */
	public static void main(String[] args) {
		AnagramSolver anagramSolver = new AnagramSolver("words.txt");
		ArrayList<String> solutions = anagramSolver.solveAnagram("LUSODH");
		if (solutions.size() == 0) { System.out.println("No solutions."); }
		for (String s : solutions) {
			System.out.println(s);
		}
	}
}
