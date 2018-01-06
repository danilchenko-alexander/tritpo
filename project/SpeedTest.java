package application;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import application.Menu.MenuItem;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpeedTest extends Test{
  
  public final int maxTimeForTest = 10;
  public SpeedTest(Scene sc, Stage stt,String Lang){
    for (int j = 0; j < maxNumOfWords; j++){
      labels[j] = new Label();
      labels[j].setPadding(new Insets(15,0,15,0));
      testWords.add(labels[j],j,1);
    }
    Language = Lang;
    timeForTest = maxTimeForTest;
    rand = new Random();
    css = application.SpeedTest.class.getResource("application.css").toExternalForm();
    _stage = stt;
    _stage.setOnCloseRequest(e->{
      e.consume();
      System.exit(0);
    });
    timerLabel.setText(""+timeForTest);
    refresh = new Button("Refresh");
    back = new Button("Back");
    //refresh.graphicProperty().setValue(new ImageView(new Image(getClass().getResourceAsStream("fast-typing.jpg"))));
    refresh.setPadding(new Insets(25,15,25,15));
    back.setPadding(new Insets(25,25,25,25));
    back.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        _Refresh();
        stt.setScene(sc);
      }
      
    });
    refresh.setOnMouseClicked(new EventHandler<Event>() {

      @Override
      public void handle(Event event) {
        _Refresh();
      }
    });
  }
  
  public void _Refresh(){
    inputWords.setText("");
    if(flagStart)
    at.stop();
    fillingALine();
    timerLabel.setText(""+maxTimeForTest);
    flagStart = false;
    rightWordsCounter = 0;
    wrongWordsCounter = 0;
    Accurasy = "";
    counter = 0;
  }
  
  public void Read() throws FileNotFoundException{
    String line = new String();
    File file;
    if(Language == "English")
      file = new File("words1.txt");
    else file = new File("words2.txt");
    readWords = new Scanner(file);
    while(readWords.hasNextLine()){
      line += readWords.nextLine();
    }
    string = line.split(" ");
    readWords.close();
  }
  
  public void fillingALine(){
    String tmp = "";
    String currentWord;
    int numberOfChars = 0,i = 0;
    rand.nextInt(string.length);
    for (int j = 0; j < 10; j++){
      labels[j].setText("");
      labels[j].setPadding(new Insets(15,0,15,0));
    }
    while(numberOfChars < 50){
      currentWord = string[rand.nextInt(string.length)];
      if(numberOfChars+currentWord.length() < 50){
        numberOfChars+=currentWord.length()+1;
        tmp += currentWord;
        tmp += " ";
        labels[0].setStyle("-fx-background-color: lightgrey");
        labels[i].setText(currentWord);
        labels[i].setStyle("-fx-text-fill: black");
        labels[i].setPadding(new Insets(15,15,15,15));
        i++;
        numberOfTestWords = i;
        if(i == 10)
          break;
      }
      else break;
    }

    randomWordsInLine = tmp.split(" ");
    tmp = "";
    i = 0;
    numberOfChars = 0;
  }
  
  
  public void SceneFilling(){
    sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
    testWords.setMinSize(950, 40);
    InputWords.setPadding(new Insets(15,0,15,0));
    InputWords.setMargin(inputWords, new Insets(0,10,0,10));
    InputWords.setMargin(refresh, new Insets(0,10,0,10));
    testWords.setStyle("-fx-font-size: 30;"
        + " -fx-border-style: solid;"
        + "-fx-alignment: CENTER;"
        + "-fx-background-color: white;"
        + "-fx-border-color: darkturquoise");
    timerLabel.setStyle("-fx-border-style: solid;"
        + "-fx-background-color: white;"
        + "-fx-font-size: 30;"
        + "-fx-border-color: darkturquoise;");
    InputWords.setStyle("-fx-alignment: CENTER;");
    timerLabel.setPadding(new Insets(10,25,10,25));
    inputWords.setStyle("-fx-font-size: 30");
    InputWords.add(inputWords, 0, 0);
    InputWords.add(timerLabel, 1, 0);
    InputWords.add(refresh, 2, 0);
    InputWords.add(back, 3, 0);
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(35);
    grid.setVgap(80);
    grid.setPadding(new Insets(25,25,25,25));
    grid.add(sceneTitle, 0, 0, 2, 1);
    grid.add(stringWithTestWords, 0, 1);
    grid.add(testWords,1,1);
    grid.add(stringWithinputWords,0,2); 
    grid.add(InputWords, 1,2);
    Image image = new Image(getClass().getResourceAsStream("background.jpg"));
    ImageView img = new ImageView(image);
    img.setFitWidth(1100);
    img.setFitHeight(500);
    root.getChildren().add(img);
    root.getChildren().add(grid);
    
    scene = new Scene(root,1100,500);
    scene.getStylesheets().add(css);
    inputWords.setOnKeyReleased(event->{
      testController(event);
    });
    inputWords.setOnKeyPressed(event->{
      if(event.getCode() == KeyCode.SPACE){
        if(inputWords.getText().length() > 1){
          if(inputWords.getText().substring(0, inputWords.getText().length()).equals(labels[counter].getText())){ 
            labels[counter].setStyle("-fx-text-fill: green; -fx-background-color: white");
            rightWordsCounter++;
          }
            else {
              labels[counter].setStyle("-fx-text-fill: red; -fx-background-color: white");
              wrongWordsCounter++;
            }
          counter++;
          labels[counter].setStyle("-fx-background-color: lightgrey");
          if (counter == numberOfTestWords){
            fillingALine();
            counter = 0;
          }
        }
        inputWords.clear();
      }
      if(!flagStart){
        timeForTest = maxTimeForTest;
        at = new AnimationTimer(){

          @Override
          public void handle(long now) {
            //--
            timerLabel.setText(""+timeForTest);
            if(timeForTest == 0){
              timerLabel.setText(""+timeForTest);
              ShowResult();
            }
            at.stop();
          }
          
        };
        tt = new testTimer();
        tt.start();
        flagStart = true;
      }
    });
    
  }
  
 
  public void ShowResult(){
    Image image = new Image(getClass().getResourceAsStream("background.jpg"));
    ImageView img = new ImageView(image);
    img.setFitWidth(1100);
    img.setFitHeight(500);
    Button _return = new Button("return");
    MenuItem mi = new MenuItem("return");
    _return.setPadding(new Insets(10,60,10,60));
    Pane _root = new Pane();
    GridPane resultGrid = new GridPane();
    
    resultGrid.setPadding(new Insets(155,0,0,350));
    Text WPM = new Text("WPM (words per minute):  " + rightWordsCounter);
    Text right_words = new Text("Right words:   " + rightWordsCounter);
    Text wrong_words = new Text("Wrong words:   " + wrongWordsCounter);
    Text accurasy;
    accurasy = new Text();
    if(rightWordsCounter > 0)
    accurasy.setText("Accurasy:   " +(100-( Math.round(((double)wrongWordsCounter/
        (double)(wrongWordsCounter+rightWordsCounter))*100)))+"%");
    else
      accurasy.setText("Accurasy:   "+"0%");
    
    GridPane wpm = new GridPane();
    GridPane rw = new GridPane();
    GridPane ww = new GridPane();
    GridPane ac = new GridPane();
    wpm.add(WPM, 0, 0);
    rw.add(right_words, 0, 0);
    ww.add(wrong_words, 0, 0);
    ac.add(accurasy, 0, 0);
    WPM.setStyle("-fx-font-size: 30"
        + "");
    WPM.setFont(Font.font("Arial",FontWeight.THIN, FontPosture.ITALIC,50));
    WPM.setFill(Color.GREEN);
    
    right_words.setStyle("-fx-font-size: 30");
    wrong_words.setStyle("-fx-font-size: 30");
    accurasy.setStyle("-fx-font-size: 30");
    resultGrid.setMargin(rw,new Insets(0,0,0,90));
    resultGrid.setMargin(ww,new Insets(0,0,0,75));
    resultGrid.setMargin(ac,new Insets(0,0,0,105));
    resultGrid.setMargin(_return,new Insets(50,0,0,125));
    resultGrid.add(wpm, 0, 0);
    resultGrid.add(rw, 0, 1);
    resultGrid.add(ww, 0, 2);
    resultGrid.add(ac, 0, 3);
    resultGrid.add(_return, 0, 4);
    
    _return.setOnMouseClicked(new EventHandler<Event>() {

      @Override
      public void handle(Event event) {
        _stage.setScene(scene);
        _Refresh();
      }
      
    });
    
    _root.getChildren().add(img);
    _root.getChildren().add(resultGrid);
    FadeTransition ft = new FadeTransition(Duration.seconds(3),_root);
      ft.setFromValue(0);
      ft.setToValue(1);
      ft.play();
    Scene _scene = new Scene(_root,1100,500);
    _scene.getStylesheets().add(css);
    _stage.setScene(_scene);
  }
  
  private void testController(KeyEvent event){
    if(timeForTest!=0){
    if(labels[counter].getText().contains(inputWords.getText().replaceAll(" ", "")))
    {
      labels[counter].setStyle("-fx-background-color: lightgrey");
    //  System.out.println("input: |"+inputWords.getText()+"|          test: |"+ labels[counter].getText()+"|");
      }
    else labels[counter].setStyle("-fx-background-color: red");
      if(event.getCode() == KeyCode.SPACE){
        inputWords.clear();
      }
    }
    else {
      inputWords.clear();
    }
  }
  
  public class testTimer extends Thread{
    public void run(){
      try {
        while (!isInterrupted()){ 
          at.start();
          sleep(1000);
          
          if(timeForTest == 0){
            tt.interrupt();
            saveStats();
          }
          timeForTest--;
          if(!flagStart){
            tt.interrupt();
          }
        }
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  public int getTime(){
    return timeForTest;
  }
  
  
  public void saveStats(){
    Date date = new Date();
    Accurasy = ((100-( Math.round(((double)wrongWordsCounter/
        (double)(wrongWordsCounter+rightWordsCounter))*100)))+"%").toString();
    
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd kk:mm:ss" + " | "
        + ""+rightWordsCounter+"   "+Accurasy);
    try{
      File file = new File("stats.txt");
      PrintWriter writer = new PrintWriter (new BufferedWriter(new FileWriter(file,true)));
      writer.println(formatForDateNow.format(date) + "     " + Language);
      writer.close();
    }catch(IOException ex){
      ex.printStackTrace();
    }
    
  }
  
  public Scene getScene() throws FileNotFoundException{
    Read();
    fillingALine();
    SceneFilling();
    return scene;
  }
  
}


