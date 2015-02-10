import java.util.ArrayList;


public class QuickSort implements Sortable {
  private ArrayList<Integer> array;
  private int size;

	@Override
	public ArrayList<Integer> sort(ArrayList<Integer> newArray) {
	    this.array = newArray;
	    size = newArray.size();
	    quicksort(0, size - 1);
		return null;
	}

	@Override
	public String getName() {
		return "QuickSort";
	}

  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Pivot element from the middle of the list
    int pivot = array.get(low + (high-low)/2);

    // Split into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list (leave it where it is)
      while (array.get(i) < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (array.get(j) > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion (Maybe i'll change this later)
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j) {
    int temp = array.get(i);
    array.set(i, array.get(j));
    array.set(j, temp);
  }
} 
