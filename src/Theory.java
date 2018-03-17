import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Theory {

	public static void main(String[] args) {
		HashMap<Integer, String> hm = new HashMap<>();
		
		String[] stuffToAdd = {"zeke", "harry", "astronaut", "dweevel", "citizen", "thorn", "Ji"};
		int[] hashCodes = new int[stuffToAdd.length];
		int[] bucketNumbers = new int[stuffToAdd.length];
		
		final int TABLE_LENGTH = 16;
 		for (int i = 0; i < stuffToAdd.length; i++) {
			hm.put(i, stuffToAdd[i]);
			int hashCode = stuffToAdd[i].hashCode();
			int bucketNumber = hashCode % TABLE_LENGTH;
			if (bucketNumber < 0) { bucketNumber += TABLE_LENGTH; }
			hashCodes[i] = hashCode;
			bucketNumbers[i] = bucketNumber;
		}
		
		List<String> sortedList = sortAlphabetically(hm);
		System.out.println(sortedList.toString());
		for (int i = 0; i < hashCodes.length; i++) {
			System.out.print(hashCodes[i] + ", ");
			System.out.println(bucketNumbers[i] + ", ");
		}
	}
	
	public static List<String> sortAlphabetically(HashMap<Integer, String> hm) {
		List<String> output = new ArrayList<>();
		for (Integer key : hm.keySet()) {
			output.add(hm.get(key));
		}
		Collections.sort(output);
		return output;
		
	}

}
