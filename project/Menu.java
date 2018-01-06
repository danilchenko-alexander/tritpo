package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Menu extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		primaryStage.setResizable(false);
		primaryStage.setX(200);
        primaryStage.setY(100);
		Image image = new Image(getClass().getResourceAsStream("fast-typing.jpg"));
		ImageView img = new ImageView(image);
		img.setFitWidth(700);
		img.setFitHeight(500);
		root.getChildren().add(img);
		
		MenuItem startTypingTest = new MenuItem("Start typing test");
		MenuItem results = new MenuItem("Results");
		MenuItem exit = new MenuItem("Exit");
		SubMenu mainMenu = new SubMenu(startTypingTest,results,exit);
		
		MenuItem Test1 = new MenuItem("Speed typing test");
		MenuItem Test2 = new MenuItem("Moving String");
		MenuItem TestBack = new MenuItem("Back");
		MenuItem LangBack = new MenuItem("Back");
		MenuItem LangRus = new MenuItem("Русский");
		MenuItem LangEng = new MenuItem("English");
		
		
		SubMenu Test1SubMenu = new SubMenu(LangRus,LangEng,LangBack);
		SubMenu startTypingTestMenu = new SubMenu(Test1,Test2,TestBack);
		
		MenuBox menuBox = new MenuBox(mainMenu);
		startTypingTest.setOnMouseClicked(event->menuBox.setSubMenu(startTypingTestMenu));
		exit.setOnMouseClicked(event-> System.exit(0));
		startTypingTest.setOnMouseClicked(event->menuBox.setSubMenu(Test1SubMenu));
		TestBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
		LangBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
		root.getChildren().addAll(menuBox);
		Scene scene = new Scene(root,690,490);
		LangRus.setOnMouseClicked(new EventHandler<Event>() {

      @Override
      public void handle(Event event) {
        SpeedTest s = new SpeedTest(scene,primaryStage,"Russian");
        try {
          
          primaryStage.setScene(s.getScene());
          
       } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
       }
      }
		  
		});
		
		LangEng.setOnMouseClicked(new EventHandler<Event>() {

      @Override
         public void handle(Event event) {
         SpeedTest s = new SpeedTest(scene,primaryStage,"English");
         try {
           
           primaryStage.setScene(s.getScene());
           
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
         } 
		});
		
		Test2.setOnMouseClicked(new EventHandler<Event>() {

      @Override
      public void handle(Event event) {
        
        MovingString movingString = new MovingString();
        primaryStage.setScene(movingString.getScene());  
      }
		  
		});
		
		
		results.setOnMouseClicked(new EventHandler<Event>() {
		  @Override
		  public void handle(Event event) {
		    try {
		      Stage resWindow = new Stage();
              Parent _root = FXMLLoader.load(getClass().getResource("/application/table.fxml"));
              Scene table = new Scene(_root);
              table.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
              resWindow.setScene(table);
              resWindow.show();
              
        } catch (IOException e) {
          e.printStackTrace();
        }
		  }
		});
		
		scene.setOnKeyPressed(event-> {
		  if(event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
		    FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
		    if(!menuBox.isVisible()){
		      ft.setFromValue(0);
		      ft.setToValue(1);
		      ft.play();
		      menuBox.setVisible(true);
		    }
		    else{
		      ft.setFromValue(1);
		      ft.setToValue(0);
		      ft.setOnFinished(evt -> menuBox.setVisible(false));
		      ft.play();
		    }
		  }
		});
		primaryStage.setTitle("Test your fingers");
		primaryStage.getIcons().add(new Image("file:keyboard.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static class MenuItem extends StackPane{
	  public MenuItem(String name){
	    Rectangle bg = new Rectangle(200,20,Color.BLACK);
	    bg.setOpacity(0.5);
	    
	    Text text = new Text(name);
	    text.setFont(Font.font("Arial",FontWeight.BOLD,14));
	    
	    setAlignment(Pos.CENTER);
	    getChildren().addAll(bg,text);
	    FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
	    setOnMouseEntered (event-> {
	      st.setFromValue(Color.DARKGRAY);
	      st.setToValue(Color.DARKGOLDENROD);
	      st.setCycleCount(Animation.INDEFINITE);
	      st.setAutoReverse(true);
	      st.play();
	    });
	    
	    setOnMouseExited(event-> {
	      st.stop();
	      bg.setFill(Color.WHITE);
	    });
	  }
	}
	
	private static class SubMenu extends VBox{
	  public SubMenu(MenuItem...items){
	    setSpacing(15);
	    setTranslateY(100);
        setTranslateX(50);
	    for(MenuItem item: items){
	      getChildren().addAll(item);
	    }
	  }
	}
	
	private static class MenuBox extends Pane{
	  static SubMenu subMenu;
	  public MenuBox(SubMenu subMenu){
	    MenuBox.subMenu = subMenu;
	    
	    setVisible(false);
	    Rectangle bg = new Rectangle(700,500,Color.LIGHTBLUE);
	    bg.setOpacity(0.4);
	    getChildren().addAll(bg,subMenu);
	  }
	  public void setSubMenu(SubMenu subMenu){
	    getChildren().remove(MenuBox.subMenu);
	    MenuBox.subMenu = subMenu;
	    getChildren().add(subMenu);
	  }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
