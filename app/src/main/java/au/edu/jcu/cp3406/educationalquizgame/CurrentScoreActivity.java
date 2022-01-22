package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class CurrentScoreActivity extends AppCompatActivity {
  Integer correctAnswerNum;
  Integer maxQuestionNum;
  String levelName;
  TextView scoreDisplay;
  TextView subjectDisplay;

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

        subjectDisplay = (TextView) findViewById(R.id.subjectDisplay);
        scoreDisplay = (TextView) findViewById(R.id.currentScoreDisplay);

        subjectDisplay.setText(String.format("Subject: %s", capitalizedName));
        scoreDisplay.setText(String.format("Score: %1$s out of %2$s!", correctAnswerNum, maxQuestionNum));

    }
}
