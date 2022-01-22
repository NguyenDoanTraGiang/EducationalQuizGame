package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentScoreActivity extends AppCompatActivity {
  Integer correctAnswerNum;
  Integer maxQuestionNum;
  TextView exampleDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_score);

        Intent intent = getIntent();
        correctAnswerNum = intent.getIntExtra("correctAnswerNum", 0);
        maxQuestionNum = intent.getIntExtra("maxQuestionNum", 0);

        exampleDisplay = (TextView) findViewById(R.id.currentScoreDisplay);
        exampleDisplay.setText(String.format("Recent attempt: %1$s out of %2$s", correctAnswerNum, maxQuestionNum));

    }
}
