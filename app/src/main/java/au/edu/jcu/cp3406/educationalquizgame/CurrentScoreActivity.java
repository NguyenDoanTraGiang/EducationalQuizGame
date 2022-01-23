package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentScoreActivity extends AppCompatActivity {
  Integer correctAnswerNum;
  Integer maxQuestionNum;
  String levelName;

  TextView scoreDisplay;
  TextView subjectDisplay;
  HighScoreDatabaseHelper databaseHelper;
  Button saveBtn, viewBtn;
  String highScoreValue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_current_score);

    Intent intent = getIntent();
    correctAnswerNum = intent.getIntExtra("correctAnswerNum", 0);
    maxQuestionNum = intent.getIntExtra("maxQuestionNum", 0);
    levelName = intent.getStringExtra("levelName");
    // capitalize the first character of level name
    String firstLetter = levelName.substring(0, 1);
    String otherLetter = levelName.substring(1);
    firstLetter = firstLetter.toUpperCase();
    String capitalizedName = firstLetter + otherLetter;

    subjectDisplay = findViewById(R.id.subjectDisplay);
    scoreDisplay = findViewById(R.id.currentScoreDisplay);

    subjectDisplay.setText(String.format("Subject: %s", capitalizedName));
    scoreDisplay.setText(String.format("Score: %1$s out of %2$s!", correctAnswerNum, maxQuestionNum));

    databaseHelper = new HighScoreDatabaseHelper(this);
    saveBtn = findViewById(R.id.saveScoreBtn);
    viewBtn = findViewById(R.id.viewScoreBtn);
    highScoreValue = String.format("%1$s: %2$s out of %3$s", capitalizedName, correctAnswerNum, maxQuestionNum);
  }

  public void saveBtnClicked(View view) {
    if (!highScoreValue.equals("")) {
      addHighScore(highScoreValue);
      highScoreValue = ""; // reset the value to empty if successfully added
    } else {
      Toast.makeText(this, "You already save this score!", Toast.LENGTH_SHORT).show();
    }
  }

  public void addHighScore(String highScoreValue) {
    boolean isDataInserted = databaseHelper.addData(highScoreValue);

    if (isDataInserted) {
      Toast.makeText(this, "Successfully save your score!", Toast.LENGTH_SHORT).show();

    } else {
      Toast.makeText(this, "Unable to save your score!", Toast.LENGTH_SHORT).show();
    }
  }

  public void viewBtnClicked(View view) {
    Intent intent = new Intent(this, ViewHighScoreActivity.class);
    startActivity(intent);
    finish();
  }
}
