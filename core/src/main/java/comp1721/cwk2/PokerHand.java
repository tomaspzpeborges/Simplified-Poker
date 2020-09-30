package comp1721.cwk2;

// Implement PokerHand class here
public class PokerHand extends CardCollection{

    private static final int LIMIT_CARDS = 5;
    public PokerHand(){

        super();

    }


    public PokerHand(String hand) throws CardException{

        super();

        String[] splitted = hand.split("\\s+");
        if(splitted.length >5){
            throw new CardException("Too many cards for a hand");
        }
        for(String card : splitted){
            this.cards.add( new Card(card) );

        }
    }


    @Override
    public void add(Card card) throws CardException {

        for(Card inHand : this.cards){

            if(inHand.equals(card)){
                throw new CardException("Poker Hand can't have duplicates");
            }
        }
        if(this.cards.size() < LIMIT_CARDS ) {
            this.cards.add(card);
        }
        else{
            throw new CardException("Can´t add cards to a full hand");
        }
    }


    @Override
    public String toString() {

        StringBuilder sb1 = new StringBuilder();
        for(Card card: this.cards){
            sb1.append(card + " ");
        }

        return sb1.toString().trim();

    }

    public void discard(){

        if(this.cards.size() == 0){

            throw new CardException("Can´t discard from an empty hand");
        }
        super.discard();
    }

    public void discardTo(Deck deck){

        if(this.cards.size() == 0){

            throw new CardException("Can´t discard from an empty hand");
        }

        Card card;
        while( ! this.cards.isEmpty()){

             card = this.cards.remove(0);
             deck.add(card);
        }
    }

    public boolean isPair(){

        if(this.cards.size() != 5){

            return false;
        }
        if ( this.isFourOfAKind() || this.isFullHouse() || this.isThreeOfAKind() || this.isTwoPairs() )
            return false;        // The hand is not one pair (but better)

        boolean a1, a2, a3, a4;
        this.sortByRank();

        //checking a a x y z
        a1 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank());

        //checking x a a y z
        a2 = this.cards.get(1).getRank().equals(this.cards.get(2).getRank());

        //checking x y a a z
        a3 = this.cards.get(2).getRank().equals(this.cards.get(3).getRank());

        //checking x y z a a
        a4 = this.cards.get(3).getRank().equals(this.cards.get(4).getRank());



        return (a1|| a2|| a3|| a4);
    }

    public boolean isTwoPairs(){
        if(this.cards.size() != 5){

            return false;
        }
        if ( this.isFourOfAKind() || this.isFullHouse() || this.isThreeOfAKind() )
            return false;        // The hand is not 2 pairs (but better)

        boolean a1, a2, a3;
        this.sortByRank();

        //checking a a b b x
        a1 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(2).getRank().equals(this.cards.get(3).getRank());

        //checking a a x b b
        a2 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank());

        //checking  x a a b b
        a3 = this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank());

        return (a1||a2||a3);
    }

    public boolean isThreeOfAKind(){

        if(this.cards.size() != 5){

            return false;
        }

        boolean a1,a2,a3;
        this.sortByRank();

        //check for x x x a b
        a1 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(3).getRank().compareTo(this.cards.get(0).getRank()) !=0 &&
                this.cards.get(4).getRank().compareTo(this.cards.get(0).getRank()) !=0 &&
                this.cards.get(3).getRank().compareTo(this.cards.get(4).getRank()) !=0;


        //check for a x x x b
        a2 = this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(2).getRank().equals(this.cards.get(3).getRank()) &&
                this.cards.get(0).getRank().compareTo(this.cards.get(1).getRank()) !=0 &&
                this.cards.get(4).getRank().compareTo(this.cards.get(1).getRank()) !=0 &&
                this.cards.get(0).getRank().compareTo(this.cards.get(4).getRank()) !=0;

        //check for a b x x x
        a3 = this.cards.get(2).getRank().equals(this.cards.get(3).getRank()) &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank()) &&
                this.cards.get(0).getRank().compareTo(this.cards.get(2).getRank()) !=0 &&
                this.cards.get(1).getRank().compareTo(this.cards.get(2).getRank()) !=0 &&
                this.cards.get(0).getRank().compareTo(this.cards.get(1).getRank()) !=0;

        return (a1||a2 ||a3);
    }

    public boolean isFourOfAKind(){

        if(this.cards.size() != 5){

            return false;
        }

        boolean a1,a2;
        this.sortByRank();

        a1 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(2).getRank().equals(this.cards.get(3).getRank());

        a2 = this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(2).getRank().equals(this.cards.get(3).getRank()) &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank());

        return (a1|| a2);
    }

    public boolean isFullHouse(){

        if(this.cards.size() != 5){

            return false;
        }

        boolean a1,a2;
        this.sortByRank();

        //check for a a a b b
        a1 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(1).getRank().equals(this.cards.get(2).getRank()) &&
                this.cards.get(2).getRank().compareTo(this.cards.get(3).getRank()) != 0 &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank());


        //check for b b a a a
        a2 = this.cards.get(0).getRank().equals(this.cards.get(1).getRank()) &&
                this.cards.get(1).getRank().compareTo(this.cards.get(2).getRank()) != 0 &&
                this.cards.get(2).getRank().equals(this.cards.get(3).getRank()) &&
                this.cards.get(3).getRank().equals(this.cards.get(4).getRank());


        return (a1 || a2);
    }

    public boolean isFlush(){

        if(this.cards.size() != 5){

            return false;
        }
        this.sort();

        if(this.cards.get(0).getSuit().equals(this.cards.get(4).getSuit())) {
            return true;
        }else{
            return false;
        }

    }

    public boolean isStraight(){

        int i, testRank;
        if(this.cards.size() != 5){

            return false;
        }
        this.sortByRank();

        //check if hand has an Ace
        if( this.cards.get(0).getRank().equals(Card.Rank.ACE) ){

            boolean a1 = this.cards.get(1).getRank().equals(Card.Rank.TWO) &&
                    this.cards.get(2).getRank().equals(Card.Rank.THREE) &&
                    this.cards.get(3).getRank().equals(Card.Rank.FOUR) &&
                    this.cards.get(4).getRank().equals(Card.Rank.FIVE);

            boolean a2 = this.cards.get(1).getRank().equals(Card.Rank.TEN) &&
                    this.cards.get(2).getRank().equals(Card.Rank.JACK) &&
                    this.cards.get(3).getRank().equals(Card.Rank.QUEEN) &&
                    this.cards.get(4).getRank().equals(Card.Rank.KING);



           return ( a1 || a2 );
        }else{

            //general case

            testRank = this.cards.get(0).getRank().ordinal() +1;

            for( i =1; i < this.cards.size(); i++){

                if(this.cards.get(i).getRank().ordinal() != testRank){
                    return false; //straight failed
                }
                testRank++;
            }
            return true;
        }
    }

    public void sortByRank(){
        int i, j, min_j;

        for(i=0; i < this.cards.size(); i++){

            min_j = i; //assume elem i (this.cards.get(i)is the minimum
            for(j=i+1; j< this.cards.size(); j++) {

                if (this.cards.get(j).getRank().compareTo(this.cards.get(min_j).getRank()) < 0) { //this compareTo works based on the order the enums are created

                    min_j = j; //we found a smaller rank value, update min_j
                }
            }
                //Swap a[i] and a[min_j]
                Card aux = this.cards.get(i);
                this.cards.set(i,this.cards.get(min_j));
                this.cards.set(min_j, aux);


        }

    }


}