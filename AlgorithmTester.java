import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AlgorithmTester {
	
	AlgorithmTimer timer;
	ArrayList<Sortable> algorithms;
	boolean[] selectedAlgorithms;
	int start;
	int stop;
	int increment;
	
	public AlgorithmTester() {
		timer = new AlgorithmTimer();
		algorithms = new ArrayList<Sortable>();
		
		algorithms.add(new BubbleSort());
		algorithms.add(new InsertionSort());
		algorithms.add(new SelectionSort());
		algorithms.add(new HeapSort());
		algorithms.add(new QuickSort());
		
		selectedAlgorithms = new boolean[algorithms.size()];
		for(int i = 0; i < selectedAlgorithms.length; ++i) {
			selectedAlgorithms[i] = false;
		}
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
	
	/**
	 * generates an array based on the given size
	 * @param size
	 * @return
	 */
	private ArrayList<Integer> generateArray(int size) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < size; ++i) {
			list.add( (int)(Math.random() * size) );
		}
		System.out.println("array size: " + list.size());
		return list;
	}
	
	/**
	 * prints the array
	 * @param list
	 */
	private void printArray(ArrayList<Integer> list) {
		System.out.println("\n ---------- \n");
		for(int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i));
		}
		System.out.println("\n ---------- \n");
	}

	/**
	 * gets user input as a number
	 */
	private int getNumber() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      String number = null;
	      try {
	         number = br.readLine();
	         if(number.isEmpty()) {
	        	 return -1;
	         }
	         return Integer.parseInt(number);
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read integer");
	         return 1;
	      }
	}
	
	/**
	 * this defines the test parameters with a starting array size, 
	 * ending array size, and how fast the size grows for each sequential test
	 */
	private void getRunValues() {
		do {
			System.out.print("Enter start value: ");
			start = getNumber();
			System.out.print("Enter stop value: ");
			stop = getNumber();
			System.out.print("Enter increment value: ");
			increment = getNumber();
			System.out.println(start + " " + stop + " " + increment);
		} while(start > stop ||  increment >= stop-start && stop != start);
	}
	
	/**
	 * prepares and starts a series of algorithm tests
	 */
	public void run() {
		// choose equations to test, add them to a list
		chooseAlgorithms();
		// get array size range
		getRunValues();
		// run each equation with a COPY of the same array
		startTest();
		System.out.println("Done!");
	}
	
	/**
	 * starts the algorithm test
	 * @param algorithms
	 */
	private void startTest() {
		for(int i = start; i <= stop; i += increment ) {
			ArrayList<Integer> array = generateArray(i);
			for(int j = 0; j < algorithms.size(); j++) {
				if(selectedAlgorithms[j]) {
					ArrayList<Integer> arrayCopy = new ArrayList<Integer>(array);
					System.out.println("copied array size: " + arrayCopy.size());
					Sortable alg = algorithms.get(j);
					Timer results = timer.start( arrayCopy, alg );
					// print results
					if(isSorted(arrayCopy)) {
						System.out.println("Success!");
						System.out.println("\tAlgorithm name: " + alg.getName() + 
								" \n\tArray size: " + arrayCopy.size() +
								" \n\tMilliseconds: " + results.getMilliseconds() + '\n');
						// write results to CSV
						writeCSV(alg.getName(), arrayCopy.size(), results.getNanoseconds());
					}
					else {
						printArray(arrayCopy);
						System.out.println(alg.getName() + " failed to sort the array, size" + arrayCopy.size());
						//writeCSV(alg.getName(), arrayCopy.size(), results.getMilliseconds());
						System.exit(1);
					}
					
				}
			}
		}
	}

	/**
	 * returns true if the array is sorted
	 * @param array
	 * @return
	 */
	private boolean isSorted(ArrayList<Integer> array) {
		for(int i = 0; i < array.size()-1; ++i)
			if(array.get(i) > array.get(i+1)) {
				System.out.println( "\tInvalid, array[i]: " + array.get(i) 
						+ "\n\tarray[i+1]: " + array.get(i+1) 
						+ "\n\tindex: " + i);
				return false;
			}
		
		return true;
			
	}

	/**
	 * will return an ArrayList of the algorithms we will be testing with
	 * @return
	 */
	private void chooseAlgorithms() {
		int input;
		do {
			displayAlgorithmChoices();
			System.out.println("Enter a vaule to toggle choices (negative values start program): ");
			input = getNumber();
			if(input <= selectedAlgorithms.length && input >= 1) {
				selectedAlgorithms[input-1] = !selectedAlgorithms[input-1]; 
			}
		} while(input >= 0);
	}
	
	private void displayAlgorithmChoices() {
		for(int i = 1; i <= algorithms.size(); ++i) {
			System.out.print(i + ". " + algorithms.get(i-1).getName());
			if(selectedAlgorithms[i-1]) {
				System.out.println(" Yes");
			} else {
				System.out.println(" No");
			}
		}
	}

	/**
	 * Main just gets the program started
	 * @param args
	 */
	public static void main(String[] args) {
		AlgorithmTester tester = new AlgorithmTester();
		tester.run();
	}
}
