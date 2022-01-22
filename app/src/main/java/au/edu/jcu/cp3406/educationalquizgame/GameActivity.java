package au.edu.jcu.cp3406.educationalquizgame;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {
  Integer TIMER_SPEED = 1000;  // 1 second

  String levelName;
  TextView question;
  TextView correctAnswerDisplay;
  TextView currentQuestionDisplay;
  HashMap<String, String> quesAndAns;
  Integer correctAnswerNum;
  Integer currentQuestionNum;
  Integer maxQuestionNum;

  String[] questions;
  String[] correctAnswers;
  String[][] allAnswers;

  QuestionTimer questionTimer;
  TextView timer;
  Integer timeLimit;
  boolean isTimerRunning;
  Handler handler;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

      // get the button name from MainActivity intent
      Intent intent = getIntent();
      levelName = intent.getExtras().getString("gameLabel");
      timeLimit = intent.getIntExtra("timeLimit", 180);
      maxQuestionNum= intent.getIntExtra("maxQuestion", 4);

      // Set game label
      TextView gameLabel = (TextView) findViewById(R.id.gameLabel);
      gameLabel.setText("Answer this " + levelName + " question before time runs out!");

      // get Views
      question = (TextView) findViewById(R.id.quesDisplay);
      correctAnswerDisplay = (TextView) findViewById(R.id.correctAnswerDisplay);
      currentQuestionDisplay = (TextView) findViewById((R.id.currentQuesDisplay));

      //get timer display
      timer = (TextView) findViewById(R.id.timer);

      // set default value
      currentQuestionNum = 0;
      correctAnswerNum = 0;
      isTimerRunning = false;
      int minutes = timeLimit/60;
      int seconds = timeLimit % 60;

        if(savedInstanceState == null){
          questionTimer = new QuestionTimer(minutes, seconds);
        } else{
          int savedSeconds = savedInstanceState.getInt("seconds");
          int savedMinutes = savedInstanceState.getInt("minutes");
          currentQuestionNum = savedInstanceState.getInt("currentQuestionNum");
          maxQuestionNum = savedInstanceState.getInt("maxQuestionNum");
          isTimerRunning = savedInstanceState.getBoolean("isTimerRunning");
          correctAnswerNum = savedInstanceState.getInt("correctAnswerNum");
          //timeLimit = savedMinutes*60 + savedSeconds;
          questionTimer = new QuestionTimer(savedMinutes, savedSeconds);
        }


    startTimer();

        getQuiz(levelName);
        displayQuestion();
    }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("seconds", questionTimer.getSeconds());
    outState.putInt("minutes", questionTimer.getMinutes());
    outState.putInt("currentQuestionNum", currentQuestionNum);
    outState.putInt("maxQuestionNum", maxQuestionNum);
    outState.putBoolean("isTimerRunning", isTimerRunning);
    outState.putInt("correctAnswerNum", correctAnswerNum);
  }

  // display a question and its options
    public void displayQuestion(){
      if (currentQuestionNum > maxQuestionNum - 1){
        displayScore();
        //end the Activity when player answered all questions
        finish();
      } else {
        // display current answer and score
        currentQuestionDisplay.setText(String.format("Question: %1$s/%2$s", currentQuestionNum+1, maxQuestionNum));
        correctAnswerDisplay.setText(String.format("Correct: %1$s out of %2$s", correctAnswerNum, maxQuestionNum));

        // get arrays of option button ids
        final int[] optionBtns = {
          R.id.option1,
          R.id.option2,
          R.id.option3,
          R.id.option4
        };

        //quesAndAns = getQuestions(levelName, questions, correctAnswers);

        question.setText(questions[currentQuestionNum]);
        for (int j = 0; j < allAnswers.length; j++) {
          Button button = (Button) findViewById(optionBtns[j]);
          button.setText(allAnswers[currentQuestionNum][j]);
        }
      }

  }

  private void startTimer() {

    isTimerRunning = true;
    handler = new Handler();

    handler.post(new Runnable(){
      @Override
      public void run() {
        if(isTimerRunning){
          String currentTime = questionTimer.toString();
          timer.setText(currentTime);
          questionTimer.tick();
          handler.postDelayed(this, TIMER_SPEED);
        }
        if(questionTimer.toString().equals("00:00")){
            // stop handler from running the previous timer
            handler.removeCallbacks(this);
            Toast.makeText(getApplicationContext(), "Time's out!", Toast.LENGTH_SHORT).show();
            // end activity when player ran out of time
            displayScore();
            finish();
        }
      }
    });
  }

  //private void stopTimer() {
    //isTimerRunning = false;
  //}

  public void optionBtnClicked(View view) {
    // change to next question after an option has been pressed
    Button button = (Button) view;
    String optionText = button.getText().toString();
    if (optionText.equals(correctAnswers[currentQuestionNum])){
      currentQuestionNum++;
      correctAnswerNum++;
      Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
    } else {
      currentQuestionNum++;
      Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
    }
    displayQuestion();
  }

  public void displayScore() {
    // put data to intent for HighScore Activity
    Intent currentScoreIntent = new Intent(this, CurrentScoreActivity.class);
    currentScoreIntent.putExtra("correctAnswerNum", correctAnswerNum);
    currentScoreIntent.putExtra("maxQuestionNum", maxQuestionNum);
    currentScoreIntent.putExtra("levelName", levelName);
    startActivity(currentScoreIntent);
  }

  // put each question as key and answer as value in HashMap
    /*public HashMap<String, String> getQuestions(String levelName, String[] questions, String[] correctAnswers) {

      HashMap<String, String> quesAndAns = new HashMap<String, String>();
      for (int i=0; i < questions.length; i++){
        quesAndAns.put(questions[i], correctAnswers[i]);
      }
      return quesAndAns;
  }*/

  // get all the questions, their answers and options
  private void getQuiz(String levelName) {
    switch (levelName) {
      case "math":
        //get arrays for questions and correct answers from strings.xml
        questions = getResources().getStringArray(R.array.mathQuestions);
        correctAnswers = getResources().getStringArray(R.array.mathAnswers);
        // get arrays of arrays for each question's options
        allAnswers = new String[][]{
          getResources().getStringArray(R.array.mathOptions1),
          getResources().getStringArray(R.array.mathOptions2),
          getResources().getStringArray(R.array.mathOptions3),
          getResources().getStringArray(R.array.mathOptions4),
        };
        break;
      case "english":
        questions = getResources().getStringArray(R.array.engQuestions);
        correctAnswers = getResources().getStringArray(R.array.engAnswers);
        allAnswers = new String[][]{
          getResources().getStringArray(R.array.engOptions1),
          getResources().getStringArray(R.array.engOptions2),
          getResources().getStringArray(R.array.engOptions3),
          getResources().getStringArray(R.array.engOptions4),
        };
        break;
      case "geography":
        questions = getResources().getStringArray(R.array.geoQuestions);
        correctAnswers = getResources().getStringArray(R.array.geoAnswers);
        allAnswers = new String[][]{
          getResources().getStringArray(R.array.geoOptions1),
          getResources().getStringArray(R.array.geoOptions2),
          getResources().getStringArray(R.array.geoOptions3),
          getResources().getStringArray(R.array.geoOptions4),
        };
        break;
      case "history":
        questions = getResources().getStringArray(R.array.hisQuestions);
        correctAnswers = getResources().getStringArray(R.array.hisAnswers);
        allAnswers = new String[][]{
          getResources().getStringArray(R.array.hisOptions1),
          getResources().getStringArray(R.array.hisOptions2),
          getResources().getStringArray(R.array.hisOptions3),
          getResources().getStringArray(R.array.hisOptions4),
        };
        break;
    }
  }

}
