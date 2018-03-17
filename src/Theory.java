import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Theory {

	public static void main(String[] args) {
		HashMap<Integer, String> hm = new HashMap<>();
		
		hm.put(1, "Brian");
		hm.put(2, "Aardvark");
		hm.put(3, "Alex");
		hm.put(4, "Zeke");
		hm.put(5, "Putin");
		hm.put(6, "Leo");
		hm.put(7, "Putin");
		
		List<String> sortedList = sortAlphabetically(hm);
		System.out.println(sortedList.toString());
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
