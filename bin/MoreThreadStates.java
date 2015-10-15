// This Thread class just invokes sleep method after acquiring lock on its class object
class SleepyThread extends Thread {
		public void run() {
				synchronized(SleepyThread.class){
						try {
								Thread.sleep(1000);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
				}
		}
}

// The class creates two threads to show how to these thread will enter into
// TIMED_WAITING and BLOCKED states
class MoreThreadStates {
		public static void main(String[] args) {
				Thread t1 = new SleepyThread();
				Thread t2 = new SleepyThread();

				t1.start();
				t2.start();
				
				System.out.println(t1.getName() + ": I'm in state " + t1.getState());
				System.out.println(t2.getName() + ": I'm in state " + t2.getState());
		}
}
