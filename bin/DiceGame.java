import java.util.Random;

// the Gamers class just holds the name of players who roll the dice
class Gamers {
		// prevent instantiating this utility class by making constructor private
		private Gamers() {}
		public static final String JOE="Joe";
		public static final String JANE="Jane";
}

// the Dice class abastracts how the dice rolls and who plays it
class Dice {
		// to remember whose turn it is to roll the dice
		private static String turn = null;
		synchronized public static String getTurn() { return turn; }
		synchronized public static void setTurn(String player) { turn = player; }
		
		// which player starts the game
		public static void setWhoStarts(String name) { turn=name; }

		// prevent instantiating the class by making it private (we've only static members)
		private Dice() { }

		// when we roll the dice, it should give a random result
		private static Random random = new Random();
		// random.nextInt(6) gives values from 0 to 5, so add 1 to result in roll()
		public static int roll() { return random.nextInt(6)+1; }
}

// the class Player abstracts a player playing the Dice game
// each player runs as a separate thread, so Player extends Thread class
class Player extends Thread {
		private String currentPlayer = null;
		private String otherPlayer=null;

		public Player(String thisPlayer) {
				currentPlayer=thisPlayer;
				// we've only two players; we remember them in currentPlayer and otherPlayer
				otherPlayer=thisPlayer.equals(Gamers.JOE) ? Gamers.JANE:Gamers.JOE;
		}

		public void run() {
				//each player rolls the dice 6 times in the game
				for(int i=0; i < 6; i++){
						// acquire the lock before proceeding
						synchronized(Dice.class) {
								// if it is not currentPlayer's turn, then
								// wait for otherPlayer's notification
								while(!Dice.getTurn().equals(currentPlayer)){
										try {
												Dice.class.wait(1000);
												System.out.println(currentPlayer + " was waiting for " + otherPlayer);
										} catch(InterruptedException ie) {
												ie.printStackTrace();
										}
								}

								// its currentPlayer's turn now; throw the dice
								System.out.println(Dice.getTurn() + " throws " + Dice.roll());
								// set the turn to otherPlayer, and notify the otherPlayer
								Dice.setTurn(otherPlayer);
								Dice.class.notifyAll();
						}
				}
		}
}

// class DiceGame just starts the game by starting player threads
class DiceGame {
		public static void main(String[] args){
				Player p1 = new Player(Gamers.JANE);
				Player p2 = new Player(Gamers.JOE);
				// dont forget to set who starts the game
				Dice.setWhoStarts(Gamers.JOE);
				p1.start();
				p2.start();
		}
}
