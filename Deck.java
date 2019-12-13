
import java.util.Random;
import java.util.ArrayList;


public class Deck{

	//CHANGED THE VARIABLES - SIZE NOT NEEDED
	protected ArrayList<Card> deck;


	//Without set initial size
	public Deck(){
		deck = new ArrayList<Card>();
	}

	//With set initial size
	public Deck(int n){
		deck = new ArrayList<Card>(n);
	}

	public void shuffle() {
		Random randomGen = new Random();

      		for (int i = deck.size() - 1; i > 0; i--) {
         		int j = randomGen.nextInt(i + 1);
         		Card tmpCard = deck.get(j);
         		deck.set(j, deck.get(i));
         		deck.set(i, tmpCard);
      		}
	}

	public void add(Card c) {
		deck.add(c);
	}

	public boolean isEmpty() {
		return deck.isEmpty();
	}

	public Card removeFirst() {
		return deck.remove(0);
	}
	
	public Card getCard(int i) {
		return deck.get(i);
	}
	
	public int getSize() {
		return deck.size();
	}

	//Compares cards and returns index of card that has highest value (ie rank)
	//FIXED: in case of =???
	public int compareCards() {
		int maxIndex = 0;
		int maxRank = (deck.get(0)).getRank();
		//System.out.println(maxRank);
		for(int i = 1; i < deck.size(); i++){
			int tempRank = (deck.get(i)).getRank();
			//System.out.println(tempRank);
			if(tempRank > maxRank) {
				maxIndex = i;
				maxRank = tempRank;
			} else if(tempRank == maxRank) {
				if(maxIndex == 0)
					return -5;
				else
					return (maxIndex *= -1);
			}
		}
		return maxIndex;
	}
	
	public Deck splitDeck(int s, int f){
		//ArrayList<Card> sub = new ArrayList<Card>();
		Deck sub = new Deck();
		//CHECK THAT i < f
		for(int i = s; i <= f; i++){
			sub.add(deck.get(i));
		}
		return sub;
	}
	
	public void clearDeck() {
		deck.clear();
	}
	
	/*public String displayDeck() {
		String visualDeck = "";
		for(int i = 0; i <deck.size(); i++) {
			visualDeck += "Player " + (i+1) ": " + 
		}
		
		return visualDeck;
	}*/

}
