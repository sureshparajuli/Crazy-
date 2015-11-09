package hwk.crazyeight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sparajul on 10/29/15.
 */
public class Card {


    private String suit;
    private String rank;
    //private final String[] suitsFirstLetter = {"C", "D", "H", "S"};
    //private final Map<String , Integer> suitsDict;
    
    public Card( String Suit, String Rank)
    {
    	this.suit = Suit;
        this.rank = Rank;
        
        // useful to quickly get first letter of card's suit
        /*suitsDict = new HashMap<String, Integer>();
        suitsDict.put("Clubs", 0);
        suitsDict.put("Diamonds", 1);
        suitsDict.put("Hearts", 2);
        suitsDict.put("Spades", 3);*/
    }




    public void setSuit(String suit)
    {
         this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString()
    {
    	return this.rank + " of " + this.suit;
    }
    
    // makes it easier to print
    public String getShortString() {
    	return this.rank + "/" + this.suit.charAt(0);
    }
    
    // change the suit of an "8" card
    public void changeEightSuit(String newSuit) {
    	if ( this.getRank().equals("8") )
    		this.setSuit(newSuit);
    }
}