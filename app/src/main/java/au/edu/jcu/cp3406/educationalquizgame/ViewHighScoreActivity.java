package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewHighScoreActivity extends AppCompatActivity {
  HighScoreDatabaseHelper databaseHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_high_score);

    ListView highScoreDisplay = findViewById(R.id.highScoreDisplay);
    databaseHelper = new HighScoreDatabaseHelper(this);

    // put all values in database to an Array
    ArrayList<String> highScores = new ArrayList<>();
    Cursor highScoreValues = databaseHelper.getHighScoreList();

    if (highScoreValues.getCount() == 0) {
      Toast.makeText(this, "There is no saved high scores yet!", Toast.LENGTH_SHORT).show();
    } else {
      while (highScoreValues.moveToNext()) {
        highScores.add(highScoreValues.getString(1)); // get values from second column of the database (index 1)
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScores);
        highScoreDisplay.setAdapter(listAdapter);
      }
    }
  }

  public void toHomeBtnClicked(View view) {
    // exit the activity
    finish();
  }
}
