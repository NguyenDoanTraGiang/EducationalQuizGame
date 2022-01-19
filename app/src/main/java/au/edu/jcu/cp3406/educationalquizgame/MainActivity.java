package au.edu.jcu.cp3406.educationalquizgame;

/* Author: Doan Tra Giang Nguyen
Student ID: 13836396
App: Educational quiz game app for high school students
*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
  GameActivity gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

  public void levelBtnClicked(View view) {
      Button button = (Button) view;
      String btnText = button.getText().toString();

      // Start another activity using intent
      Intent intent = new Intent(this, GameActivity.class);
      // put the button name into intent so GameActivity can access it
      intent.putExtra("gameLabel", btnText);
      startActivity(intent);
  }

  public void settingBtnClicked(View view) {
    Intent intent = new Intent(this, SettingActivity.class);
    startActivity(intent);
  }

  public void highScoreBtnClicked(View view) {
    Intent intent = new Intent(this, HighScoreActivity.class);
    startActivity(intent);
  }
}
