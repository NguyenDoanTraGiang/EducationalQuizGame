package au.edu.jcu.cp3406.educationalquizgame;

/* Author: Doan Tra Giang Nguyen
Student ID: 13836396
App: Educational quiz game app for high school students
*/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  GameActivity gameActivity;
  Integer timeLimit;
  Integer maxQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set default value
        timeLimit = 180;
        maxQuestion = 4;

        // get data from SavedInstance
        if(savedInstanceState != null){
          timeLimit = savedInstanceState.getInt("timeLimit");
          maxQuestion = savedInstanceState.getInt("maxQuestion");
        }
    }

  // called when the UI changed, save data before activity is destroyed
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("timeLimit", timeLimit);
    outState.putInt("maxQuestion", maxQuestion);
  }

  public void levelBtnClicked(View view) {
      Button button = (Button) view;
      String btnText = button.getText().toString();

      // Start another activity using intent
      Intent intent = new Intent(this, GameActivity.class);
      // put the button name into intent so GameActivity can access it
      intent.putExtra("gameLabel", btnText);
      intent.putExtra("timeLimit", timeLimit);
      intent.putExtra("maxQuestion", maxQuestion);
      startActivity(intent);
  }

  public void settingBtnClicked(View view) {
    Intent intent = new Intent(this, SettingActivity.class);
    startActivityForResult(intent, SettingActivity.SETTING_REQUEST);
  }

  public void highScoreBtnClicked(View view) {
    Intent intent = new Intent(this, ViewHighScore.class);
    startActivity(intent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // get data from intent after pressing apply in SettingActivity
    if(requestCode == SettingActivity.SETTING_REQUEST){
      if(resultCode == RESULT_OK){
        if (data != null) {
          timeLimit = data.getIntExtra("timeLimit", 180);
          maxQuestion = data.getIntExtra("maxQuestion", 4);

        }
        else {
          timeLimit = 180;
          maxQuestion = 4;
        }

      }
    }
  }
}
