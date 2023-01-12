package testgui;
import guijavafx.Card;
import guijavafx.Deck;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/**
 * This program can deal 3 cards selected at random from a Deck.
 * It depends on the files Deck.java, Card.java, and cards.png.
 * There is a button that the user can click to redraw the
 * image using new random cards or a button to quit the game.
 */

public class FirstThreeCardGame extends Application {
	
private Canvas canvas;  // The canvas on which the strings are drawn.
    
    private Image cardImages;  // Contains images of all of the cards.
                               // Cards are arranged in 5 rows and 13 columns.
                               // Each of the first four rows contains the cards
                               // from one suit, in numerical order.  The first
                               // four rows contain clubs, diamonds, hearts, and
                               // spades in that order.  The fifth row contains
                               // two jokers and a face-down card.
    

    public static void main(String[] args) {
        launch(args);
    }
    

    public void start( Stage stage ) {
        
        cardImages = new Image("resources/cards.png"); //get the source image.
        
        
        //79*3 = because there would be 3 cards to be drawn and each card is 79 pixels wide.
        //79*3 + 80 = because cards should be 20 pixels from each other (3*20) but the other 20 is for space in the last card on far right. 
        //123 + 40 = because cards should be 20 pixels away from the top and 20 pixels away from the bottom.
        //The 123 is the height of the card.
        canvas = new Canvas(3*79 + 80, 123 + 40); 
        draw();  // draw content of canvas the first time.
        

        Button redraw = new Button("Deal Again!");
        redraw.setStyle("-fx-font: bold 10pt monospace");
        redraw.setOnAction( e -> draw() );
        
        Button quitButton = new Button("Quit");
		quitButton.setOnAction( e -> Platform.exit() );
		
		HBox buttonBar = new HBox( 20, redraw, quitButton );
		buttonBar.setAlignment(Pos.CENTER);
		buttonBar.setStyle("-fx-background-color: yellow; -fx-padding:6px;" + 
                " -fx-border-color:white; -fx-border-width: 2px 0 0 0;");
        
        BorderPane root = new BorderPane(canvas);
        root.setBottom(buttonBar);
        root.setStyle("-fx-border-color:white; -fx-border-width: 2px; -fx-background-color: black");
        
        stage.setScene( new Scene(root, Color.WHITE) );
        stage.setTitle("First Three Cards");
        stage.setResizable(false);
        stage.show();

    }
    

    /**
     * The draw() method is responsible for drawing the content of the canvas.
     * It draws 3 cards in a row.  The first card has top left corner at (20,20),
     * and there is a 20 pixel gap between each card and the next.
     */
    private void draw() {
        
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        Deck deck = new Deck();
        deck.shuffle();
        
        double sx,sy;  // top left corner of source rect for card in cardImages
        double dx,dy;  // top left corner of destination rect for card in the canvas
        double sw,sh; //the source (resource image) width and height.
        double dw, dh; //the destination (canvas) width and height.
        
        for (int i = 0; i < 3; i++) {
            Card card = deck.dealCard();
            System.out.println(card); // check the console to make sure the cards appear the right way. you can remove this if you want.
            sw = 79;
            sh = 123;
            dw = 79;
            dh = 123;
            sx = 79 * (card.getValue()-1); //you have to adjust the value a bit so the card appears the right way.
            sy = 123 * (3 - card.getSuit()); //you have to adjust the value a bit so the card appears the right way.
            dx = 20 + (79+20) * i; //make sure the pixels for x are allocated for the space pixel(20) and the card pixel(79)
            dy = 20; //make sure the pixels are allocated for pixels in the y coordinate.
            g.drawImage( cardImages, sx,sy,sw,sh, dx,dy,dw,dh );
            
        } //end of for
        
    } // end draw()



} //end of class FirstThreeCardGame
