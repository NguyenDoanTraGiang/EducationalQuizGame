package au.edu.jcu.cp3406.educationalquizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoreDatabaseHelper extends SQLiteOpenHelper {
  public static final String DATABASE_NAME = "high_score.db";
  public static final String TABLE_NAME = "high_score";
  public static final String COLUMN1= "ID";
  public static final String COLUMN2 = "HIGH_SCORE";

  public HighScoreDatabaseHelper(Context context){
      super(context, DATABASE_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // create a table with ID column as primary key
    String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
      " HIGH_SCORE TEXT)";
    sqLiteDatabase.execSQL(createTable);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
  }

  public boolean addData(String highScore){
    SQLiteDatabase database = this.getWritableDatabase();  // the database can be read or write
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN2, highScore); // put the high score to column 2

    // insert the data into table
    long result = database.insert(TABLE_NAME, null, contentValues);

    if (result == -1){
      // database.insert returns -1 if the data is not inserted successfully
        return false;
    } else{
      return true;
    }
  }

  public Cursor getHighScoreList(){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    // get the values of all columns from table using SQL query
    return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
  }
}
