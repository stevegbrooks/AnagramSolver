import java.util.ArrayList;

public class AnagramSolver {
	private Dictionary dictionary;
	
	public AnagramSolver(String dictFileName) {
		dictionary = new Dictionary(dictFileName);
	}
	
	public String solveAnagram(String jumbledWord) {
		ArrayList<String> possibilities = findAllCombinations(jumbledWord);
		StringBuilder solutions = new StringBuilder();
		solutions.append("Solutions: ");
		
		for (String string : possibilities) {
			if (dictionary.contains(string)) {
				solutions.append(string + ", ");
			}
		}
		
		solutions.setLength(solutions.length() - 2);
		return solutions.toString();
	}
	
	private ArrayList<String> findAllCombinations(String jumbledWord) {
		return null;
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
