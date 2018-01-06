package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MovingString extends Test {
  
  Label letters[] = new Label[50];
  GridPane TestLine = new GridPane();
  
  public MovingString(){
    for (int i = 0; i < 25; i++){
      letters[i] = new Label();
      letters[i].setStyle("-fx-background-color: lightgray");
      letters[i].setPadding(new Insets(7,20,7,20));
      TestLine.add(letters[i], i, 1);
    }
  }
  
  public void sceneFilling(){
    String css = application.SpeedTest.class.getResource("application.css").toExternalForm();
    TestLine.setMinSize(850, 40);
    TestLine.setStyle("-fx-background-color: white");
    grid.add(TestLine, 0, 0);
    
    
    Image image = new Image(getClass().getResourceAsStream("background.jpg"));
    ImageView img = new ImageView(image);
    img.setFitWidth(1100);
    img.setFitHeight(500);
    root.getChildren().add(img);
    root.getChildren().add(grid);
    grid.setAlignment(Pos.CENTER);
    scene = new Scene(root,1100,500);  
  }
  
  public Scene getScene(){
    sceneFilling();
    return scene;
  }
}
