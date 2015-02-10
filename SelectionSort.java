import java.util.ArrayList;

public class SelectionSort implements Sortable
{
    //@Override
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
                
            for (int i = 0; i < array.size() - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < array.size(); j++)
                {
                    if (array.get(j) < array.get(min))
                    {
                        min = j;
                    }
                    
                }
                swap(array,i,min);
            }
                
                /*for (int i = 0; i < array.size() - 1; i++)
                {
                    if ( array.get(i) > array.get(i + 1))
                    {
                        System.out.println("false");
                        System.out.println(i);
                        
                    }
                }*/
                
                //System.out.println("true");
                return array;
	}

	//@Override
	public String getName() {
		return "SelectionSort";
	}
        
        public void swap(ArrayList<Integer> array, int i, int j)
        {
            int temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j,temp);
         
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
            SelectionSort sort = new SelectionSort();
            sort.sort(generateArray(10000));
        }
    
}
