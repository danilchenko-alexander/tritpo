package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class TestTimer{
  int time;
  Timer timer;
  TimerTask task ;
  
  public TestTimer(){
    time = 5;
    timer = new Timer();
    task = new TimerTask(){
      public void run(){
        time--;
        //SpeedTest.getLab().setText(""+time);
        //SpeedTest.timerLabel.setText(""+time);
        System.out.println("timer is: "+time);
        if(time == 0){
          stop();
        }
      }
    };
  }
  
  public void start(){
    timer.scheduleAtFixedRate(task, 1000, 1000);
  }
  
  public void stop(){
    timer.cancel();
  }
  
}
