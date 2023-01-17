package guijavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * A simple program that simulates BorderPane Layout showing how
 * the children can be positioned in the scene.
 * */

public class BorderPaneDemo extends Application {
	
	//-------------------------------------------------------------------------

    Checkerboard board;  // A canvas on which a checker board is drawn,
                         // defined by a static nested subclass.

    Button resignButton;      // Two buttons.
    Button newGameButton;

    Label message;   // A label for displaying messages to the user.

    int clickCount;   // Counts how many times the button was clicked.

	
	

	public static void main(String[] args) {
        launch(args);
    }
	
	
	public void start(Stage stage) {
		
		 /* Create the child nodes. */

        board = new Checkerboard(); // a subclass of Canvas, defined below
        board.draw();  // draws the content of the checkerboard

        newGameButton = new Button("New Game");
        newGameButton.setOnAction( e -> doNewGame() );

        resignButton = new Button("Resign");
        resignButton.setOnAction( e -> doResign() );
        
        Button quitButton = new Button("Click to quit");
		quitButton.setOnAction( e -> Platform.exit() );

        message = new Label("Click \"New Game\" to begin.");
        message.setTextFill( Color.rgb(100,255,100) ); // Light green.
        message.setFont( Font.font(null, FontWeight.BOLD, 18) );

		
		BorderPane root = new BorderPane();
		
		//Layout the child in the scene.
		
		root.setCenter(board);
		root.setTop(message);
		root.setRight(newGameButton);
		root.setLeft(resignButton);
		root.setBottom(quitButton);
		root.setStyle("-fx-background-color: darkblue; -fx-border-color: yellow; -fx-border-width:3");
		
		//Set one of its child in the center.
		BorderPane.setAlignment( quitButton, Pos.CENTER );
		
		//Set a margin to one of its child.
		BorderPane.setMargin( board, new Insets(2,5,2,5) ); //param as follows: top, right, bottom, left (in clockwise)
		Scene scene = new Scene(root);
		stage.setScene(scene);
        stage.setTitle("Sample BorderPane Layout");
        stage.show();
		 
	} //end of stage.
	
	
	/**
     * A method to be called when the user clicks "New Game"
     */
    private void doNewGame() {
        clickCount++;
        if (clickCount == 1)
            message.setText("First click:  \"New Game\" was clicked.");
        else
            message.setText("Click no. " + clickCount + ":  \"New Game\" was clicked."); 
    }
    
    
    /**
     * A method to be called when the user clicks "Resign"
     */
    private void doResign() {
        clickCount++;
        if (clickCount == 1)
            message.setText("First click:  \"Resign\" was clicked.");
        else
            message.setText("Click no. " + clickCount + ":  \"Resign\" was clicked."); 
    }


    /**
     * This canvas displays a 320-by-320 checkerboard pattern with
     * a 2-pixel dark red border.  The canvas will be exactly
     * 324-by-324 pixels.
     */
    private static class Checkerboard extends Canvas {

        public Checkerboard() {
            super(324,324); // Call constructor from Canvas class to set the size.
        }

        /**
         * Draws the content of the canvas.
         */
        public void draw() {
            
            GraphicsContext g = getGraphicsContext2D();

            // Draw a 2-pixel dark red border around the edges of the board.

            g.setStroke(Color.YELLOW);
            g.setLineWidth(2);
            g.strokeRect( 1, 1, 322, 322 );

            // Draw  checkerboard pattern in gray and lightGray.

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 )
                        g.setFill(Color.WHITE);
                    else
                        g.setFill(Color.RED);
                    g.fillRect(2 + col*40, 2 + row*40, 40, 40);
                }
            }
            
        }
        
    } // end nested class Checkerboard
	
	
	

} //end of class BorderPaneDemo
