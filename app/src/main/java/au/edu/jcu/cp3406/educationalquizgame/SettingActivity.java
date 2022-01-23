package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
  public static int SETTING_REQUEST = 1234;
  Spinner timeLimitSpinner;
  Spinner maxQuesSpinner;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);

    // create spinners
    timeLimitSpinner = findViewById(R.id.timeSpinner);
    maxQuesSpinner = findViewById(R.id.maxQuesSpinner);

    // create adapters from suitable arrays in strings.xml
    ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.timeLimits, R.layout.spinner_style);
    ArrayAdapter<CharSequence> maxQuesAdapter = ArrayAdapter.createFromResource(this, R.array.maxQuesNum, R.layout.spinner_style);

    // apply Adapter array to respective Spinner
    timeLimitSpinner.setAdapter(timeAdapter);
    maxQuesSpinner.setAdapter(maxQuesAdapter);

    // set default values
    timeLimitSpinner.setSelection(2);
    maxQuesSpinner.setSelection(3);
  }

  public void applyBtnClicked(View view) {
    // get selected item from Spinner
    String timeLimitText = timeLimitSpinner.getSelectedItem().toString();
    String maxQuesText = maxQuesSpinner.getSelectedItem().toString();
    // convert to Integer
    int timeLimit = Integer.parseInt(timeLimitText);
    int maxQues = Integer.parseInt(maxQuesText);

    // add data to intent so Main Activity can access them
    Intent intent = new Intent();
    intent.putExtra("timeLimit", timeLimit);
    intent.putExtra("maxQuestion", maxQues);
    setResult(RESULT_OK, intent);
    finish();
  }

  // return to Main Activity without putting data into intent
  public void cancelBtnClicked(View view) {
    Toast.makeText(this, "Setting cancelled", Toast.LENGTH_SHORT).show();
    finish();
  }
}
