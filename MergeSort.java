import java.util.ArrayList;


public class MergeSort implements Sortable {
	
	private ArrayList<Integer> array;
	private int[] mergeArray;
	int length;
   
	@Override
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
		this.array = array;
		this .length = array.size();
		this.mergeArray = new int[array.size()];
		sort(0, array.size()-1);
		return this.array;
	}

   private void sort(int begin, int end) {
	   if (begin < end) {
           int middle = (end + begin) / 2;
           System.out.println("\narray.size:" + array.size());
           System.out.println("mergeArray.size:" + mergeArray.length);
           System.out.println("begin: " + begin);
           System.out.println("middle: " + middle);
           System.out.println("end:" + end);
           // Left side of the array
           sort(begin, middle);
           // Right side of the array
           sort(middle + 1, end);
           // Now merge
           merge(begin, middle, end);
       }
	}

   private void merge(int iLeft, int iRight, int rightEnd) {
	   System.out.println("\nMerging");
	   int leftEnd = iRight - 1;
       int k = iLeft; // mergeArray iterator
       int subSize = rightEnd - iLeft + 1; // sub array size

       // if leftSubArray[iLeft] < rightSubArray[iRight]
       while(iLeft <= leftEnd && iRight <= rightEnd) {
    	   System.out.println("Array left: " + array.get(iLeft) + " Array right: " + array.get(iRight));
           if(array.get(iLeft) < array.get(iRight)) {
        	   System.out.println("adding left: " + array.get(iLeft));
               mergeArray[k] = array.get(iLeft); // add left item to mergeArray
               k++; // iterate through mergeArray
               iLeft++; // left array gets smaller
           }
           else {
        	   System.out.println("adding right: " + array.get(iRight));
               mergeArray[k] = array.get(iRight); // add right item to merge array
               k++; // iterate through mergeArray
               iRight++; // right array gets smaller
           }
   		}
       while(iLeft <= leftEnd) {   // Copy rest of left half
           mergeArray[k] = array.get(iLeft); 
           k++;
           iLeft++;
       }       

       while(iRight <= rightEnd) { // Copy rest of right half
           mergeArray[k] = array.get(iRight);
           k++;
           iRight++;
       }
       // Copy mergeArray back into array
       for(int i = 0; i < subSize; i++, rightEnd--){ // right end is the only unchanged iterator
           array.set(rightEnd, mergeArray[rightEnd]); // rightmost item into rightmost position
           System.out.println("adding " + mergeArray[rightEnd]);
       }
   }


@Override
	public String getName() {
		return "MergeSort";
	}
}