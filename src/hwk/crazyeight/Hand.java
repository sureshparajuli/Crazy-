package hwk.crazyeight;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by sparajul on 10/29/15.
 */

public class Hand
{
    private ArrayList<Card> cards;


    public Hand() {
       this.cards = new ArrayList<Card>();
    }

    public int totalCards() {
        return this.cards.size();
    }

    /*public void showHand() {
        // for each card in hand print out what that card is
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i)); // showing card in the hand
        }
    }*/

    public void  addToHand(Card addCard) {
        cards.add(addCard);
    }
    
    // remove card from specified position
    public Card  removeFromHand(int pos) {
        return cards.remove(pos);
    }

    /*public void removeACard(Card removeACard) {
        cards.remove(removeACard);
    }*/
    
    // check if there's a card matching a suit
    // --- return the position of the first card found or -1
    public int matchSuit(Card other) {
    	int i;
    	for (i=0;i<cards.size(); i++) {
    		if (cards.get(i).getSuit() == other.getSuit())
    			return i;
    	}
    	return -1;
    }
    
    // check if there's a card matching a rank
    // --- return the position of the first card found or -1
    public int matchRank(Card other) {
    	int i;
    	for (i=0;i<cards.size(); i++) {
    		if (cards.get(i).getRank() == other.getRank())
    			return i;
    	}
    	return -1;
    }
    
    // check if there's a card matching either a rank or suit
    // --- return the position of the first card found or -1
    public int matchRankOrSuit(Card other) {
    	int i;
    	for (i=0;i<cards.size(); i++) {
    		if (cards.get(i).getRank() == other.getRank() || 
    				cards.get(i).getSuit() == other.getSuit() )
    			return i;
    	}
    	return -1;
    }
    
    // check if there's an 8 card 
    // --- return the position of the 8 card found or -1
    public int findEight() {
    	int i;
    	for (i=0;i<cards.size(); i++) {
    		if ( cards.get(i).getRank().equals("8") )
    			return i;
    	}
    	return -1;
    }
    
    // line print Hand - useful for Human player only
    public void showMyHand() {
    	int i;
        for (i=0; i<cards.size(); i++) {
            System.out.print( i +":"+ cards.get(i).getShortString() + ", " ); 
        }
    }
    
    // check if majority of cards in hand are of the same suit
    // --- 
    public Object[] majoritySameSuit() {
    	Object[] res = new Object[] {false, null};
    	int i;
    	int[] cntSuits = new int[] {0,0,0,0};   //C, D, H, S
    	
    	String tmpSuit;
    	for (i=0;i<cards.size(); i++) {
    		tmpSuit = cards.get(i).getSuit();
    		switch (tmpSuit) {
	    		case "Clubs":
	    			cntSuits[0]++;
	    			break;
	    		case "Diamonds":
	    			cntSuits[1]++;
	    			break;
	    		case "Hearts":
	    			cntSuits[2]++;
	    			break;
	    		case "Spades":
	    			cntSuits[3]++;
	    			break;
    		}
    	}
    	
    	// check for a majority - same suit
    	int tmpMax = 0;
    	int maxIndex = -1;
    	int j;
    	for (j=0; j<cntSuits.length; j++) {
    		if (cntSuits[j] > tmpMax) {
    			tmpMax = cntSuits[j];
    			maxIndex = j;
    		}
    	}
    	if (tmpMax > 0) {
    		// should be more than 55% of cards
    		double percent = (double) tmpMax/cards.size();
    		if(percent > 0.55) {
    			res[0] = true;
    			res[1] = maxIndex;
    		}
    	}
    	
    	return res;
    }
}
