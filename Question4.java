import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Question4 extends Application{

	Pane paneObj = new Pane();
	TextField textObj = new TextField();
	VBox vbox = new VBox();

	@Override
	public void start(Stage primaryStage) throws Exception{

		Label fileNamelbl = new Label("Filename:", textObj);
		fileNamelbl.setContentDisplay(ContentDisplay.RIGHT);
		textObj.setPrefColumnCount(20);

		Button btview = new Button("View");
		HBox hbox = new HBox(fileNamelbl, btview);
		vbox.getChildren().addAll(paneObj, hbox);

		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Histogram");
		primaryStage.sizeToScene();

		btview.setOnAction(e ->
		{
			updateHistogram();
			vbox.setTranslateY(10);

		}); 

		primaryStage.show();

	}

	private void updateHistogram(){

		Histogram graph = new Histogram(textObj.getText());
		paneObj.getChildren().add(graph);

	}

	private static class Histogram extends Pane{

		private char[] chars= new char[26];
		private int counts[] = new int[26];
		private Rectangle[] rectangleBar = new Rectangle[26];
		private File fileObj;

		GridPane gridpane;
		double w = 350;
		double h = 350;

		public Histogram(String filename){
			this.fileObj = new File(filename.trim());
			setWidth(w);
			setHeight(h);
			readFile();
			drawHistogram();
		} 

		private void readFile(){
			Scanner scanner;
			String s = "";

			try
			{
				scanner = new Scanner(fileObj);

				while(scanner.hasNextLine())
				{
					s+= scanner.nextLine();
				}
			} catch(IOException ex)
			{

			} 
			s = s.toUpperCase();

			for(int i = 0; i < s.length(); i++){
				char character = s.charAt(i);
				if(Character.isLetter(character)){
					counts[character - 'A']++;
				}
			}
		}

		private double getTotalCount(){
			double total = 0; 
			for(int count: counts){
				total += count;
			}
			return total;
		}

		private void drawHistogram(){
			gridpane = new GridPane();
			double barW = w/chars.length;
			double total = getTotalCount();

			for(int i = 0; i < counts.length; i++){
				chars[i] = (char)('A' + i);
				double percentage = counts[i] / total;
				double barH = h*percentage;
				rectangleBar[i] = new Rectangle(barW, barH);
				Label label = new Label(chars[i] + "", rectangleBar[i]); 
				label.setContentDisplay(ContentDisplay.TOP);
				gridpane.add(label, i, 0);
				GridPane.setValignment(label, VPos.BASELINE);
			}

			getChildren().addAll(gridpane);
		}

		public int[] getCounts(){
			return counts;
		}

		public void setCounts(int[] counts){
			this.counts = counts;
			drawHistogram();
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
