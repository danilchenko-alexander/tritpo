package application;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.security.auth.callback.ConfirmationCallback;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{
  @FXML private TableView<TableData> table;
  @FXML private TableColumn<TableData, String> _date;
  @FXML private TableColumn<TableData, Integer> _wpm;
  @FXML private TableColumn<TableData, String> _accurasy;
  @FXML private TableColumn<TableData, String> _Language;
  
  public MainController() throws FileNotFoundException{
    fillRows();
  }
  public ObservableList<TableData> list = FXCollections.observableArrayList();
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    _date.setCellValueFactory(new PropertyValueFactory<TableData, String>("Date"));
    _wpm.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("wpm"));
    _accurasy.setCellValueFactory(new PropertyValueFactory<TableData, String>("Accurasy"));
    _Language.setCellValueFactory(new PropertyValueFactory<TableData, String>("Language"));
    table.setItems(list);
  }
  
  public void ClearTableClicked(){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("If you clear the table - all data will be lost.");
    alert.setContentText("Are you ok with this?");
    Optional<ButtonType> result = alert.showAndWait();
    
    if (result.get() == ButtonType.OK){
     list.clear();
     try {
       File file = new File("stats.txt");
       FileWriter fstream1 = new FileWriter(file);
       BufferedWriter out1 = new BufferedWriter(fstream1);
       out1.write("");
        out1.close(); 
        } catch (Exception e) 
           {System.err.println("Error in file cleaning: " + e.getMessage());}

    }
  }
  
  public void fillRows() throws FileNotFoundException{
    File file = new File("stats.txt");
    Scanner scan = new Scanner(file);
    ArrayList<String> Stats = new ArrayList<String>();
    while(scan.hasNextLine()){
      Stats.add(scan.nextLine());
    }
    
    for (Object o : Stats){
      list.add(new TableData(o.toString().substring(0,22),
          Integer.parseInt(o.toString().substring(25,28).replaceAll(" ", "")),
          o.toString().substring(29,34).replaceAll(" ", ""),o.toString().substring(35).replaceAll(" ", "")));
    }
    scan.close();
  }
  
}
