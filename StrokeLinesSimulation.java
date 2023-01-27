package guijavafx;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;


public class StrokeLinesSimulation extends Application {
	
	 public static void main(String[] args) {
	        launch(args);
	    }

	    //---------------------------------------------------------------------


	 

	    private Canvas canvas;  // The canvas where the shapes are drawn.


	 
	    public void start(Stage stage) {

	        canvas = new Canvas(220,250);
	        draw(); // show squares in original positions
	        
	        
	        Pane root = new Pane(canvas);
	        
	        Scene scene = new Scene(root);  
	        stage.setScene(scene);
	        stage.setTitle("StrokeLine Simulation");
	        stage.setResizable(false);
	        stage.show();
	    } 


	    /**
	     * The outer loop is executed for values of i equal to 10, 60, 110, 160, and 210. 
	     * For each of these values, the inner loop is executed for j equal to 10, 60, 110, 160, and 210. 
	     * The strokeLine command is therefore executed 25 times—and so, 25 different lines are drawn.
	     * These lines connect the five points (10,10), (60,10), (110,10), (160,10), and 
	     * (210,10) to the five points (10,60), (60,60), (110,60), (160,60), and (210,60) 
	     * in all possible pairings.
	     */
	    private void draw() {
	        GraphicsContext g = canvas.getGraphicsContext2D();
	        
	        for (int i=10; i <= 210; i = i + 50) {
	        	   for (int j = 10; j <= 210; j = j + 50) {
	        	      g.strokeLine(i,10,j,60); //strokeline(x1,y1,x2,y2) x1,y1 -> start point && x2,y2 => end point
	        	   }
	        	}
	        
	        //Drawing Triangle.
	        
	        g.strokeLine(10,240,110,70);
	        g.strokeLine(10,240,210,240);
	        g.strokeLine(210,240,110,70);


	        
	        
	
	        
	    } //end of draw()


} //end of class StrokeLinesSimulation
