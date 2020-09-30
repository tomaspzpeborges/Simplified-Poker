package comp1721.cwk2;

import java.io.FileWriter;
import java.io.IOException;

// Implement PokerStats program here
public class PokerStats{


    public static void main(String[] args){

        double  cPairs=0.0, c2Pairs=0.0, c3Kind=0.0, c4Kind=0.0, cFlush=0.0, cStraight=0.0, cFullHouse =0.0;
        int i,j;
        int trials =1000;

        if( (args.length == 0) || (args.length >2)){
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }else if(args.length ==2){

            String sTrials = args[1];
            try {
                trials = Integer.parseInt(sTrials);

            }catch(NumberFormatException e){
                System.out.println("Second argument could not be formatted to a number." +
                        "Default 1000 trials will be used");
                e.printStackTrace();

            }

        }


        Deck deck = new Deck();
        deck.shuffle();
        PokerHand hand = new PokerHand();


        try {
            FileWriter myWriter = new FileWriter(args[0]);

            for(i=0; i < trials; i++){

                for(j=0; j<5;j++){

                    hand.add(deck.deal());
                }
                myWriter.write(hand + " ");

                if(hand.isPair()){
                    myWriter.write("Pair\n");
                    cPairs++;

                }else if(hand.isTwoPairs()){
                    myWriter.write("2 Pairs\n");
                    c2Pairs++;

                }else if(hand.isThreeOfAKind()){
                    myWriter.write("3 of a Kind\n");
                    c3Kind++;

                }else if(hand.isFourOfAKind()) {
                    myWriter.write("4 of a Kind\n");
                    c4Kind++;

                }else if(hand.isFlush()){
                    myWriter.write("Flush\n");
                    cFlush++;

                }else if(hand.isFullHouse()){
                    myWriter.write("Full House\n");
                    cFullHouse++;

                }else if(hand.isStraight()){
                    myWriter.write("Straight\n");
                    cStraight++;

                }else{
                    myWriter.write("\n");

                }

                hand.discardTo(deck);
                deck.shuffle();

            }
            myWriter.close();

        }catch (IOException e){
            System.out.println("Could not open file");
            e.printStackTrace();
        }



        System.out.println(trials + " hands dealt\t");
        System.out.printf("P(Pair) = %.4f%%\t\n", cPairs/trials *100);
        System.out.printf("P(2 Pairs) = %.4f%%\t\n", c2Pairs/trials*100);
        System.out.printf("P(3 of a Kind) = %.4f%%\t\n", c3Kind/trials*100);
        System.out.printf("P(Straight) = %.4f%%\t\n", cStraight/trials*100);
        System.out.printf("P(Flush) = %.4f%%\t\n", cFlush/trials*100);
        System.out.printf("P(Full House) = %.4f%%\t\n", cFullHouse/trials*100);
        System.out.printf("P(4 of a Kind) = %.4f%%\t\n", c4Kind/trials*100);


    }

}