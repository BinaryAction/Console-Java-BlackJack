import java.util.Random;
public class bj {
	public static Card[] playerHand;
	public static Card[] dealerHand;
	
	public static boolean gameRunning = true;
	
	public static final String[] CardList = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	public static void checkGame() {
		if (bj.getHandValue("player") > 21) {
			// player bust
			System.out.println("Bust! You lose!");
			bj.gameRunning = false;
		} else if (bj.getHandValue("dealer") > 21) {
			// dealer bust
			System.out.println("Dealer bust! You win!");
			bj.gameRunning = false;
		} else if (bj.getHandValue("player") == 21) {
			System.out.println("You win! You hit 21.");
			bj.gameRunning = false;
		} else if (bj.getHandValue("dealer") == 21) {
			System.out.println("Dealer hit 21! You lose!");
			bj.gameRunning = false;
		}
	}
	
	public static void checkStick() {
		
		if (bj.getHandValue("player") > bj.getHandValue("dealer")) {
			System.out.println("You win! You had more than the dealer.");
		} else if (bj.getHandValue("player") < bj.getHandValue("dealer")) {
			System.out.println("You lose! You had less than the dealer.");
		} else {
			System.out.println("Tie game.");
		}
		bj.gameRunning = false;
	}
	
	public static int getHandValue(String user) {
		if (user.equals("player")) {
			int total = 0;
			if (playerHand == null) {
				return 0;
			}
			for (int i = 0; i < playerHand.length; i++) {
				total += playerHand[i].faceValue;
			}
			return total;
		} else {
			int total = 0;
			if (dealerHand == null) {
				return 0;
			}
			for (int i = 0; i < dealerHand.length; i++) {
				total += dealerHand[i].faceValue;
			}
			return total;
		}
	}
	
	public static Card newCard(String s, String user) {
		// i realized i could have just made a constructor in the Card class itself, oh well maybe in the future
		Card c = new Card();
		c.name = s;
		if (s.equals("Ace")) {
			if (user.equals("player")){
				c.faceValue = getHandValue("player") <= 21 - 11 ? 11 : 1;
			} else {
				c.faceValue = getHandValue("dealer") <= 21 - 11 ? 11 : 1;
			}
		} else if (s.equals("Jack") || s.equals("Queen") || s.equals("King")) {
			c.faceValue = 10;
		} else {
			c.faceValue = Integer.parseInt(s);
		}
		return c;
	}
	
	public static Card[] getHandByName(String name) {
		if (name.equals("player")) {
			return playerHand;
		} else {
			return dealerHand;
		}
	}
	
	public static Card drawRandomCard(String user) {
		Random r = new Random();
		String randomCardName = CardList[r.nextInt(CardList.length)];
		Card actualCard = newCard(randomCardName, user);
		int amountOfCards = getHandByName(user) == null ? 0 : getHandByName(user).length;
		Card[] newArray = new Card[amountOfCards+1]; // get amount of cards
		if (getHandByName(user) != null) {
			for(int i = 0; i < newArray.length-1; i++) {
				newArray[i] = getHandByName(user)[i];
			}
			newArray[newArray.length-1] = actualCard;
			if (getHandByName(user) == playerHand) {
				playerHand = newArray;
			} else {
				dealerHand = newArray;
			}
		} else {
			if (getHandByName(user) == playerHand) {
				playerHand = new Card[1];
				playerHand[0] = actualCard;
			} else {
				dealerHand = new Card[1];
				dealerHand[0] = actualCard;
			}
		}
		return actualCard;
	}
	public static void showTable() {
		System.out.println("Your deck: " + bj.getHandValue("player"));
		System.out.println("Dealer's deck: " + bj.getHandValue("dealer"));
	}
	
	public static void showHands() {
		System.out.println("Your deck: ");
		for (int i = 0; i < bj.playerHand.length; i++) {
			System.out.print(bj.playerHand[i].name + "(" + bj.playerHand[i].faceValue + ")");
			if (i != bj.playerHand.length-1) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
		System.out.println("Dealers deck: ");
		for (int i = 0; i < bj.dealerHand.length; i++) {
			System.out.print(bj.dealerHand[i].name + "(" + bj.dealerHand[i].faceValue + ")");
			if (i != bj.dealerHand.length-1) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
	}
}
