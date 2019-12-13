
public class Player{

	protected String name;
	protected Deck deck;
	protected int place;

	public Player(String pName, Deck pDeck){
		name = pName;
		deck = pDeck;
	}
	
	//CHECK IF ARRAYLIST OR DECK AS PARAMETER
	public void addCards(Deck c){
		for(int i = 0; i < c.getSize(); i++)
			(this.deck).add(c.getCard(i));
	}

	public Card playCard(){
		return (this.deck).removeFirst();
	}

	public int getPlace(){
		return this.place;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public int getDSize() {
		return deck.getSize();
	}

	public void setPlace(int p){
		this.place = p;
	}
	
}
