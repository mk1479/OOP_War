/* ADDED SETPLACE FUNCTION
* ADDED GETROUNDSWON FUNCTION
*/


public abstract class Player{

	protected String name;
	protected Deck deck;
	protected int place;
	protected int roundsWon;

	public Player(String pName, Deck pDeck){
		name = pName;
		deck = pDeck;
		roundsWon = place = 0;
	}
	
	public void addCards(ArrayList<Card> c){
		for(int i = 0; i < c.size(); i++)
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

	public void setPlace(int p){
		this.place = p;
	}
	
}
