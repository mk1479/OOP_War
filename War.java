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
//HAVE TO ADD IN TYPE CHECK THINGY WITH TRY-CATCH FOR USER INPUT


public abstract class War{
	protected int players;
	protected Deck deckPlayed = new Deck();
	protected StandardDeck playingDeck = new StandardDeck(); 
	//Might have to change this depending on how to initialize a new standard deck w/ file
	protected ArrayList<Player> currentPlayers = new ArrayList<Player>;
	protected ArrayList<Player> finalRank = new ArrayList<Player>;

	//Needs a constructor!
	public War(){
		//prompt for number of players and add players
		System.out.println("Welcome to the game of War! How many players will be participating this round? (2 or 4 only)");
		//take input: players = …
		if(players == 2){
			System.out.println("What is Player 1's name?")
			//String name = input 
			Player p1 = new Player(name, playingDeck.splitDeck(0,25));
			currentPlayers.add(p1);

			System.out.println("What is Player 2's name?")
			//String name = input 
			Player p2 = new Player(name, playingDeck.splitDeck(26,51));
			currentPlayers.add(p2);

		} else if(players == 4){
			System.out.println("What is Player 1's name?")
			//String name = input 
			Player p1 = new Player(name, playingDeck.splitDeck(0,12));
			currentPlayers.add(p1);

			System.out.println("What is Player 2's name?")
			//String name = input 
			Player p2 = new Player(name, playingDeck.splitDeck(13,25));
			currentPlayers.add(p2);

			System.out.println("What is Player 3's name?")
			//String name = input 
			Player p3 = new Player(name, playingDeck.splitDeck(26,38));
			currentPlayers.add(p3);

			System.out.println("What is Player 4's name?")
			//String name = input 
			Player p4 = new Player(name, playingDeck.splitDeck(39,51));
			currentPlayers.add(p4);

		}
	}

	// DONT NEED THIS
	//Adds player to player list and current player list
	//Can this prompt for player info?
	//Should this take a parameter?
	public void addPlayer(Player p){
		currentPlayers.add(p);
		allPlayers.add(p);
		
	}
	
	// Removes a player from the current player list
	// Must also set place for that player
	//Should this take a paramter?
	public void removePlayer(Player p){
		currentPlayers.remove(p);
		p.setPlace(players);
		players—;
	}

	//Checks if there's. winner of the game yet
	//Is this needed? 
	//Can we use this to check winner and print all the players w/ their rank?
	public boolean checkWinner(){
		if(players == 1)
			return True;
		else
			return False;
	}

	//Plays one card for each Player still in game
	//Compares to see which card/player wins
	//if tie (-1) -> iDeclareWar, else:
	//Adds cards to winner's pile
	//Should this be void instead of Card?
	public void playCards(){
		for(int i = 1; i <= players; i++){
			deckPlayed.add((currentPlayers.get(i)).playCard());
		}
		int rWinner = deckPlayed.compareCards();
		

		//FIX THIS SHIT - HOW DO WE KNOW WHO'S TIED??
		while(rWinner == -1)
			rWinner = iDeclareWar(deckPlayed);

		(currentPlayers.get(i)).addCards(deckPlayed);
		
		//Needs to print out all cards played and winner of round!!!

		deckPlayed.clear();
	}

	//In case of tie! (ie compareCards = -1)
	//Each player plays 2 cards and only flips over the 2nd card
	//returns the index of new winner and goes back to continue playing
	//should return an int??
	//should take in ????
	public int iDeclareWar(ArrayList<Card> c){

		
		
	}

	//This will run actual game
	public static void Main(String[] args){

	}


}
