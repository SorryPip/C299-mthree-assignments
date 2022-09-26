import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	static int wins = 0;
	static int draws = 0;
	static int losses = 0;
	static int maxTurns;
	static Integer cpu;
	static Integer user;
	
	static HashMap<Integer, String> map = new HashMap<Integer, String>();

	public static void main(String[] args) {
		
		map.put(1, "Rock");
		map.put(2, "Paper");
		map.put(3, "Scissors");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("how many games do you want to play, 0-10: ");
		try {
			maxTurns = Integer.parseInt(scanner.nextLine());
			
		}
		catch (Exception e){
			System.out.println("Game Over");
		} 
		
		
		if ((0 < maxTurns) && (maxTurns <= 10)) {
			 System.out.println("play " +maxTurns+ " rounds");
			 
			 // has played turn amount of turns
			 for(int turn = 1; turn < maxTurns+1; turn++) {
				 System.out.println("\nRound "+turn);
				 System.out.println("Please, enter your move (1 = Rock, 2 = Paper, 3 = Scissors)");
				 user = Integer.parseInt(scanner.nextLine());
				 cpu = ThreadLocalRandom.current().nextInt(1, 4);
				 System.out.print("You chose "  +map.get(user) 
				 + ", CPU chose " + map.get(cpu));
				 
				 if(user.equals(cpu)) {
					 System.out.println(": Draw");
					 draws++;
				 } else if((user == 1 && cpu == 2) || (user == 2 && cpu == 3)
						 || (user == 3 && cpu == 1)) {
					 System.out.println(": Win");
					 wins++;
				 } else {
					 System.out.println(": Lose");
					 losses++;
				 }
			 }
			 
			 scanner.close();
			 
			 System.out.println("\n\nTotal Wins: "+wins
					 + "\nTotal Losses: " + losses
					 + "\nTotal Draws: "+ draws);
			 
			 if(wins > losses) {
				 System.out.println("You Win!");
			 }else if (wins == losses) {
				 System.out.println("We Draw");
			 }else {
				 System.out.println("Haha you lose");
			 }
		
		 }
	}
	

}
