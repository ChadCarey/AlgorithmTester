import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class InsertionSortFloat {
	 
	
	public ArrayList<Float> sort(ArrayList<Float> array) {
		for (int i = 0; i < array.size(); ++i) {
			float x = array.get(i);
			int j = i;
			while(j > 0 && array.get(j-1) > x) {
				array.set(j, array.get(j-1));
				j = j - 1;
			}
			array.set(j, x);
		}
		return array;
	}

	/**
	 * appends the test results to the specified filename
	 * @param filename
	 * @param count
	 * @param time
	 */
	private void writeCSV(String filename, int count, Long time) {
		File file = new File(filename + ".csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file, true);
				writer.append("ArraySize,Milliseconds\n");
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed to create file");
			}
		}
		
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.append(count + "," + time + '\n');
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return "InsertionSort";
	}
	
	public static void main(String[] args) {
		InsertionSortFloat sort = new InsertionSortFloat();
		sort.runTest();
	}

	private void runTest() {
		for(int size = 0; size <= 100000; size+=1000) {
			ArrayList<Float> array= generateFloatArray(size);
			Timer results = runTest(array);
			writeCSV("InsetionSortFloat", array.size(), results.getMilliseconds());
		}
	}

	private Timer runTest(ArrayList<Float> array) {
		Timer timer = new Timer();
	   // start timer
	   System.out.println("Start time: " + timer.start());
	   // run equation
	   sort(array);
	   // stop timer
	   System.out.println("Stop time: " + timer.stop());
	   // create DataPoint
	   return timer;
	}

	private ArrayList<Float> generateFloatArray(int size) {
		
		ArrayList<Float> list = new ArrayList<Float>();
		for(int i = 0; i < size; ++i) {
			list.add((float)(Math.random() * size) );
		}
		System.out.println("array size: " + list.size());
		return list;
	}

}
