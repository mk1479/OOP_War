
/** ADD A COMPARISON CLASS TO COMPARE CARDS <- moved to Deck class
* ADD A LIST OF THE RANKS OF VALUES USING A MAP?? - 
* potentially easier than the try catch but maybe not necessary??
* ADD A TOSTRING METHOD TO PRINT CARD - maybe not bc all strings, use get methods
*/
import java.util.*;
import java.util.Map;

public class Card{

	protected String suit;
	protected int rank;
	protected String fvalue;
	protected String color; //Changed from char to string to keep it modular


	public Card(int val){
		rank = val;
		suit = color = fvalue = "";
	}

	public Card(String s, String val, String c){
		suit = s;
		fvalue = val;
		color = c;
		try{ rank = Integer.parseInt(val);}
		catch(Exception e) {
			//Set A to 14? since it beats all other cards
			if((val.toUpperCase()).equals("A"))
				rank = 14;
			else if((val.toUpperCase()).equals("J"))
				rank = 11;
			else if((val.toUpperCase()).equals("Q"))
				rank = 12;
			else if((val.toUpperCase()).equals("K"))
				rank = 13;
		}
		
	}

	public String getFValue() {
		return this.fvalue;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getColor() {
		return this.color;
	}

	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return fvalue + " of " + suit;
	}


}
