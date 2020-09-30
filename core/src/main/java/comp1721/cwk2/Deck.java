package comp1721.cwk2;

import java.util.Collections;

// Implement Deck class here
public class Deck extends CardCollection{


    public Deck(){

       super();
        for (Card.Suit suit: Card.Suit.values()) {

            for (Card.Rank rank: Card.Rank.values()) {

                this.add(new Card(rank,suit));
            }
        }
        this.sort();
    }


    public Card deal() throws CardException{

        if(this.cards.size() == 0){
            throw new CardException("Can't deal from an empty deck");
        }

        return this.cards.remove(0);
    }

    public void shuffle(){

        Collections.shuffle(this.cards);
    }


}