// This class exposes a publicly accessible counter
// to help demonstrate data race problem
class Counter {
		public static long count = 0;
}

// This class implements Runnable interface
// Its run method increments the counte three times
class UseCounter implements Runnable {
		public void increment(){
				// These two statements perform read and write operations
				// on a variable that is commonly accessed by multiple thread.
				// So, acquire a lock before processing this "critical section"
				synchronized(this) {
						Counter.count++;
						System.out.print(Counter.count + " ");
				}
		}

		public void run() {
			increment();
			increment();
			increment();
		}
}

// This class creates three threads
public class DataRace {
		public static void main(String[] args){
				UseCounter c = new UseCounter();
				Thread t1 = new Thread(c);
				Thread t2 = new Thread(c);
				Thread t3 = new Thread(c);
				t1.start();
				t2.start();
				t3.start();
		}
}
