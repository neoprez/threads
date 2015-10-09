public class AsyncThread extends Thread{
		public void run() {
				System.out.println("Starting the thread " + getName());
				for(int i = 0; i < 3; i++) {
						System.out.println("In thread " + getName() + "; iteration " + i);
						try {
								// Sleep for sometime before the next iteration
								Thread.sleep(10);
						} catch(InterruptedException e){
							e.printStackTrace();
						}
				}
		}

		public static void main(String[] args){
				AsyncThread async1 = new AsyncThread();
				AsyncThread async2 = new AsyncThread();
				async1.start();
				async2.start();
		}
}
