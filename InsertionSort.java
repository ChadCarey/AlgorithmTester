import java.util.ArrayList;


public class InsertionSort implements Sortable {
	
	@Override
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
		for (int i = 0; i < array.size(); ++i) {
			int x = array.get(i);
			int j = i;
			while(j > 0 && array.get(j-1) > x) {
				array.set(j, array.get(j-1));
				j = j - 1;
			}
			array.set(j, x);
		}
		return array;
	}

	@Override
	public String getName() {
		return "InsertionSort";
	}

}
