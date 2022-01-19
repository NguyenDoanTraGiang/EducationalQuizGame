package au.edu.jcu.cp3406.educationalquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {
  Spinner timeLimitSpinner;
  Spinner maxQuesSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // create spinners
      timeLimitSpinner = (Spinner) findViewById(R.id.timeSpinner);
      maxQuesSpinner = (Spinner) findViewById(R.id.maxQuesSpinner);

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

}
