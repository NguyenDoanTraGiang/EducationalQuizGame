package au.edu.jcu.cp3406.educationalquizgame;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test
  public void QuestionTimerConstructor() {
    QuestionTimer questionTimer = new QuestionTimer(60, 0);
    assertEquals("01:00", questionTimer.toString());
  }

  @Test
  public void QuestionTimerTest() {
    QuestionTimer questionTimer = new QuestionTimer(60, 0);
    questionTimer.tick();
    assertEquals("00:59", questionTimer.toString());

    // test from 60 seconds to 1 minute
    for(int i=0; i<59; ++i){
      questionTimer.tick();
    }
    assertEquals("00:00", questionTimer.toString());
  }
}
