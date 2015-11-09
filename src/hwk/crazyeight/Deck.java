
package hwk.crazyeight;


import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by sparajul on 10/29/15.
 */
public class Deck {

    private final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack","Queen", "King"};
    private ArrayList<Card> deck = new ArrayList<Card>();

    public void makeDeck()
    {
        for (String Ranks : ranks)
        {
            // Tony: lines a) & b) below was cause of wrong things being printed
        	///a) System.out.println(Ranks);
            for ( String Suits : suits)
            {
                ///b) System.out.println(Suits);
                Card card = new Card(Suits, Ranks);
                this.deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }


    public Card drawACard() {
        return deck.remove(0);
    }
    
    // return deck size
    public int getDeckSize() {
        return deck.size();
    }

    // print out all cards currently in the deck
    public void printDeck() {
        for (Card aCard : deck)
            System.out.println(aCard);
         
    }
    
    // return deck of cards
    public ArrayList<Card> getCardsFromDeck() {
    	return deck;
    }


}
