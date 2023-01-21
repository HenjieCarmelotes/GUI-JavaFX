package guijavafx;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShapeSimulation extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    //---------------------------------------------------------------------


 

    private Canvas canvas;  // The canvas where the shapes are drawn.


 
    public void start(Stage stage) {

        canvas = new Canvas(100,100);
        draw(); // show squares in original positions
        
        
        Pane root = new Pane(canvas);
        
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.setTitle("Shape Simulation");
        stage.setResizable(false);
        stage.show();
    } 


    /**
     * Draw the canvas, showing the squares in their current positions.
     */
    private void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.RED);     // draw the shapes
        g.fillOval(10,10,80,80);
        g.setFill(Color.GREEN);
        g.fillRect(30,30,40,40);
    }


}
