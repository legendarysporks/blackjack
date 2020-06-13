package main;

import java.util.ArrayList;

public class Hand {
	private ArrayList<String> cards;
	private int score;
	public Hand() {
		cards = new ArrayList<>();
		score = 0;
	}

	@Override
	public String toString(){
		String cardString = "";
		for(String card: cards){
			cardString += card + " ";
		}
		return cardString;
	}

	public String[] cards(){
		String[] cardsArray = new String[cards.size()];
		for(int i = 0; i < cards.size(); i++){
			cardsArray[i] = cards.get(i);
		}
		return cardsArray;
	}

	public int score(){
		return score;
	}

	public void addCard(String newCard){
		cards.add(newCard);
		score += parseCardScore(newCard);
	}

	static int parseCardScore(String card){
		switch(card.charAt(0)){
			case 'A':
				return 1;
			case 'K':
				return 10;
			case 'Q':
				return 10;
			case 'J':
				return 10;
			case '1':
				if(card.length() > 2){
					return 10;
				}
				else{
					return 1;
				}
			default:
				return card.charAt(0) - 48;
		}
	}


}