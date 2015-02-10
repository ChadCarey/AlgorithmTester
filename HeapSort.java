import java.util.ArrayList;


public class HeapSort implements Sortable
{

	
	//@Override
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
                int count = array.size();
		heapify(array, count);
		int end = count-1;
		while(end > 0)
		{
			swap(array, end, 0);
			end = end - 1;
			siftDown(array, 0, end);
		}
		
                
                /*for (int i = 0; i < array.size() - 1; i++)
                {
                    if ( array.get(i) > array.get(i + 1))
                    {
                        System.out.println("false");
                       
                    }
                }
                
                System.out.println("true");*/
                return array;
	}

	//@Override
	public String getName() {
		return "HeapSort";
	}
	
    public void heapify(ArrayList<Integer> unsortedList, int count)
	{
		int start = count/2 - 1;
		while(start >= 0)
		{
			siftDown(unsortedList, start, count - 1);
			start -= 1;
		}
	}
	public void siftDown(ArrayList<Integer> unsortedList, int start, int end)
	{
		int root = start;
		while(root*2+1 <= end)
		{
			int child = root*2+1;
			int swap = root;
			if(unsortedList.get(swap) < unsortedList.get(child))
			{
				swap = child;
			}
			if(child+1 <= end && unsortedList.get(swap) < unsortedList.get(child+1))
			{
				swap = child+1;
			}
			if(swap != root)
			{
				swap(unsortedList, root, swap);
				root = swap;
			}
			else
			{
				return;
			}
		}
	}
	public void swap(ArrayList<Integer> unsortedList, int swapOne, int swapTwo)
	{
		int holder = unsortedList.get(swapOne);
		unsortedList.set(swapOne, unsortedList.get(swapTwo));
		unsortedList.set(swapTwo, holder);
	}
        
        /**
	 * generates an array based on the given size
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateArray(int size) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < size; ++i) {
			list.add( (int)(Math.random() * size) );
		}
		return list;
	}
    
    public static void main(String[] args) {
    	HeapSort sort = new HeapSort();
    	sort.sort(generateArray(100000));
    }
}