package comp1721.cwk2;


import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;



// Implement PokerGame program here
public class PokerGame extends Application{


    @Override
    public void start(Stage primaryStage) {


        Deck deck = new Deck();
        deck.shuffle();
        PokerHand hand1 = new PokerHand();
        PokerHand hand2 = new PokerHand();
        RotateTransition rotate1;
        TranslateTransition translate1;
        ParallelTransition transition1;


        // Create a TilePane to hold the displayed cards of hand1
        TilePane tiles1 = new TilePane();
        tiles1.setPrefRows(2);
        tiles1.setPrefColumns(5);
        tiles1.setHgap(5);
        tiles1.setVgap(10);
        tiles1.setStyle("-fx-padding: 10; -fx-background-color: darkgreen;");
        Group root1 = new Group(tiles1);

        //HAND1
        for (int j = 0; j < 5; j++) {

            Card card = deck.deal();
            hand1.add(card);

            FancyCard fancy = new FancyCard(card.getRank(), card.getSuit());
            Image image = fancy.getImage();
            tiles1.getChildren().add(new ImageView(image));

            //Transitions
            rotate1 = new RotateTransition(Duration.millis(1500), tiles1.getChildren().get(j));
            rotate1.setToAngle(180);
            rotate1.play();



        }

        //HAND2
        for (int j = 0; j < 5; j++) {

            Card card = deck.deal();
            hand2.add(card);

            FancyCard fancy = new FancyCard(card.getRank(), card.getSuit());
            Image image = fancy.getImage();
            tiles1.getChildren().add(new ImageView(image));

            //Transitions
            translate1 = new TranslateTransition(Duration.millis(1500));
            translate1.setToX(0);
            translate1.setToY(100);
            rotate1 = new RotateTransition(Duration.millis(1500));
            rotate1.setToAngle(180);
            transition1 = new ParallelTransition(tiles1.getChildren().get(j+5), translate1, rotate1);
            transition1.play();

        }

        //Classifiing hands
        Label message1 = new Label(handType(hand1));
        message1.setFont( new Font(40) );
        root1.getChildren().add(message1);
        translate1 = new TranslateTransition(Duration.millis(1500),message1 );
        translate1.setToX(400);
        translate1.setToY(20);
        translate1.play();

        Label message2 = new Label(handType(hand2));
        message2.setFont( new Font(40) );
        root1.getChildren().add(message2);
        translate1 = new TranslateTransition(Duration.millis(1500),message2 );
        translate1.setToX(400);
        translate1.setToY(220);
        translate1.play();


        // Set up scene, stage it and make it visible
        Scene scene1 = new Scene(root1, 640, 500, Color.DARKGREEN);
        primaryStage.setTitle("Poker Game");
        primaryStage.setScene(scene1);
        primaryStage.show();

    }

    public static void main(String[] args) {
            launch(args);
    }


    private static String handType(PokerHand hand) {
        if (hand.isFourOfAKind()) {
            return new String("4 of a Kind");

        } else if (hand.isFullHouse()) {
            return new String("Full House");

        } else if (hand.isFlush()) {
            return new String("Flush");

        } else if (hand.isStraight()) {
            return new String("Straight");

        } else if (hand.isThreeOfAKind()) {
            return new String("3 of a Kind");

        } else if (hand.isTwoPairs()) {
            return new String("2 Pairs");

        } else if (hand.isPair()) {
            return new String("Pair");
        }else{
            return new String("1 of a Kind");
        }
    }



}


