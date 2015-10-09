class TimeBomb extends Thread {
		String[] timeStr = { "Zero", "One", "Two", "Three", "Four", "Five",
												 "Six", "Seven", "Eight", "Nine" };

		public void run(){
				for(int i = 9; i >= 0; i--){
						try{
								System.out.println(timeStr[i]);
								Thread.sleep(1000);
						}catch(InterruptedException ex){
							ex.printStackTrace();		
						}
				}
		}

		public static void main(String[] args){
				System.out.println("Starting 10 second count down...");
				TimeBomb timer = new TimeBomb();
				timer.start();
				try {
						//Main thread waits for the timer thread to die
						timer.join();
				} catch(InterruptedException e){
						e.printStackTrace();
				}
				System.out.println("Booom!!!");
		}
}
