package au.edu.jcu.cp3406.educationalquizgame;

import androidx.annotation.NonNull;

import java.util.Formatter;
import java.util.Locale;

public class QuestionTimer {
  private int minutes, seconds;
  QuestionTimer(int totalTime) {// in seconds
    minutes = totalTime/60;
    seconds = totalTime % 60;
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


}
