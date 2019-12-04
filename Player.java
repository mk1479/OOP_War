/* ADDED SETPLACE FUNCTION
* ADDED GETROUNDSWON FUNCTION
*/


public abstract class Player{

	String name;
	Deck deck;
	int place;
	int roundsWon;

	public Player(String pName, Deck pDeck){
		name = pName;
		deck = pDeck;
		roundsWon = place = 0;
	}
	
	public void addCards(Card[] c){
		for(int i = 0; i < c.length; i++)
			(this.deck).add(c[i]);
	}

	public Card playCard(){
		return (this.deck).removeFirst();
	}

	public int getPlace(){
		return this.place;
	}

	public void incRoundsWon(){
		roundsWon++;
	}

	public int getRoundsWon(){
		return this.roundsWon;
	}

	public setPlace(int p){
		this.place = p;
	}
	
}