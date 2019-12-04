
public abstract class StandardDeck extends Deck{


	public StandardDeck(){
		super(52);
	}

	public void setFaces(File imput) {
		//Do this shit
		//IDEA: constructor takes file and sets faces at beginning??
	}

	public Deck splitDeck(int s, int f){
		Deck sub = new Deck();
		//CHECK THAT i < f
		for(int i = s; i <= f; i++){
			sub.add(this.(deck.get(i)));
		}
		return sub;
	}

}