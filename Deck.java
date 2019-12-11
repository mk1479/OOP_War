import java.util.Random;
import java.util.ArrayList;


public abstract class Deck{

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

      		for (int i = deck.length - 1; i > 0; i--) {
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
	
	public int getSize() {
		return deck.size();
	}

	//Compares cards and returns index of card that has highest value (ie rank)
	//FIXED: in case of =???
	public int compareCards() {
		int maxIndex = 0;
		int maxRank = 0;
		for(int i = 0; i < deck.length; i++){
			int tempRank = (deck.get(i)).getRank();
			if(tempRank > maxRank) {
				maxIndex = i;
				maxRank = tempRank;
			} else if(tempRank = maxRank)
				maxIndex = -1;
		}
		return maxIndex;
	}

}
