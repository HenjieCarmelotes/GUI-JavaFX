package arraysGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;


public class RandomSquares extends Application {
	
	 public static void main(String[] args) {
	        launch(args);
	    }

	    //----------------------------------------------
	    

	    private Canvas canvas;  // The canvas on which the strings are drawn.
	    
	    private SquareData[] squareData;  // Holds all information needed
	                                      // to draw the square.

	    private static class SquareData {  // Info needed to draw one string.
	        double x,y;    // location of the square
	        double dx,dy;  // velocity of the square, in pixels per second... This one will update 
	        				//the x and y positions when the handle() is called.
	        Color color;   // color of the square
	    }
	    

	    public void start( Stage stage ) {
	    	
	        canvas = new Canvas(500,300);
	        createSquareData();
	        draw();  // draw content of canvas the first time.

	        Button redraw = new Button("Restart!");
	        redraw.setOnAction( e -> createSquareData() );
	             // When the button is clicked, the string data is re-initialized.
	             // There is no need to call draw() because the animation that
	             // runs continually will redraw the canvas in the next frame.

	        StackPane bottom = new StackPane(redraw);
	        bottom.setStyle("-fx-background-color: gray; -fx-padding:5px;" + 
	                " -fx-border-color:black; -fx-border-width: 2px 0 0 0");
	        BorderPane root = new BorderPane(canvas);
	        root.setBottom(bottom);
	        root.setStyle("-fx-border-color:black; -fx-border-width: 2px");

	        stage.setScene( new Scene(root, Color.BLACK) );
	        stage.setTitle("Random Squares");
	        stage.setResizable(false);
	        stage.show();
	        
	        AnimationTimer timer = new AnimationTimer() {
	               // The timer will run continually.  In each frame, all the circles
	               // will be moved, and the canvas will be redrawn.
	            long previousTime;
	            public void handle(long time) {
	                if (previousTime > 0) {
	                       // Time since previous call to handle is (time - previousTime),
	                       // in nanoseconds.  Dividing by 1e9 converts nanoseconds to seconds.
	                       // The first time handle() is called, previousTime is 0 and the
	                       // update is not done.
	                    updateSquareData( (time - previousTime)/1e9 );
	                }
	                draw();
	                previousTime = time;
	            }
	        };
	        timer.start();
	    	
	    }
	    
	    /**
	     * Creates an array of 100 SquareData objects and fills it with
	     * randomly generated data for each of the 100 squares.  This is
	     * called in the start() method and when the user clicks the
	     * "Restart!" button.
	     */
	    private void createSquareData() {
	    	squareData = new SquareData[100];
	        for (int i = 0; i < squareData.length; i++) {
	        	squareData[i] = new SquareData();
	        	squareData[i].x = canvas.getWidth() * Math.random();
	        	squareData[i].y = canvas.getHeight() * Math.random();
	        	squareData[i].dx = 50 + 150*Math.random(); // 50 to 200 pixels per second
	            if (Math.random() < 0.5) // 50% chance that dx is negative
	            	squareData[i].dx = -squareData[i].dx; //make the value of dx negative
	            squareData[i].dy = 50 + 150*Math.random();
	            if (Math.random() < 0.5) // 50% chance that dy is negative
	            	squareData[i].dy = -squareData[i].dy; //make the value of dy negative
	            squareData[i].color = Color.hsb( 360*Math.random(), 1.0, 1.0 );
	        }
	    } //end of createSquareData()
	    
	    
	    /**s
	     * Update the data for the 100 squares by moving each circle, where the
	     * distance moved depends on the velocity.  If a square moves too far
	     * off the canvas, move it to the opposite side of the canvas.
	     * (To make sure a square has moved all the way off the canvas to the
	     * left, wait until data.x reaches -400 before moving it to the
	     * right of the canvas. 
	     * @param deltaTimeInSeconds time that has elapsed since the previous
	     *          call to updateStringData, measured in seconds.
	     */
	    private void updateSquareData(double deltaTimeInSeconds) {
	        for ( SquareData data : squareData ) {
	            data.x += data.dx * deltaTimeInSeconds;
	            data.y += data.dy * deltaTimeInSeconds;
	            if (data.x < -400)
	                data.x = canvas.getWidth();
	            if (data.x > canvas.getWidth()+10)
	                data.x = -400;
	            if (data.y < -10)
	                data.y = canvas.getHeight() + 70;
	            if (data.y > canvas.getHeight() + 80)
	                data.y = -10;
	        }
	    } //end of update
	    
	    

	    /**
	     * The draw() method is responsible for drawing the content of the canvas.
	     * It draws 100 copies of the square, using a random color and
	     * position for each string.
	     */
	    private void draw() {

	        GraphicsContext g = canvas.getGraphicsContext2D();

	        double width = canvas.getWidth();
	        double height = canvas.getHeight();

	        g.setFill( Color.WHITE );  // fill with white background
	        g.fillRect(0, 0, width, height);

	        for ( SquareData data : squareData ) {

	            // Draw one square, using the properties in one of the
	            // SquareData objects from the array.

	            g.setFill( data.color );
	            g.setStroke(Color.BLACK);
	            g.fillRect(data.x,data.y,20,20);

	        } // end for

	    } // end draw()
	    
	    
	    

	    
	

} //end class RandomSquares
