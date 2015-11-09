package hwk.crazyeight;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

    	///Suresh: line below Not needed
        // int players = 2;

    	///Suresh: line below for printing all cards in deck
    	// Player h = new Player();
        
        Deck currentDeck = new Deck();
        currentDeck.makeDeck();
        
        ///Suresh: line below for printing all cards in deck
        // d.printDeck();

        
        // declare and initialize game variables
        boolean GameOver;
        boolean keepPlaying = true;   //user doesn't want to stop playing
        boolean isHumanDealer;
        
        Hand hComp = new Hand();
        Hand hHuman = new Hand();
        ArrayList<Card> undealtCards;
        ArrayList<Card> discardPile = new ArrayList<Card>();
        Card[] topOfDiscardPile = new Card[]{null};
        boolean isHumanTurn;
        String currentSuit;
        
        
        while(keepPlaying) {
        	System.out.println("********************************************************");
    		System.out.println("Note: C=Clubs, D=Diamonds, H=Hearts, S=Spades");
        	// select dealer at random - even is human, odd is computer
        	//int rand_num = (int) (100*Math.random());
        	Random rand = new Random();
    		int rand_num = rand.nextInt(100) + 1;
        	if (rand_num % 2 == 0) {
        		isHumanDealer = true;
        		System.out.println("You are the dealer.");
        	} else {
        		isHumanDealer = false;
        		System.out.println("The Computer is the dealer.");
        	}
        	///System.out.println( rand );
        	///System.out.println( isHumanDealer );
        	
        	// deal 7 cards each 
        	dealFromDeck(currentDeck, isHumanDealer, hHuman, hComp);
        	
        	//take one card from deck and place face-up (print), 
        	topOfDiscardPile[0] = currentDeck.drawACard();
        	discardPile.add( topOfDiscardPile[0] );
        	// TODO --- for eigth
        	///currentSuit = topOfDiscardPile.getSuit();
        	// TODO ??? 
        	//int currentWinner = -1;  // 1-human, 0-computer
        	
        	// place the undealt deck face-down (no print)
        	undealtCards = currentDeck.getCardsFromDeck();
        	
        	//initialize the GameOver and isHumanTurn variables
        	GameOver = false;
        	if (isHumanDealer)
        		isHumanTurn = false;  // // Comp's turn - you can't deal and play first
        	else isHumanTurn = true;  // your turn
        		
        	
        	// game-loop: continuous game play
        	while (!GameOver) {
        		// print the current game state
        		printGameState(topOfDiscardPile[0], hHuman);
        		
        		turnToPlay(isHumanTurn, topOfDiscardPile, hHuman, hComp,
        				undealtCards, discardPile );
        		
        		// TODO --- update variables
        		// GameOver
        		isHumanTurn = !isHumanTurn;
        		if (hHuman.totalCards() == 0 || hComp.totalCards() == 0 )
        			GameOver = true;
        	}
        	 
        	 //does user want to quit?
        	 ///if (GameOver) {
        		 //prompt user to keep playing, 
        		 //exit this loop if user does not want to play anymore
        		 //TODO
        	 ///}
        }
        
        		 
        	
        




    }

    //
	private static void turnToPlay(boolean isHumanTurn, Card[] topOfDiscardPile,
			Hand hHuman, Hand hComp, 
			ArrayList<Card> undealtCards, ArrayList<Card> discardPile) {
		Card tmp; // card that the player lays down
		if (isHumanTurn) {
			System.out.println("...Enter from 0 to "+ (hHuman.totalCards()-1) 
					+" to select card to play.");
			tmp = playFromHumanHand(hHuman);			
		} else {
			// --- computer's turn
			System.out.println("...Computer's turn to play");
			 // sleep for 3 seconds???
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			tmp = playFromCompHand(topOfDiscardPile[0], hComp);
		}
		discardPile.add( tmp ); // add to the discard pile
		topOfDiscardPile[0] = tmp; // update the "topOfDiscardPile" variable
		
	}

	private static Card playFromCompHand(Card topOfDiscardPile, Hand hComp) {
		/*int pos = hComp.matchRankOrSuit(topOfDiscardPile);
		if (pos != -1)
			return hComp.removeFromHand(pos);
		else {
			if (hComp.findEight() != -1)
				// use the "8" card
				;
			else
				// draw from undealt cards
				;
		}
		return null;*/
		
		//for now, randomly select from computer's hand
		Random rand = new Random();
		int pos = rand.nextInt(hComp.totalCards());
		return hComp.removeFromHand(pos);
	}

	private static Card playFromHumanHand(Hand hHuman) {
		// TODO --- scanner for input
		Scanner sc = new Scanner(System.in);
		int pos = sc.nextInt();
		
		// use card selected by human
		return hHuman.removeFromHand(pos);
	}

	private static void printGameState(Card topOfDiscardPile, Hand hHuman) {
		// TODO 
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("\t"); // indent(tab) before printing hand
		hHuman.showMyHand();
		System.out.println("\n\tcard on top: "+ topOfDiscardPile.getShortString());		
	}

	private static void dealFromDeck(Deck currentDeck, boolean isHumanDealer,
			Hand hHuman, Hand hComp) {
		// TODO --- Only computer should randomly choose 1 card at a time vs 3,3,1 vs 2,2,2,1
		int rand_three;
		
		//random gen for comp
		Random rand = new Random();
		rand_three = rand.nextInt(4);
		
		//TODO - prompt to enter 1,2, or 3 for deal sequence
		//if (isHumanDealer) {
			//;
		//}
		
		int cnt = 0;
		switch (rand_three) {
			case 1:
				//deal the cards one at a time - 7 to each player
				System.out.println("\tdealing cards one at a time...");	
				while (currentDeck.getDeckSize() > 0 && cnt < 7) {
					if (isHumanDealer) {
						hComp.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() );
					} else {
						hHuman.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() );
					}
					cnt++;
				}
				break;
			case 2:
				//deal the cards in the manner 3,3,1 at a time - 7 to each player
				System.out.println("\tdealing cards using 3,3,1 method...");
				while (currentDeck.getDeckSize() > 0 && cnt < 6) {
					if (isHumanDealer) {
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() ); //
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() ); //
					} else {
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() ); //
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() ); //
					}
					cnt +=3;
				}
				if (isHumanDealer) {
					hComp.addToHand( currentDeck.drawACard() );
					hHuman.addToHand( currentDeck.drawACard() );
				} else {
					hHuman.addToHand( currentDeck.drawACard() );
					hComp.addToHand( currentDeck.drawACard() );
				}
				break;
			case 3:
				//deal the cards in the manner 2,2,2,1 at a time - 7 to each player
				System.out.println("\tdealing cards using 2,2,2,1 method...");
				while (currentDeck.getDeckSize() > 0 && cnt < 6) {
					if (isHumanDealer) {
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() ); //
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() ); //
					} else {
						hHuman.addToHand( currentDeck.drawACard() );
						hHuman.addToHand( currentDeck.drawACard() ); //
						hComp.addToHand( currentDeck.drawACard() );
						hComp.addToHand( currentDeck.drawACard() ); //
					}
					cnt +=2;
				}
				if (isHumanDealer) {
					hComp.addToHand( currentDeck.drawACard() );
					hHuman.addToHand( currentDeck.drawACard() );
				} else {
					hHuman.addToHand( currentDeck.drawACard() );
					hComp.addToHand( currentDeck.drawACard() );
				}
				break;
		}
	}
}
