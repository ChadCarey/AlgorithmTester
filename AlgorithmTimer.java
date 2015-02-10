import java.util.ArrayList;

public class AlgorithmTimer {
   
	private Timer timer;
	
	public AlgorithmTimer() { timer = null; };
	   
	   public Timer start(ArrayList<Integer> array, Sortable algorithm) {
		   timer = new Timer();
		   // start timer
		   System.out.println("Start time: " + timer.start());
		   // run equation
		   algorithm.sort(array);
		   // stop timer
		   System.out.println("Stop time: " + timer.stop());
		   // create DataPoint
		   return timer;
	   }
}

