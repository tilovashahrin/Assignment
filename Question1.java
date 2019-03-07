import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.layout.HBox;

public class Question1 extends Application{
	@Override 
	public void start(Stage primaryStage){
    	HBox pane = new HBox(7);

    	ArrayList<Integer> cards= getcards();

    	for(int i = 0; i < 3; i++){
    		pane.getChildren().add(new ImageView("file:///C:/Users/Tilova Shahrin/Desktop/Cards/" + cards.get(i) + ".png"));
    	}

    	Scene scene = new Scene(pane);
    	primaryStage.setTitle("Question1"); // Set the stage title
    	primaryStage.setScene(scene); // Place the scene in the stage
    	primaryStage.show(); // Display the stage
	}

	private ArrayList<Integer> getcards(){
		ArrayList<Integer> cards= new ArrayList<>();
    	for(int i = 0; i < 54; i++){
    		cards.add(i + 1);
    	}

    	java.util.Collections.shuffle(cards);
    	return cards;
	}

	public static void main(String [] args){
		launch(args);
	}
}