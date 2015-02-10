public class Timer {
   private Long startTime;
   private Long stopTime;

   public Long start() {
      startTime = System.nanoTime();
      return startTime;
   }

   public Long stop() {
      stopTime = System.nanoTime();
      return stopTime;
   }
   
   public Long getNanoseconds() {
	   return stopTime - startTime;
   }

   public Long getMilliseconds() {
      return getNanoseconds()/1000000;
   }

   public Long getSeconds() {
      return getMilliseconds() / 1000;
   }

   public Long getMinutes() {
      return getSeconds() / 60;
   }

   public Long getHours() {
      return getMinutes() / 60;
   }

   public Long getDays() {
      return getHours() / 24;
   }
}