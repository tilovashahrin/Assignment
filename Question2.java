import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Question2 extends Application{
	@Override
	public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);

        TextField tfamount = new TextField();
        TextField tfyears = new TextField();
        TextField tfinterestRate = new TextField();
        TextField tffutureValue = new TextField();
        
    	pane.add(new Label("Investment Amount:"), 0, 0);
        pane.add(tfamount, 1, 0);

        pane.add(new Label("Years"), 0, 1); 
        pane.add(tfyears, 1, 1);
        

        pane.add(new Label("Annual Interest Rate"), 0, 2);
        pane.add(tfinterestRate, 1, 2);
        

        pane.add(new Label("Future Value"), 0, 3);
        pane.add(tffutureValue, 1, 3);
        tffutureValue.setEditable(false);

        Button btCalculate = new Button("Calculate");
        pane.add(btCalculate, 0, 6);

        Scene scene = new Scene(pane, 300, 400);
        primaryStage.setTitle("Question2"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 

        btCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                double amount = Double.parseDouble(tfamount.getText());
                double years = Double.parseDouble(tfyears.getText());
                double interest = Double.parseDouble(tfinterestRate.getText()) / 1200;

                double result = amount * Math.pow((1 + interest), (years * 12));
                tffutureValue.setText(String.format("$%.2f", (result)));          
            }
        });

	}

	public static void main(String[] args){
		launch(args);
	}
}