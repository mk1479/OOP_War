import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;


// This is the game of war
// Can play with 2 or 4 players
/*
* Process based on variables:
* arraylist of current players
* use length of current players +1 to set place as players lose
* check current player decks to see when empty
* 
* array of final ranks - add players in order that they are out
*/



public class War{
	protected int players;
	protected Deck deckPlayed = new Deck();
	protected StandardDeck playingDeck = new StandardDeck("/Users/seanzy/Desktop/deck.csv"); 
	//Might have to change this depending on how to initialize a new standard deck w/ file
	protected ArrayList<Player> currentPlayers = new ArrayList<Player>();
	protected ArrayList<Player> finalRank = new ArrayList<Player>();

	//Needs a constructor!
	public War(){
		//prompt for number of players and add players
		players = start();
		while(players != 2 && players != 4) {
			players = start();}
		
		if(players == 2){
			int i = 1;
			String pname = getPlayerName(i);
			Player p1 = new Player(pname, playingDeck.splitDeck(0,25));
			currentPlayers.add(p1);
			i++;

			pname = getPlayerName(i);
			Player p2 = new Player(pname, playingDeck.splitDeck(26,51));
			currentPlayers.add(p2);
			
			(currentPlayers.get(0)).deck.shuffle();
			(currentPlayers.get(1)).deck.shuffle();
		} else if(players == 4){
			int i = 1;
			String pname = getPlayerName(i);
			Player p1 = new Player(pname, playingDeck.splitDeck(0,12));
			currentPlayers.add(p1);
			i++;

			pname = getPlayerName(i);
			Player p2 = new Player(pname, playingDeck.splitDeck(13,25));
			currentPlayers.add(p2);
			i++;

			pname = getPlayerName(i);
			Player p3 = new Player(pname, playingDeck.splitDeck(26,38));
			currentPlayers.add(p3);
			i++;

			pname = getPlayerName(i);
			Player p4 = new Player(pname, playingDeck.splitDeck(39,51));
			currentPlayers.add(p4);
			(currentPlayers.get(0)).deck.shuffle();
			(currentPlayers.get(1)).deck.shuffle();
			(currentPlayers.get(2)).deck.shuffle();
			(currentPlayers.get(3)).deck.shuffle();
		}
	}

	// DONT NEED THIS
	//Adds player to player list and current player list
	//Can this prompt for player info?
	//Should this take a parameter?
	/*public void addPlayer(Player p){
		currentPlayers.add(p);
		allPlayers.add(p);
		*/
	
	// Removes a player from the current player list
	// Must also set place for that player
	//Should this take a parameter?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void removePlayer(Player p){
		currentPlayers.remove(p);
		p.setPlace(players);
		players--;
		finalRank.add(0,p);
	}

	//Checks if there's. winner of the game yet
	//Is this needed? 
	//Can we use this to check winner and print all the players w/ their rank?!!!!!!!!!!!!!!!!!!
	public void checkLost(){
		int maxPlayers = currentPlayers.size();
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for(int t = 0; t < maxPlayers; t++){
			if((currentPlayers.get(t)).getDSize() == 0)
				toRemove.add(t);
		}
		for(int i = 0; i <toRemove.size(); i++) {
			removePlayer(currentPlayers.get(toRemove.get(i)));
		}
	}

	//Plays one card for each Player still in game
	//Compares to see which card/player wins
	//if tie (-1) -> iDeclareWar, else:
	//Adds cards to winner's pile
	public void playCards(){
		for(int i = 0; i < currentPlayers.size(); i++){
			deckPlayed.add((currentPlayers.get(i)).playCard());
			System.out.println((currentPlayers.get(i)).getName() + ": " + (deckPlayed.getCard(i)).toString() + "\n");
		}
		
		int rWinner = deckPlayed.compareCards();
		//System.out.println(deckPlayed.getSize());
		
		//If there's a tie
		while(rWinner < 0) {
			if(rWinner == -5)
				rWinner = 0;
			rWinner = iDeclareWar(Math.abs(rWinner));
		}

		(currentPlayers.get(rWinner)).addCards(deckPlayed);
		System.out.println("Winner of this round is " + (currentPlayers.get(rWinner)).getName() + "\n");
		
		promptEnterKey();
		
		//Needs to print out all cards played and winner of round!!!
		
		deckPlayed.clearDeck();
		checkLost();
	}
	
	public void promptEnterKey(){
		for(int a = 0; a<currentPlayers.size(); a++)
			System.out.print((currentPlayers.get(a)).getName() + ": " + (currentPlayers.get(a)).getDSize() + " cards | ");
			
		int quitting;
		if(currentPlayers.size() != 2) {
			quitting = 1;}
		else {
			quitting = quit();}
		
		if(quitting == 0) {
			for(int p = 0; p < 2; p++) {
			(currentPlayers.get(0)).setPlace(players-1);
			finalRank.add(0,currentPlayers.get(0));
			currentPlayers.remove(0);
			}
		}
		else {
		   System.out.println("\nPress \"ENTER\" for next round...");
		   Scanner scanner = new Scanner(System.in);
		   scanner.nextLine();
		}
		}

	//In case of tie! (ie compareCards = -1)
	//Each player plays 2 cards and only flips over the 2nd card
	//returns the index of new winner and goes back to continue playing
	//should return an int??
	//should take in ????
	public int iDeclareWar(int c){
		System.out.println("---I DECLARE WAR---");
		ArrayList<Integer> ptp = new ArrayList<Integer>();
		
		
		//first figure out who is tied
		int maxRank = (deckPlayed.getCard(c)).getRank();
		
		ptp.add(c);
		for(int i = c+1; i < players; i++){
			int tempRank = (deckPlayed.getCard(i)).getRank();
			if(tempRank == maxRank)
				ptp.add(i);
		}
		
		//Double check if all players to play have enough cards
		for(int q = 0; q < ptp.size(); q++) {
			if((currentPlayers.get(ptp.get(q))).getDSize() < 2) {
				removePlayer((currentPlayers.get(ptp.get(q))));
				ptp.remove(q);
			}
		}
		
		if(ptp.size() <2)
			return ptp.get(0);
		
		//Play face down card
		for(int p = 0; p <ptp.size(); p++) {
			deckPlayed.add((currentPlayers.get(p)).playCard());
		}
		
		//Play new cards to compare
		Deck idwDeck = new Deck();
		for(int p = 0; p <ptp.size(); p++) {
			//deckPlayed.add((currentPlayers.get(p)).playCard());
			idwDeck.add((currentPlayers.get(ptp.get(p))).playCard());
			System.out.println((currentPlayers.get(ptp.get(p))).getName() + ": " + (idwDeck.getCard(p)).toString() + "\n");
		}
		
		//add idw cards to deckplayed
		for(int w = 0; w <idwDeck.getSize(); w++) {
			deckPlayed.add(idwDeck.getCard(w));
		}
		int rWinner = idwDeck.compareCards();
		
		//If there's a tie
		while(rWinner < 0) {
			if(rWinner == -5)
				rWinner = 0;
			rWinner = iDeclareWar(Math.abs(rWinner));
		}
		
		//returns winner of IDW
		return ptp.get(rWinner);
	}
	
	public void printFRanks(){
		System.out.println("GAME OVER! \n");
		System.out.println("   Place   |   Player Name   \n");
		System.out.println("-----------------------------");
		for(int b =0; b < finalRank.size(); b++) {
			System.out.println("     " + (finalRank.get(b).getPlace()) + "         " + (finalRank.get(b).getName()) + "\n");
		}
		System.out.println("      Congratulations!!      ");
	}

	//This will run actual game
	public static void main(String[] args){
		
		boolean playAgain = true;
		while(playAgain) {
			War game = new War();
		
			while((game.currentPlayers).size() > 1) {
				game.playCards();
			}
			
			game.printFRanks();
			
			int play = playAgainNum();
			while(play > 1) {
				play = playAgainNum();
			}
			
			if(play == 1)
				playAgain = false;
		
		}
		
		

	}

	
	static int playAgainNum() {
		int enteredNumber = 0;
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";

		do {
			try {
				System.out.print("\nDo you want to play again? (Enter 0 for yes, 1 for no) ");
				enteredString = myScanner.next();  //Read into a string
				enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
				numberError = false;  //if we haven't bailed out, then the number must be valid.
			} catch(Exception e) {
				System.out.println("Invalid response. Answer 0 for yes or 1 for no.");
				numberError = true;  //Uh-Oh...We have a problem.
			}

		} while (numberError == true );  //Keep asking the user until the correct number is entered.



		return enteredNumber;
	}
	
	static String getPlayerName(int playerNum) {
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";

		do {
			try {
				System.out.print("What is Player " + playerNum + "'s name?");
				enteredString = myScanner.next();  //Read into a string
				numberError = false;  //if we haven't bailed out, then the number must be valid.
			} catch(Exception e) {
				System.out.println("Your entry: \"" + enteredString + "\" is invalid...Please try again");
				numberError = true;  //Uh-Oh...We have a problem.
			}

		} while (numberError == true );  //Keep asking the user until the correct answer is entered.




		return enteredString;
	}
	
	
	static int start() {
		int enteredNumber = 0;
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";

		do {
			try {
				System.out.print("Welcome to the game of War! How many players will be participating this round? (2 or 4 only)");
				enteredString = myScanner.next();  //Read into a string
				enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
				numberError = false;  //if we haven't bailed out, then the number must be valid.
			} catch(Exception e) {
				System.out.println("Invalid response. Answer 0 for yes or 1 for no.");
				numberError = true;  //Uh-Oh...We have a problem.
			}

		} while (numberError == true );  //Keep asking the user until the correct number is entered.



		return enteredNumber;
	}
	
	static int quit() {
		int enteredNumber = 0;
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";

		do {
			try {
				System.out.print("\nDo you want to quit? (0 for yes or 1 for no)");
				enteredString = myScanner.next();  //Read into a string
				enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
				numberError = false;  //if we haven't bailed out, then the number must be valid.
			} catch(Exception e) {
				System.out.println("Invalid response. Answer 0 for yes or 1 for no.");
				numberError = true;  //Uh-Oh...We have a problem.
			}

		} while (numberError == true );  //Keep asking the user until the correct number is entered.



		return enteredNumber;
	}

}
