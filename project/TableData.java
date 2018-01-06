package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
  private final SimpleStringProperty _date;
  private final SimpleIntegerProperty _wpm;
  private final SimpleStringProperty _accurasy;
  private final SimpleStringProperty _Language;
  
  public TableData(String _date, Integer _wpm, String _accurasy, String _Language) {
    super();
    this._date = new SimpleStringProperty(_date);
    this._wpm = new SimpleIntegerProperty(_wpm);
    this._accurasy = new SimpleStringProperty(_accurasy);
    this._Language = new SimpleStringProperty(_Language);
  }
  
  public Integer getWpm(){
    return _wpm.get();
  }
  
  public String getDate(){
    return _date.get();
  }
  
  public String getAccurasy(){
    return _accurasy.get();
  }
  
  public String getLanguage(){
    return _Language.get();
  }
}
