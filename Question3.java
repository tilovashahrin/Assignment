import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class Question3 extends Application{
	@Override

	public void start(Stage primaryStage){
		Pane pane = new Pane();

		double width = 250;
		double height = 250;
		
		Circle circle = new Circle(width/2, height/2, 100);
		pane.getChildren().add(circle);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);

		Circle[] circlePoints = new Circle[3];
		Line[] line = new Line[3];
		Text[] text = new Text[3];

		//iterates over points count
		for(int i = 0; i < circlePoints.length; i++){

			text[i] = new Text();
			circlePoints[i] = new Circle(0,0,5);
			circlePoints[i].setFill(Color.BLUE);
            circlePoints[i].setStroke(Color.BLUE);

			setARandomLocation(circlePoints[i], circle);
			
			final int index = i;
			
			circlePoints[i].setOnMouseDragged(e ->
			{
				double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
				double x = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
				double y = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);
				circlePoints[index].setCenterX(x);
				circlePoints[index].setCenterY(y);
				updateTheLineLocation(line, circlePoints, text);

			});
		}

		//iterate loops, to add up lines
		for(int i = 0; i < line.length; i++){
			int circleIndex = (i + 1 >= circlePoints.length) ? 0 : i + 1;
			line[i] = new Line(circlePoints[i].getCenterX(), circlePoints[i].getCenterY(), circlePoints[circleIndex].getCenterX(), circlePoints[circleIndex].getCenterY());

		}

		updateTheLineLocation(line, circlePoints, text);
		pane.getChildren().addAll(line);
		pane.getChildren().addAll(text);
		pane.getChildren().addAll(circlePoints);
		primaryStage.setScene(new Scene(pane, width, height));
		primaryStage.setTitle("Question3");
		primaryStage.show();
	}

	private void updateTheLineLocation(Line[] line, Circle[] p, Text[] angles){
		
		for(int i = 0; i < line.length; i++){
			
			int circleIndex = (i + 1 >= p.length) ? 0 : i + 1;
			line[i].setStartX(p[i].getCenterX());
			line[i].setStartY(p[i].getCenterY());
			line[i].setEndX(p[circleIndex].getCenterX());
			line[i].setEndY(p[circleIndex].getCenterY());
			angles[i].setX(p[i].getCenterX() + 5);
			angles[i].setY(p[i].getCenterY() - 5);
		}

		double a = drawLines(line[0]);
		double b = drawLines(line[1]);
		double c = drawLines(line[2]);

		double A = Math.toDegrees(Math.acos((a*a - b*b - c*c)/(-2*b*c)));
		angles[2].setText(String.format("%.2f", A));

		double B = Math.toDegrees(Math.acos((b*b - a*a - c*c)/(-2*a*c)));
		angles[0].setText(String.format("%.2f", B));

		double C = Math.toDegrees(Math.acos((c*c - b*b - a*a)/(-2*a*b)));
		angles[1].setText(String.format("%.2f", C));
	}

	public void setARandomLocation(Circle cPoint, Circle c){
		double angle = Math.random() * 360;
		double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
		double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));
		cPoint.setCenterX(x);
		cPoint.setCenterY(y);
	}

	public static double distanceCalculator(double x1, double y1, double x2, double y2){

		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
 	
 	public static double drawLines(Line line) {
        return distanceCalculator(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

	public static void main(String[] args){
		launch(args);
	}
}