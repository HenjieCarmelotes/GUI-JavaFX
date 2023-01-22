package guijavafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Shows a pair of dice that are rolled when the user clicks on the
 * canvas where the dice are drawn.
 */
public class RollingDice extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }

    //---------------------------------------------------------------------

    private int die1 = 4;  // The values shown on the dice for the.
    private int die2 = 3;
    
    private int dieX1 = 10;
    private int dieY1 = 10;
    private int dieX2 = 55;
    private int dieY2 = 55;
    
    private Button rollButton;  // The button that is clicked to roll the dice.
    
    private int frameNumber;  // When an animation is running, the number of
                              //    frames for which it has been running.  This
                              //    is used to end the animation after 60 frames.

    private Canvas canvas;  // The canvas on which the dice are drawn.
    

    /**
     *  The start() method sets up the GUI and installs a mouse listener
     *  on the canvas where the dice are to be drawn.
     */
    public void start(Stage stage) {
    	
    	
        
        canvas = new Canvas(100,100);
        draw();  // Draw the original dice.
        
        rollButton = new Button("Roll!");
        rollButton.setMaxWidth(1000);  // so button can grow to full width of window
        rollButton.setOnAction( e -> roll() ); // When clicked, roll the dice.
        
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(rollButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Roll the Dice!");
        stage.setResizable(false);
        stage.show();
        
    }
    


	/**
     * Draw a die with upper left corner at (x,y).  The die is
     * 35 by 35 pixels in size.  The val parameter gives the
     * value showing on the die (that is, the number of dots).
     */
    private void drawDie(GraphicsContext g, int val, int x, int y) {
        g.setFill(Color.WHITE);
        g.fillRect(x, y, 35, 35);
        g.setStroke(Color.BLACK);
        g.strokeRect(x+0.5, y+0.5, 34, 34);
        g.setFill(Color.BLACK);
        
        if (val > 1)  // upper left dot
            g.fillOval(x+3, y+3, 9, 9);
        if (val > 3)  // upper right dot
            g.fillOval(x+23, y+3, 9, 9);
        if (val == 6) // middle left dot
            g.fillOval(x+3, y+13, 9, 9);
        if (val % 2 == 1) // middle dot (for odd-numbered val's)
            g.fillOval(x+13, y+13, 9, 9);
        if (val == 6) // middle right dot
            g.fillOval(x+23, y+13, 9, 9);
        if (val > 3)  // bottom left dot
            g.fillOval(x+3, y+23, 9, 9);
        if (val > 1)  // bottom right dot
            g.fillOval(x+23, y+23, 9,9);
        
       
        
    }


    /**
     * Roll the dice by randomizing their values.  Tell the
     * system to repaint the canvas, to show the new values and randomize their X position.
     */
    private void roll() {
    
    	frameNumber = 0;
        rollButton.setDisable(true);
        timer.start(); // start an animation
    }


    /**
     * The draw() method just draws the two dice and draws
     * a two-pixel wide blue border around the canvas.
     */
    private void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.rgb(200,200,255));
        g.fillRect(0,0,100,100);
        g.setStroke( Color.BLUE );
        g.setLineWidth(2);
        g.strokeRect(1,1,98,98);
        drawDie(g, die1, dieX1, dieY1);
        drawDie(g, die2, dieX2, dieY2);
    }
	
    
    private AnimationTimer timer = new AnimationTimer() {
        // The timer is used to animate "rolling" of the dice.
        // In each frame, the dice values are randomized.  When
        // the number of frames reaches 60, the timer stops itself.
        // The rollButton is disabled while an animation is in
        // progress, so it has to be enabled when the animation stops.
	    public void handle( long time ) { //will be called 60 frames in 1 second.
	    	die1 = (int)(Math.random()*6) + 1;
	        die2 = (int)(Math.random()*6) + 1;
	        dieX1 = (int)(Math.random()*89) + 1; //randomize the x coordinate of the first die
	        if (dieX1 < 5)
	        	dieX1 = 5;
	        if (dieX1 > 60)
	        	dieX1 = 60;
	        dieY1 = (int)(Math.random()*89) + 1; //randomize the y coordinate of the second die
	        if (dieY1 < 5)
	        	dieY1 = 5;
	        if (dieY1 > 60)
	        	dieY1 = 60;
	        dieX2 = (int)(Math.random()*89) + 1; //randomize the x coordinate of the second die
	        if (dieX2 < 5)
	        	dieX2 = 5;
	        if (dieX2 > 60)
	        	dieX2 = 60;
	        dieY2 = (int)(Math.random()*89) + 1; //randomize the y coordinate of the second die
	        if (dieY2 < 5)
	        	dieY2 = 5;
	        if (dieY2 > 60)
	        	dieY2 = 60;
	        if (Math.abs(dieX1 - dieX2) <= 48 && Math.abs(dieY1 - dieY2) <=30) {
	        	dieX1 = 5;
	        	dieX2 = 60;
	        	dieY1 = 5;
	        	dieY2 = 60;
	        }
	        
	        
	        //draw the dice
	        draw();
	        frameNumber++; //increment the frameNumner
	        if (frameNumber == 60) {
	            timer.stop();
	            rollButton.setDisable(false);
	        }
    }
};

	

} //end class RollingDice
