package main;

import java.util.ArrayList;

public class Blackjack {
	private ArrayList<Player> players;
	private Player dealer;
	private Deck deck;

	public  static void main(String[] args) {
		new Blackjack().run();
	}

	public Blackjack() {
		players = new ArrayList<>();
		for(int i = 1; i < 5; i++){
			players.add(new LocalHumanPlayer("Player" + i));
		}
		dealer = new LocalRoboPlayer("Dealer");
		deck = new Deck();
	}

	public void handleUserInput(Player player, String input) {
		// handle input from the user
	}

    public void run() {
		boolean playing = true;
		while(playing){
			playOneRound();
			if(!"y".equalsIgnoreCase(dealer.prompt("Play Again? (Y or N)"))){
				playing = false;
			}
			if(deck.size() <= 13){
				deck.reshuffle();
			}
		}
    }

     void playOneRound(){
		System.out.println("Starting Cards:");
		dealCards();
		printCardsHidden();
		runHands();
		runDealerHand();
		System.out.println("Final Cards:");
		printCards();
		for(Player p: players){
			p.clearHand();
		}
	    dealer.clearHand();
    }


    private void runHands(){
		for(Player p : players){
			if (p.hand().score() < 21) {
				boolean askForHit = true;
				while (askForHit) {
					printCardsHidden();
					if (p.wantsHit()) {
						p.hand().add(deck.deal());
						askForHit = p.hand().score() < 21;
					} else {
						askForHit = false;
					}
				}
			}
		}
    }

    private void runDealerHand(){
    	while(dealer.hand().score() < 17){
			dealer.hand().add(deck.deal());
	    }
    }

	private void dealCards(){
		for(int i = 0; i < 2; i ++){
			for(Player p: players) {
				p.deal(deck.deal());
			}
			dealer.deal(deck.deal());
		}
	}

	private void printCards(){
		System.out.println("Dealer Cards: " + dealer.hand().score() + " points, " + dealer.hand().toString());
		for(Player p: players){
			System.out.println(p.name() + ": " + p.hand().score() + " points, "
					+ p.hand().toString() + " - " + printResult(p.hand().score(), dealer.hand().score()));
		}
	}

    private void printCardsHidden(){
		System.out.println("Dealer Cards: " + dealer.hand().toStringDealer());
		for(Player p: players){
			System.out.println(p.name() + ": " + p.hand().score() + " points, " + p.hand().toString());
		}
    }

	private String printResult(int playerScore, int dealerScore){
	    if (playerScore == dealerScore || (playerScore > 21 && dealerScore > 21)) {
		    return ("Push");
	    } else if (playerScore > 21 || (dealerScore > playerScore && dealerScore <= 21)) {
		    return ("Dealer Wins");
	    } else {
		    return ("Player Wins!");
	    }
	}

}
