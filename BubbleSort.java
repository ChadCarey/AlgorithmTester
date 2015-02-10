import java.util.ArrayList;


public class BubbleSort implements Sortable {

	private ArrayList<Integer> array;
	
	private void swap(int i, int j) {
		int temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}
	
	@Override
	public ArrayList<Integer> sort(ArrayList<Integer> newArray) {
		array = newArray;
		for(int i = 0; i < array.size(); ++i) {
			for(int j = i; j < array.size(); ++j) {
				if( array.get(i) > array.get(j) ) {
					swap(i,j);
				}
			}
		}
		return array;
	}

	@Override
	public String getName() {
		return "BubbleSort";
	}

}
