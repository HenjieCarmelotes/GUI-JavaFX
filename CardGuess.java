package guijavafx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * This class implements a simple card game.  The user sees a card 
 * and tries to predict whether the next card will be higher or 
 * lower.  Aces are the lowest-valued cards.  If the user makes
 * three correct predictions, the user wins.  If not, the
 * user loses.
 * 
 * This class depends on several additional source code files:
 * Card.java, Hand.jave, Deck.java, and cards.png. 
 * 
 */


public class CardGuess extends Application {
	
	 public static void main(String[] args) {
	        launch(args);
	    }

	    //---------------------------------------------------------------------
	 
	 private Canvas board;     // The canvas on which cards and message are drawn.
	    private Image cardImages; // A single image contains the images of every card.

	    private Deck deck;      // A deck of cards to be used in the game.
	    private Hand hand;      // The cards that have been dealt so far.
	    private String message; // A message drawn on the canvas, which changes
	                            //    to reflect the state of the game.

	    private boolean gameInProgress = false;  // Set to true when a game begins and to false
	                                     //   when the game ends.

	    
	    /**
	     * The start() method sets up the GUI and event handling. The root pane is
	     * is a BorderPane. A canvas where cards are displayed occupies the center 
	     * position of the BorderPane.  On the bottom is an HBox that holds three buttons.  
	     * ActionEvent handlers are set up to call methods defined elsewhere
	     * in this class when the user clicks a button.
	     */
	    
	  
	    public void start(Stage stage) {

	        cardImages = new Image("resources/cards.png");

	        board = new Canvas(4*99 + 20, 123 + 80); 
	                         // space for 4 cards, with 20-pixel border
	                         // and space on the bottom for a message
	        
	       
	        
	        Button higher = new Button("Higher");
	        higher.setStyle("-fx-background-color:#00FFFF; -fx-border-width: 2px");
	        higher.setOnAction( e -> doHigher() );
	        
	        
	        Button lower = new Button("Lower");
	        lower.setStyle("-fx-background-color:#87CEEB; -fx-border-width: 2px");
	        lower.setOnAction( e -> doLower() );
	        
	        Button newGame = new Button("New Game");
	        newGame.setTextFill(Color.GREEN);
	        newGame.setOnAction( e -> doNewGame() );
	        
	        Button endGame = new Button("End Game");
	        endGame.setTextFill(Color.RED);
	        endGame.setOnAction( e -> Platform.exit() );
	        
	        

	        HBox buttonBar = new HBox( higher, lower, newGame, endGame );
	        
	        /* Improve the layout of the buttonBar. Without adjustment
	         * the buttons will be grouped at the left end of the
	         * HBox.  My solution is to set their preferred widths
	         * to make them all the same size and make them fill the
	         * entire HBox.  */
	        
	        higher.setPrefWidth(board.getWidth()/4.0);
	        lower.setPrefWidth(board.getWidth()/4.0);
	        newGame.setPrefWidth(board.getWidth()/4.0);
	        endGame.setPrefWidth(board.getWidth()/4.0);
	        
//	        buttonBar.setAlignment(Pos.CENTER);  // Alternative layout adjustment:
	                                             // group the buttons in the
	                                             // center of the HBox.

//	        higher.setMaxWidth(1000);  // Alternative layout adjustment:
//	        lower.setMaxWidth(1000);   // increase the max size of the buttons
//	        newGame.setMaxWidth(1000); // tell the HBox to let them grow.
//	        HBox.setHgrow(higher, Priority.ALWAYS);  // In this case, the buttons
//	        HBox.setHgrow(lower, Priority.ALWAYS);   // fill the HBox, but they are
//	        HBox.setHgrow(newGame, Priority.ALWAYS); // not the same size.
	        
	        BorderPane root = new BorderPane();
	        root.setCenter(board);
	        root.setBottom(buttonBar);

	        doNewGame();  // set up for the first game

	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("Card Guess Game");
	        stage.setResizable(false);
	        stage.show();
	        
	    }  // end start()


	    /**
	     * Called by an event handler when user clicks "Higher" button.
	     * Check the user's prediction.  Game ends if user guessed
	     * wrong or if the user has made three correct predictions.
	     */
	    private void doHigher() {
	        if (gameInProgress == false) {
	                // If the game has ended, it was an error to click "Higher",
	                // So set up an error message and abort processing.
	            message = "Click \"New Game\" to start a new game!";
	            drawBoard();
	            return;
	        }
	        hand.addCard( deck.dealCard() );     // Deal a card to the hand.
	        int cardCt = hand.getCardCount();
	        Card thisCard = hand.getCard( cardCt - 1 );  // Card just dealt.
	        Card prevCard = hand.getCard( cardCt - 2 );  // The previous card.
	        
	        if ( thisCard.getValue() < prevCard.getValue() ) {
	            gameInProgress = false;
	            message = "Too bad! You lose.";
	        }
	        else if ( thisCard.getValue() == prevCard.getValue() ) {
	            gameInProgress = false;
	            message = "Too bad!  You lose on ties.";
	        }
	        else if ( cardCt == 4) {
	            gameInProgress = false;
	            message = "You win!  You made three correct guesses.";
	        }
	        else {
	            message = "Got it right!  Try for " + cardCt + ".";
	        }
	        drawBoard();
	    } // end doHigher()


	    /**
	     * Called by an event handler when user clicks "Lower" button.
	     * Check the user's prediction.  Game ends if user guessed
	     * wrong or if the user has made three correct predictions.
	     */
	    private void doLower() {
	        if (gameInProgress == false) {
	                // If the game has ended, it was an error to click "Lower",
	                // So set up an error message and abort processing.
	            message = "Click \"New Game\" to start a new game!";
	            drawBoard();
	            return;
	        }
	        hand.addCard( deck.dealCard() );     // Deal a card to the hand.
	        int cardCt = hand.getCardCount();
	        Card thisCard = hand.getCard( cardCt - 1 );  // Card just dealt. (For the first part, subtract 1 since the position should be 1 and the first card has 
	        											//been dealt by the system already during the first load of the game. thisCard = 1; position 1.)
	        Card prevCard = hand.getCard( cardCt - 2 );  // The previous card. (subtract 2 since the position for the first card is 0ith.)
	        
	        if ( thisCard.getValue() > prevCard.getValue() ) { //check the dealt card of the user if it is greater than the one shown by the computer.
	            gameInProgress = false; //since the dealt card is greater, the user lost the game.
	            message = "Too bad! You lose.";
	        }
	        
	        else if ( thisCard.getValue() == prevCard.getValue() ) { //both cards are equal in value, neither high nor low. The computer wins.
	            gameInProgress = false;
	            message = "Too bad!  You lose on ties.";
	        }
	        else if ( cardCt == 4) { //if the cardCt which is the handsize is equal to 4 (3 excluding the first shown by the system), the user wins. 
	            gameInProgress = false;
	            message = "You win!  You made three correct guesses.";
	        }
	        else {
	            message = "Got it right!  Try for " + cardCt + "."; //let the user click again to continue the game.
	        }
	        drawBoard();
	    } // end doLower()


	    /**
	     * Called by the start() method, and called by an event handler if
	     * the user clicks the "New Game" button.  Start a new game.
	     */
	    private void doNewGame() {
	        if (gameInProgress) {
	                // If the current game is not over, it is an error to try
	                // to start a new game.
	            message = "You still have to finish this game!";
	            drawBoard();
	            return;
	        }
	        deck = new Deck();   // Create the deck and hand to use for this game.
	        hand = new Hand();
	        deck.shuffle();
	        hand.addCard( deck.dealCard() );  // Deal the first card into the hand.
	        message = "Is the next card higher or lower?";
	        gameInProgress = true;
	        drawBoard();
	    } // end doNewGame()


	    /**
	     * This method draws the message at the bottom of the
	     * canvas, and it draws all of the dealt cards spread out
	     * across the canvas.  If the game is in progress, an extra
	     * card is drawn face down representing the card to be dealt next.
	     */
	    private void drawBoard() {
	        GraphicsContext g = board.getGraphicsContext2D();
	        g.setFill( Color.DARKGREEN);
	        g.fillRect(0,0,board.getWidth(),board.getHeight());
	        g.setFill( Color.rgb(220,255,220) );
	        g.setFont( Font.font(16) );
	        //draw the message at the bottom of the board.
	        g.fillText(message,20,180);
	        int cardCt = hand.getCardCount(); //make the counting local because there are useful instance counter methods defined in the object.
	        //draw the faced up card.
	        for (int i = 0; i < cardCt; i++)
	            drawCard(g, hand.getCard(i), 20 + i * 99, 20); //for the first faced up card, it must occupy 99 pixels with 20 pixel border.
	        //draw the faced down card.
	        if (gameInProgress) 
	            drawCard(g, null, 20 + cardCt * 99, 20); //for the second card, it must occupy 99 pixels with 20 pixel border * the cardCt.
	    } 


	    /**
	     * Draws a card with top-left corner at (x,y).  If card is null,
	     * then a face-down card is drawn.  The card images are from 
	     * the file cards.png; this program will fail without it.
	     */
	    private void drawCard(GraphicsContext g, Card card, int dx, int dy) {
	        int cardRow, cardCol; //this variable will help map the coordinates of the card being drawn.
	        if (card == null) {  
	        	//draw the card at source x = 158px and y = 492px
	            cardRow = 4;   // row and column of a face down card (0,1,2,3,4) 
	            			//calculated later as: 4*123 = 492 pixels
	            cardCol = 2; //calculated later as 2*79 = 158px
	        }
	        else {
	        	//draw the card elsewhere depending on the coordinates of the image.
	            cardRow = 3 - card.getSuit(); //3 here is a restriction because there are no joker card and the possible diff is only 0,1,2,3.
	            							//So, the highest suit is 3 which is equal to clubs.
	            							//3 *123 = 363 pixels -> the last card that can be drawn from top to bottom is at 363 pixels.
	            							//Example when it is clubs, it is always equal to zero meaning the height is at 0px.      							
	            							
	            cardCol = card.getValue() - 1; //-1 means; that 1027pixels can represent 13 cards with 79px. 
	            						//Since the highest value is King = 13, to draw that last card, move back 79 pixels which
	            						// is equivalent to 1 card... So for example, 13*79 = 1027 - 79 = 948 -> draw it at x = 948 pixels.
	        }
	        double sx,sy;  // top left corner of source rect for card in cardImages
	        sx = 79 * cardCol; //cardCol is the # card's width
	        sy = 123 * cardRow; //cardRow is the # card's height
	        g.drawImage( cardImages, sx,sy,79,123, dx,dy,79,123 );
	    } // end drawCard()


	

} //end class CardGuess
