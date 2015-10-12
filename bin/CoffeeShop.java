// This class instantiates two thread - CoffeeMachine and Waiter threads
// and these two threads interact with each other through wait/notify
// till you terminate the application explicitly by pressing Ctrl-C
class CoffeeShop {
		public static void main(String[] args) {
				CoffeeMachine coffeeMachine = new CoffeeMachine();
				Waiter waiter = new Waiter();
				coffeeMachine.start();
				waiter.start();
		}
}
