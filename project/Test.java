package application;

import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import application.SpeedTest.testTimer;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Test {
  GridPane grid = new GridPane();
  Pane root = new Pane();
  Text sceneTitle = new Text("Welcome");
  TextField inputWords = new TextField();
  Label[] labels = new Label[12];
  Scene scene;
  Stage _stage;
  String[] string;
  String sss;
  String Language;
  String css ;
  int timeForTest;
  int counter = 0;
  int rightWordsCounter = 0;
  int wrongWordsCounter = 0;
  int maxNumOfWords = 11;
  String Accurasy;
  Random rand;
  boolean flagStart = false;
  boolean an = false;
  boolean th = false;
  String[] randomWordsInLine;
  int numberOfTestWords = 0; 
  Label stringWithTestWords = new Label("Test Words");
  Label stringWithinputWords = new Label("input");
  static Label timerLabel = new Label();
  Scanner readWords;
  Observable observable = new Observable();
  AnimationTimer at;
  testTimer tt;
  Button refresh;
  Button back;
  GridPane testWords = new GridPane();
  GridPane InputWords = new GridPane();
}
