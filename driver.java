// driver of Blackjack project
import java.util.Scanner;

public class driver {
	public static void main(String[] args) {
		String command = new String();
		Scanner console = new Scanner(System.in);
		// Initialize both player and dealer with initial values
		bj.drawRandomCard("player");
		bj.drawRandomCard("dealer");
		bj.showTable();
		
		while (bj.gameRunning == true) {
			System.out.println("Would you like to hit or stick?");
			command = console.nextLine();
			if (command.equals("hit")) {
				bj.drawRandomCard("player");
				if (bj.getHandValue("dealer") < 17) {
					// dealer hit
					bj.drawRandomCard("dealer");
				}
			} else if (command.equals("stick")) {
				if (bj.getHandValue("dealer") < 17) {
					// dealer hit
					bj.drawRandomCard("dealer");
				}
				bj.checkGame();
				if (bj.gameRunning == false) {
					break;
				}
				bj.checkStick();
			} else if (command.equals("quit")) {
				bj.gameRunning = false;
				break;
			}
			bj.showTable();
			bj.checkGame();
		}
		
		bj.showHands();
		console.close();
	}
}
