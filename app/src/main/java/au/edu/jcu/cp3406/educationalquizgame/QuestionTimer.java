package au.edu.jcu.cp3406.educationalquizgame;

import androidx.annotation.NonNull;

import java.util.Formatter;
import java.util.Locale;

public class QuestionTimer {
  private int minutes, seconds;
  QuestionTimer() {// in seconds
    minutes = 0;
    seconds = 0;
  }
  //QuestionTimer(int totalTime) {// in seconds
    //minutes = totalTime/60;
    //seconds = totalTime % 60;
  //}
  public QuestionTimer(int minutes, int seconds){
    this.minutes = minutes;
    this.seconds = seconds;
  }

  void tick(){
    seconds--;
    if(seconds < 0){
      minutes--;
      seconds = 59;
    }
  }

  @NonNull
  @Override
  public String toString() {
    // StringBuilder class represent a mutable string (String class is immutable)
    StringBuilder stringBuilder = new StringBuilder();
    Formatter formatter = new Formatter(stringBuilder, Locale.getDefault());

    formatter.format("%02d:%02d", minutes,seconds);
    return formatter.toString();
  }

  public int getSeconds() {
    return seconds;
  }

  public int getMinutes() {
    return minutes;
  }
}
