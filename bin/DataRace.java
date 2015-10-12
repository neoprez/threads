// This class exposes a publicly accessible counter
// to help demonstrate data race problem
class Counter {
		public static long count = 0;
}

class UseCounter implements Runnable {

		// declaring the increment synchronized instead of using
		// a synchronized statement for a block of code inside the method
		public synchronized void increment(){
				Counter.count++;
				System.out.print(Counter.count + " ");
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
