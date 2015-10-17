// This class has run method which waits forever since there is no other thread to notify it
class InfiniteWaitThread extends Thread {
		static boolean okayToRun=false;
		synchronized public void run() {
				while(!okayToRun) {
						try {
								// note the call to wait without any timeout value
								// so it waits forever for some thread to notify it
								wait();
						} catch(InterruptedException ie) {
								ie.printStackTrace();
						}
				}
		}
}

class WaitingThreadState {
		public static void main(String[] args){
				Thread t=new InfiniteWaitThread();
				t.start();
				System.out.println(t.getName() + ": I'm in state " + t.getState());
		}
}
