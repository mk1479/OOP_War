
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class StandardDeck extends Deck{


	public StandardDeck(String input){
		super(52);
		File import_file = new File(input);

		Scanner file_scanner = null;
		try {
			file_scanner = new Scanner(import_file);
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file: " + input);
		}
		
		while(file_scanner.hasNextLine()) {
			String[] row = file_scanner.nextLine().split(",");
			if(row.length < 3) {
				continue;
			}
			String face_value = row[0];
			String suit_value = row[1];
			String color = row[2];
			
			Card card = new Card(suit_value, face_value, color);
			this.deck.add(card);
		}
		
		file_scanner.close();
	}

	public void setFaces(File input) {
		//placehlder
	}


}
