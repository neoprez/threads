import java.util.concurrent.*;

class Car extends Thread {
		private CountDownLatch timer;
		public Car(CountDownLatch cdl, String name) {
				timer = cdl;
				this.setName(name);
				System.out.println(this.getName() + " ready and waiting for the countdown to start");
				start();
		}

		public void run() {
				try {
					// wait for the timer count down to reach 0
					timer.await();
				} catch(InterruptedException e) {
						System.err.println("interrupted -- can't start running the race");
				}
				System.out.println(this.getName() + " started running");
		}
}

class DragRacing {
		public static void main(String[] args) throws InterruptedException {
			
			CountDownLatch counter = new CountDownLatch(3);
			

			new Car(counter, "Toyota prius");
			new Car(counter, "Nissan murano");

			System.out.println("Starting the countdown ");
			long countVal = counter.getCount();

			while(countVal > 0) {
					Thread.sleep(1000);
					System.out.println(countVal);
					if(countVal == 1) {
							System.out.println("Start");
					}
					counter.countDown();
					countVal = counter.getCount();
			}
		}
} 
