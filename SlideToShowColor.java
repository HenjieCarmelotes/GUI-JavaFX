package guijavafx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SlideToShowColor extends Application {
	
	private Slider hueSlider, brightnessSlider, saturationSlider,  // Sliders to control color components.
    redSlider, greenSlider, blueSlider;
	

	private Label hueLabel, brightnessLabel, saturationLabel,  // For displaying color component values.
	    redLabel, greenLabel, blueLabel;

	private Pane colorPatch;  // Color patch for displaying the color.
	
	public static void main(String[] args) {
		launch();
	}
	
	private Label makeText(String message) {
		// Make a label to show a given message shown in bold, with some padding
		// between the text and the border of the label.
		Label text = new Label(message);
		text.setStyle("-fx-padding: 2px 4px 2px 4px; -fx-font-weight:bold");
		return text;
	}
	
	private void colorPatch(Slider whichSlider) {
		// Adjust the GUI to a new color value, when one of the sliders has changed.
		if ( ! whichSlider.isValueChanging() ) {
			return; // Don't respond to change if it was set programmatically;
			// Only respond if it was set by user dragging the slider.
			}
		
		Color color;
		if (whichSlider == redSlider || whichSlider == greenSlider || whichSlider == blueSlider) {
			color = Color.color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()); //returns double color.
			hueSlider.setValue(color.getHue());
			brightnessSlider.setValue(color.getBrightness());
			saturationSlider.setValue(color.getSaturation());
			}
		else {
			color = Color.hsb(hueSlider.getValue(), saturationSlider.getValue(), brightnessSlider.getValue());
			redSlider.setValue(color.getRed());
			greenSlider.setValue(color.getGreen());
			blueSlider.setValue(color.getBlue());
			}
		
		/* Let the value of the color appear on the patch (Pane). */
		
		String colorString = String.format("#%02x%02x%02x", (int)(255*color.getRed()),
							(int)(255*color.getGreen()), (int)(255*color.getBlue()) );
		
		colorPatch.setStyle("-fx-border-color:black; -fx-border-width:2px; -fx-background-color:" + colorString);
		
		
		/* Let the value of the color appear as text. */
		
		hueLabel.setText(String.format(" Hue = %1.3f", color.getHue()));
		saturationLabel.setText(String.format(" Saturation = %1.3f", color.getSaturation()));
		brightnessLabel.setText(String.format(" Brightness = %1.3f", color.getBrightness()));
		redLabel.setText(String.format(" Red = %1.3f", color.getRed()));
		greenLabel.setText(String.format(" Green = %1.3f", color.getGreen()));
		blueLabel.setText(String.format(" Blue = %1.3f", color.getBlue()));
	} //end of class colorPatch()
	
	
	public void start(Stage stage) {
		
		/* Create Sliders with possible values from 0 to 1, or 0 to 360 for hue and decorate all the sliders. */
		
		hueSlider = new Slider(0,360,0);
		hueSlider.setMajorTickUnit(90); // space between big tick marks, measured using slider values
		hueSlider.setMinorTickCount(4); 
		hueSlider.setShowTickMarks(true);
		hueSlider.setShowTickLabels(true);
		
		saturationSlider = new Slider(0,1,1);
		saturationSlider.setMajorTickUnit(0.25); // space between big tick marks, measured using slider values
		saturationSlider.setMinorTickCount(5); 
		saturationSlider.setShowTickMarks(true);
		saturationSlider.setShowTickLabels(true);
		
		
		brightnessSlider = new Slider(0,1,1);
		brightnessSlider.setMajorTickUnit(0.25);
		brightnessSlider.setMinorTickCount(5); 
		brightnessSlider.setShowTickMarks(true);
		brightnessSlider.setShowTickLabels(true);
		
		
		redSlider = new Slider(0,1,1);
		redSlider.setMajorTickUnit(0.25);
		redSlider.setMinorTickCount(5); 
		redSlider.setShowTickMarks(true);
		redSlider.setShowTickLabels(true);
		
		
		greenSlider = new Slider(0,1,0);
		greenSlider.setMajorTickUnit(0.25);
		greenSlider.setMinorTickCount(5); 
		greenSlider.setShowTickMarks(true);
		greenSlider.setShowTickLabels(true);
		
		blueSlider = new Slider(0,1,0);
		blueSlider.setMajorTickUnit(0.25);
		blueSlider.setMinorTickCount(5); 
		blueSlider.setShowTickMarks(true);
		blueSlider.setShowTickLabels(true);
		
		/* Set up listeners to respond when a slider value is changed. Note that sliders don't generate action event. */
		
		hueSlider.valueProperty().addListener( e -> colorPatch(hueSlider) );
		saturationSlider.valueProperty().addListener( e -> colorPatch(saturationSlider) );
		brightnessSlider.valueProperty().addListener( e -> colorPatch(brightnessSlider) );
		redSlider.valueProperty().addListener( e -> colorPatch(redSlider) );
		greenSlider.valueProperty().addListener( e -> colorPatch(greenSlider) );
		blueSlider.valueProperty().addListener( e -> colorPatch(blueSlider) );
		
		/* Create Labels showing current RGB and HSB values. */
		
		hueLabel = makeText(String.format(" Hue = %1.3f", 0.0));
		saturationLabel = makeText(String.format(" Saturation = %1.3f", 1.0));
		brightnessLabel = makeText(String.format(" Brightness = %1.3f", 1.0));
		redLabel = makeText(String.format(" Red = %1.3f", 1.0));
		greenLabel = makeText(String.format(" Green = %1.3f", 0.0));
		blueLabel = makeText(String.format(" Blue = %1.3f", 0.0));
		
		/* Create an object to show the default color. */
		
		colorPatch = new Pane();
		colorPatch.setStyle("-fx-background-color:red; -fx-border-color:black; -fx-border-width:2px");
		
		/* Lay out the components: There are three columns to be laid out (0,1,2). */
		
		GridPane root = new GridPane();
		root.setHgap(5); //leave 5 pixel gaps between columns
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(33);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setPercentWidth(34);
		ColumnConstraints c3 = new ColumnConstraints();
		c3.setPercentWidth(33);
		root.getColumnConstraints().addAll(c1, c2, c3);
		
		/*Add in the first column (column position 0 and the given rows)*/
		
		root.add(hueSlider, 0, 0); //Param are as follows: child, column, and row.
		root.add(saturationSlider, 0, 1);
		root.add(brightnessSlider, 0, 2);
		root.add(redSlider, 0, 3);
		root.add(greenSlider, 0, 4);
		root.add(blueSlider, 0, 5);
		
		/*Add in the second column (column position 1 and the given rows)*/
		
		root.add(colorPatch, 1, 0, 1, 6);  // occupies 6 rows! -> Param are as follows: child, column, and row, columnCount, rowCount
		root.setStyle("-fx-padding:5px; -fx-border-color:darkblue; -fx-border-width:2px; -fx-background-color:#DDF");
	
		
		/*Add in the third column (column position 2 and the given rows)*/
		
		root.add(hueLabel, 2, 0);
		root.add(saturationLabel, 2, 1);
		root.add(brightnessLabel, 2, 2);
		root.add(redLabel, 2, 3);
		root.add(greenLabel, 2, 4);
		root.add(blueLabel, 2, 5);
	
		
		/* Create the scene and show the stage. */
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Slide To Show Color");
		stage.setResizable(false);
		stage.show();
	
	} // end start();



} //end of class SlideToShowColor
